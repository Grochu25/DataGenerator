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

    @Override
    public String getGeneratorLabel() {
        return "Liczba Porządkowa";
    }

    @Override
    public boolean isDependenceSet() {
        return true;
    }

    @Override
    public void setDependencies() {}

    @Override
    public String toString() {return getGeneratorLabel();}

    public void set(int start)
    {
        ordinalNumber = start;
    }
}
