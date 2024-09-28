package mainPackage;

import mainPackage.Model.Generable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppendGeneratorEvent<T> implements ActionListener
{
    private Generable<T> generable;

    public AppendGeneratorEvent(Generable<T> generable){
        this.generable = generable;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Generator.getInstance().addToGenerables(generable);
    }
}
