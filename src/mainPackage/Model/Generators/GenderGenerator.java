package mainPackage.Model.Generators;

import mainPackage.Model.Generator;
import mainPackage.Main;
import mainPackage.Model.Generable;
import mainPackage.View.SelectionTableDialog;

import java.util.List;

public class GenderGenerator implements Generable<String>
{
    private NameGenerator nameGenerator;
    private String lastGenerated;

    public GenderGenerator(NameGenerator nameReference)
    {
        this.nameGenerator = nameReference;
    }

    public GenderGenerator()
    {
        setDependencies();
    }

    @Override
    public String generate() {
        if(nameGenerator != null)
            lastGenerated = genderFromName();
        else
            lastGenerated = Generator.getInstance().getRandom().nextBoolean() ? "Mężczyzna" : "Kobieta";

        return lastGenerated;
    }

    private String genderFromName()
    {
        String name = nameGenerator.getLastGenerated();
        if(name.endsWith("a"))
            return "Kobieta";
        else
            return "Mężczyzna";
    }

    @Override
    public String getLastGenerated() {
        return lastGenerated;
    }

    @Override
    public String getGeneratorLabel() {
        return "Płeć";
    }

    @Override
    public void setDependencies() {
        List<Generable<?>> generables = Generator.getInstance().getGenerablesList();
        SelectionTableDialog<Generable<?>> tableDialog = new SelectionTableDialog<>(Main.frame, generables);

        Generable<?> choosed = tableDialog.showDialogAndGetField("Wybierz pole Imię do połączenia",
                        "Wybierz pole Imię do połączenia lub zamknij to okienko dla losowych danych");

        this.nameGenerator = (choosed instanceof NameGenerator) ? (NameGenerator) choosed : null;
    }

    @Override
    public boolean isDependenceSet() {
        if (!Generator.getInstance().getGenerablesList().contains(nameGenerator))
            nameGenerator = null;

        return nameGenerator != null;
    }

    @Override
    public String toString() {return getGeneratorLabel();}
}
