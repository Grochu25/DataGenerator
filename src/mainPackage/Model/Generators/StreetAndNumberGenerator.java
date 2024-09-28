package mainPackage.Model.Generators;

import mainPackage.Generator;

public class StreetAndNumberGenerator extends StreetGenerator
{
    @Override
    public String generate() {
        lastGenerated = super.generate() +" "+ (Generator.getInstance().getRandom().nextInt(200)+1);
        return lastGenerated;
    }
}
