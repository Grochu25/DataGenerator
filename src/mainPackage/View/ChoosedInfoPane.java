package mainPackage.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ChoosedInfoPane extends JPanel implements ActionListener
{
    private ArrayList<JLabel> tiles;

    @Override
    public void actionPerformed(ActionEvent e) {
        createTile(e.getActionCommand());
    }

    private void createTile(String name)
    {
        JTextArea tile = new JTextArea(name);
        tile.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        tile.setEditable(false);
        add(tile);
        validate();
    }
}
