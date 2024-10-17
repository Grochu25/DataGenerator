package mainPackage.Model.Generators;

import mainPackage.Model.Generator;

public class StreetAndNumberGenerator extends StreetGenerator
{
    @Override
    public String generate() {
        lastGenerated = super.generate() +" "+ (Generator.getInstance().getRandom().nextInt(200)+1);
        return lastGenerated;
    }

    @Override
    public String getGeneratorLabel() {
        return "Ulica i numer budynku";
    }

    @Override
    public String toString() {return getGeneratorLabel();}
}
