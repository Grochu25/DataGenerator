package mainPackage.Model;

public interface Generable<T>
{
    T generate();
    T getLastGenerated();
    String getGeneratorLabel();
    void setDependencies();
    boolean isDependenceSet();
}
