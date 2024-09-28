package mainPackage.Model.Generators;

import mainPackage.Generator;
import mainPackage.Model.Generable;

public class EmailGenerator implements Generable<String>
{
    private NameGenerator nameGenerator;
    private SurnameGenerator surnameGenerator;
    private String[] mails = {"@gmail.com","@wp.pl","@onet.pl","@yahoo.com"};

    private String lastGenerated;

    public EmailGenerator(NameGenerator nameGenerator, SurnameGenerator surnameGenerator)
    {
        this.nameGenerator = nameGenerator;
        this.surnameGenerator = surnameGenerator;
    }

    public EmailGenerator()
    {
        this.nameGenerator = new NameGenerator();
        this.surnameGenerator = new SurnameGenerator();
    }

    @Override
    public String generate() {
        lastGenerated = nameGenerator.getLastGenerated().substring(0,3)+
                surnameGenerator.getLastGenerated() +
                (Generator.getInstance().getRandom().nextInt(99)+1) +
                mails[Generator.getInstance().getRandom().nextInt(mails.length)];
        return lastGenerated;
    }

    @Override
    public String getLastGenerated() {
        return lastGenerated;
    }
}
