package mainPackage.Model.Generators;

import mainPackage.Model.Generator;
import mainPackage.Model.FileLoader;
import mainPackage.Model.Generable;

import java.util.ArrayList;

public class CustomGenerator implements Generable<String>
{

    private final ArrayList<String> records;
    private String lastGenerated;
    private String name;

    public CustomGenerator(String fullFileName)
    {
        name = fullFileName;
        records = new ArrayList<>();
        records.addAll(FileLoader.loadFromFile("sources/customs/"+fullFileName));
    }

    @Override
    public String generate() {
        lastGenerated = records.get(Generator.getInstance().getRandom().nextInt(records.size()));
        return lastGenerated;
    }

    @Override
    public String getGeneratorLabel() {
        return name;
    }

    @Override
    public String getLastGenerated() {return lastGenerated;}

    @Override
    public void setDependencies() {}

    @Override
    public boolean isDependenceSet() {
        return true;
    }

    @Override
    public String toString() {return getGeneratorLabel();}

}
