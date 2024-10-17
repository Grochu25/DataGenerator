package mainPackage.View.customControls;

import mainPackage.Model.Generable;

import javax.swing.*;

public class InfoTile extends JButton
{
    private Generable<?> associatedGenerable;

    public InfoTile(String content, Generable<?> associatedGenerable){
        super(content);
        this.associatedGenerable = associatedGenerable;
    }

    public Generable<?> getAssociatedGenerable() {return associatedGenerable;}
}
