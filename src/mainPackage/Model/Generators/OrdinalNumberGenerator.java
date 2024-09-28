package mainPackage.Model.Generators;

import mainPackage.Model.Generable;

public class OrdinalNumberGenerator implements Generable<Integer>
{
    private static int ordinalNumber;

    @Override
    public Integer generate() {
        return ++ordinalNumber;
    }

    @Override
    public Integer getLastGenerated() {
        return ordinalNumber;
    }
}
