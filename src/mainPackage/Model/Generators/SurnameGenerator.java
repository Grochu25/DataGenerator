package mainPackage.Model.Generators;

import mainPackage.Generator;
import mainPackage.Model.FileLoader;
import mainPackage.Model.Generable;

import java.util.ArrayList;

public class SurnameGenerator implements Generable<String>
{
    private static ArrayList<String> surnames;
    private String lastGenerated;

    public SurnameGenerator()
    {
        if(surnames == null || surnames.isEmpty()) {
            surnames = new ArrayList<>();
            surnames.addAll(FileLoader.loadFromFile("sources/nazwiska.txt"));
        }
    }

    @Override
    public String generate() {
        lastGenerated = surnames.get(Generator.getInstance().getRandom().nextInt(surnames.size()));
        return lastGenerated;
    }

    @Override
    public String getLastGenerated() {
        return lastGenerated;
    }
}
