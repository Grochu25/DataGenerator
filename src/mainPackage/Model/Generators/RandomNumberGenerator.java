package mainPackage.Model.Generators;

import mainPackage.Model.Generator;
import mainPackage.Model.Generable;

public class RandomNumberGenerator implements Generable<Integer>
{
    private Integer lastGenerated;
    private Integer from = 0;
    private Integer to = 100;

    public RandomNumberGenerator(Integer from, Integer to)
    {
        if(from.compareTo(to) <= 0) {
            this.from = from;
            this.to = to;
        }
        else{
            this.from = to;
            this.to = from;
        }
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

    @Override
    public String getGeneratorLabel() {
        return String.format("Liczba losowa (%d-%d)", from, to);
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
