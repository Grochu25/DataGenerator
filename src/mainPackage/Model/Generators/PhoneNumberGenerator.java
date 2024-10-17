package mainPackage.Model.Generators;

import mainPackage.Model.Generator;
import mainPackage.Model.Generable;

public class PhoneNumberGenerator implements Generable<Integer>
{
    private Integer lastGenerated;

    @Override
    public Integer generate() {
        lastGenerated = Generator.getInstance().getRandom().nextInt(899999999)+100000000;
        return lastGenerated;
    }

    @Override
    public Integer getLastGenerated() {
        return lastGenerated;
    }

    @Override
    public String getGeneratorLabel() {
        return "Numer Telefonu";
    }

    @Override
    public void setDependencies() {}

    @Override
    public boolean isDependenceSet() {
        return true;
    }

    @Override
    public String toString() {return getGeneratorLabel();}
}
