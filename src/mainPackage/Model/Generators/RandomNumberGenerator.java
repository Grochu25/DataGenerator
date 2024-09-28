package mainPackage.Model.Generators;

import mainPackage.Generator;
import mainPackage.Model.Generable;

public class RandomNumberGenerator implements Generable<Integer>
{
    private Integer lastGenerated;
    private Integer from = 0;
    private Integer to = 100;

    public RandomNumberGenerator(Integer from, Integer to)
    {
        this.from = from;
        this.to = to;
    }

    public RandomNumberGenerator(){}

    @Override
    public Integer generate() {
        lastGenerated = Generator.getInstance().getRandom().nextInt(to-from)+from;
        return lastGenerated;
    }

    @Override
    public Integer getLastGenerated() {
        return lastGenerated;
    }
}
