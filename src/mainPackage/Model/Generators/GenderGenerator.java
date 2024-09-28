package mainPackage.Model.Generators;

import mainPackage.Generator;
import mainPackage.Model.Generable;

public class GenderGenerator implements Generable<String>
{
    private NameGenerator nameGenerator;
    private String lastGenerated;

    public GenderGenerator(NameGenerator nameReference)
    {
        this.nameGenerator = nameReference;
    }

    public GenderGenerator() {}

    @Override
    public String generate() {
        if(nameGenerator != null)
            lastGenerated = genderFromName();
        else
            lastGenerated = Generator.getInstance().getRandom().nextBoolean() ? "Mężczyzna" : "Kobieta";

        return lastGenerated;
    }

    @Override
    public String getLastGenerated() {
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
}
