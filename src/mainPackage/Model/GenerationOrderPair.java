package mainPackage.Model;

public class GenerationOrderPair
{
    public Integer order;
    public Generable<?> generable;

    public GenerationOrderPair(Integer order, Generable<?> generable)
    {
        this.order = order;
        this.generable = generable;
    }
}
