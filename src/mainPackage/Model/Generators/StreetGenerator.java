package mainPackage.Model.Generators;

import mainPackage.Model.Generator;
import mainPackage.Model.FileLoader;
import mainPackage.Model.Generable;

import java.util.ArrayList;

public class StreetGenerator implements Generable<String>
{
    protected static ArrayList<String> streets;
    protected String lastGenerated;

    public StreetGenerator()
    {
        if(streets == null || streets.isEmpty()){
            streets = new ArrayList<>();
            streets.addAll(FileLoader.loadFromFile("sources/ulice.txt"));
        }
    }

    @Override
    public String generate() {
        lastGenerated = streets.get(Generator.getInstance().getRandom().nextInt(streets.size()));
        return lastGenerated;
    }

    @Override
    public String getGeneratorLabel() {
        return "Ulica";
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
