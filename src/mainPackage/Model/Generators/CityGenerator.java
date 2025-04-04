package mainPackage.Model.Generators;

import mainPackage.Model.Generator;
import mainPackage.Model.FileLoader;
import mainPackage.Model.Generable;

import java.util.ArrayList;

public class CityGenerator implements Generable<String>
{
    private static ArrayList<String> cities;
    private String lastGenerated;

    public CityGenerator()
    {
        if(cities == null || cities.isEmpty()){
            cities = new ArrayList<>();
            cities.addAll(FileLoader.loadFromFile("sources/miasta.txt"));
        }
    }

    @Override
    public String generate() {
        lastGenerated = cities.get(Generator.getInstance().getRandom().nextInt(cities.size()));
        return lastGenerated;
    }

    @Override
    public String getGeneratorLabel() {
        return "Miasto";
    }

    @Override
    public void setDependencies() {}

    @Override
    public boolean isDependenceSet() {
        return true;
    }

    @Override
    public String getLastGenerated() {return lastGenerated;}

    @Override
    public String toString() {return getGeneratorLabel();}
}
