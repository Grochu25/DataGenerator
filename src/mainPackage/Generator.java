package mainPackage;

import mainPackage.Model.Generable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Random;

public class Generator
{
    private static Generator instance;

    private final Random random;
    private final ArrayList<Generable> generables;
    private DateTimeFormatter dateTimeFormatter;
    private ActionListener listener;

    public static Generator getInstance()
    {
        if(instance == null)
            instance = new Generator();

        return instance;
    }

    private Generator()
    {
        random = new Random();
        generables = new ArrayList<>();
        dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
    }

    public void Generate()
    {
        for(Generable g : generables)
        {
            System.out.println(g.generate());
        }
    }

    public Random getRandom() {return random;}
    public DateTimeFormatter getDateTimeFormatter() {return dateTimeFormatter;}
    public void addToGenerables(Generable generable){generables.add(generable); listener.actionPerformed(new ActionEvent(generable,0 , generable.getGeneratorLabel()));}

    public void addActionListener(ActionListener actionListener)
    {
        instance.listener = actionListener;
    }
}
