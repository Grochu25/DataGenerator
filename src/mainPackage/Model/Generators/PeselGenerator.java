package mainPackage.Model.Generators;

import mainPackage.Model.Generator;
import mainPackage.Main;
import mainPackage.Model.Generable;
import mainPackage.View.SelectFromListDialog;

import java.time.LocalDate;
import java.util.List;

public class PeselGenerator implements Generable<String>
{
    private GenderGenerator genderGenerator;
    private DateGenerator dateGenerator;
    private long lastGenerated;

    public PeselGenerator(DateGenerator dateGenerator)
    {
        this.dateGenerator = dateGenerator;
        this.genderGenerator = null;
    }

    public PeselGenerator(GenderGenerator genderGenerator, DateGenerator dateGenerator)
    {
        this.genderGenerator = genderGenerator;
        this.dateGenerator = dateGenerator;
    }

    public PeselGenerator(){
        setDependencies();
    };

    @Override
    public String generate() {
        LocalDate date = generateDatePart();
        long genderNumber = generateGenderNumber();
        lastGenerated = 0;
        lastGenerated += (date.getYear() % 100) * 1000000000L;
        lastGenerated += (date.getMonthValue() + ((date.getYear()>=2000)? 20L : 0L)) * 10000000L;
        lastGenerated += (date.getDayOfMonth()) * 100000L;
        lastGenerated += (Generator.getInstance().getRandom().nextLong(1000L)) * 100L;
        lastGenerated += genderNumber;
        lastGenerated += controlSum(lastGenerated);
        System.out.println(date+" "+genderGenerator.getLastGenerated());
        return String.format("%011d",lastGenerated);
    }

    private LocalDate generateDatePart()
    {
        String dateString = (dateGenerator == null) ? new DateGenerator().generate() : dateGenerator.getLastGenerated();
        return LocalDate.parse(dateString);
    }

    private long generateGenderNumber()
    {
        String gender = (genderGenerator == null) ? new GenderGenerator().generate() : genderGenerator.getLastGenerated();
        return ((Generator.getInstance().getRandom().nextLong(5)*2L) + (gender.equals("Kobieta")? 0:1)) * 10L;
    }

    private long controlSum(Long pesel)
    {
        long controlSum = 0L;
        pesel /= 10;
        int[] multipliers = {3,1,9,7,3,1,9,7,3,1};
        for(int i=0;i<10;i++) {
            controlSum += (pesel % 10) * multipliers[i];
            pesel /= 10;
        }

        return 10L - controlSum%10L;
    }

    @Override
    public String getLastGenerated() {
        return String.format("%011.0f",lastGenerated);
    }

    @Override
    public String getGeneratorLabel() {
        return "PESEL";
    }

    @Override
    public void setDependencies() {
        List<Generable<?>> generables = Generator.getInstance().getGenerablesList();

        SelectFromListDialog<Generable<?>> tableDialog = new SelectFromListDialog<>(Main.frame, generables);

        int choosed = tableDialog.showDialogAndGetFieldIndex("Wybierz pole Imię lub płeć do połączenia",
                        "Wybierz pole Imię lub płeć do połączenia lub zamknij to okienko dla losowych danych");
        Generable<?> found = (choosed < 0) ? null : generables.get(choosed);

        if(found instanceof NameGenerator)
            this.genderGenerator = new GenderGenerator((NameGenerator) found);
        else if(found instanceof GenderGenerator)
            this.genderGenerator = (GenderGenerator) found;
        else
            this.genderGenerator = null;

        choosed = tableDialog.showDialogAndGetFieldIndex("Wybierz pole Datę urodzenia do połączenia",
                        "Wybierz pole Datę urodzenia do połączenia lub zamknij to okienko dla losowych danych");
        found = (choosed < 0) ? null : generables.get(choosed);
        this.dateGenerator = (found instanceof DateGenerator) ? (DateGenerator)found : null;
    }

    @Override
    public boolean isDependenceSet() {
        if (!Generator.getInstance().getGenerablesList().contains(genderGenerator))
            genderGenerator = null;
        if (!Generator.getInstance().getGenerablesList().contains(dateGenerator))
            dateGenerator = null;

        return genderGenerator != null && dateGenerator != null;
    }

    @Override
    public String toString() {return getGeneratorLabel();}
}
