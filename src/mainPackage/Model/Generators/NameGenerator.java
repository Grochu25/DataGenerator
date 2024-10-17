package mainPackage.Model.Generators;

import mainPackage.Model.Generator;
import mainPackage.Model.FileLoader;
import mainPackage.Model.Generable;


import java.util.ArrayList;

public class NameGenerator implements Generable<String>
{
    private static ArrayList<String> names;
    private String lastGenerated;

    public NameGenerator()
    {
        if(names == null || names.isEmpty()){
            names = new ArrayList<>();
            names.addAll(FileLoader.loadFromFile("sources/imiona.txt"));
        }
    }

    @Override
    public String generate() {
        lastGenerated = names.get(Generator.getInstance().getRandom().nextInt(names.size()));
        return lastGenerated;
    }

    @Override
    public String getGeneratorLabel() {
        return "Imię";
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
