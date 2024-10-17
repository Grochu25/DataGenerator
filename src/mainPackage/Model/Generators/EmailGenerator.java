package mainPackage.Model.Generators;

import mainPackage.Model.Generator;
import mainPackage.Main;
import mainPackage.Model.Generable;
import mainPackage.View.SelectionTableDialog;

import java.util.List;

public class EmailGenerator implements Generable<String>
{
    private NameGenerator nameGenerator;
    private SurnameGenerator surnameGenerator;
    private String[] mails = {"@gmail.com","@wp.pl","@onet.pl","@yahoo.com"};

    private String lastGenerated;

    public EmailGenerator(NameGenerator nameGenerator, SurnameGenerator surnameGenerator)
    {
        this.nameGenerator = nameGenerator;
        this.surnameGenerator = surnameGenerator;
    }

    public EmailGenerator()
    {
        setDependencies();
    }

    @Override
    public String generate() {
        String namePart = generateNamePart();
        String surnamePart = generateSurnamePart();

        lastGenerated = namePart.substring(0,3)+
                surnamePart +
                (Generator.getInstance().getRandom().nextInt(99)+1) +
                mails[Generator.getInstance().getRandom().nextInt(mails.length)];
        return lastGenerated;
    }

    private String generateNamePart()
    {
        return (nameGenerator == null || nameGenerator.getLastGenerated() == null) ? new NameGenerator().generate() : nameGenerator.getLastGenerated();
    }

    private String generateSurnamePart()
    {
        return (surnameGenerator == null || surnameGenerator.getLastGenerated() == null) ? new SurnameGenerator().generate() : surnameGenerator.getLastGenerated();
    }

    @Override
    public String getLastGenerated() {
        return lastGenerated;
    }

    @Override
    public String getGeneratorLabel() {
        return "Email";
    }

    @Override
    public void setDependencies() {
        List<Generable<?>> generables = Generator.getInstance().getGenerablesList();
        SelectionTableDialog<Generable<?>> tableDialog = new SelectionTableDialog<>(Main.frame, generables);

        Generable<?> choosed = tableDialog.showDialogAndGetField("Wybierz pole Imię do połączenia",
                        "Wybierz pole Imię do połączenia lub zamknij to okienko dla losowych danych");
        this.nameGenerator = (choosed instanceof NameGenerator) ? (NameGenerator) choosed : null;

        choosed = tableDialog.showDialogAndGetField("Wybierz pole Nazwisko do połączenia",
                        "Wybierz pole Nazwisko do połączenia lub zamknij to okienko dla losowych danych");
        this.surnameGenerator = (choosed instanceof SurnameGenerator) ? (SurnameGenerator) choosed : null;
    }

    @Override
    public boolean isDependenceSet() {
        if (!Generator.getInstance().getGenerablesList().contains(nameGenerator))
            nameGenerator = null;
        if (!Generator.getInstance().getGenerablesList().contains(surnameGenerator))
            surnameGenerator = null;

        return nameGenerator != null && surnameGenerator != null;
    }

    @Override
    public String toString() {return getGeneratorLabel();}
}
