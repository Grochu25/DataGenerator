package mainPackage.Model.Generators;

import mainPackage.Generator;
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
}
