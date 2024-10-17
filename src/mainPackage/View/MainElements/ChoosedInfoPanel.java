package mainPackage.View.MainElements;

import mainPackage.Model.Generator;
import mainPackage.Main;
import mainPackage.Model.Generable;
import mainPackage.View.SelectionTableDialog;
import mainPackage.View.customControls.TilePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChoosedInfoPanel extends JPanel implements ActionListener
{
    private TilePanel tilePanel = new TilePanel();

    public ChoosedInfoPanel()
    {
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 30;
        add(tilePanel, gbc);

        JButton removeFieldButton = new JButton("Usuń pole");
        removeFieldButton.addActionListener(e->removeAction());

        gbc.gridy = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        add(removeFieldButton, gbc);
    }

    private void removeAction()
    {
        SelectionTableDialog<Generable<?>> chooser = new SelectionTableDialog<>(Main.frame, Generator.getInstance().getGenerablesList());
        Generable<?> deletedItem = chooser.showDialogAndGetField("Wybierz pole, które chcesz usunąć", "Usuń pole");
        Generator.getInstance().removeFromGenerables(deletedItem);
        tilePanel.deleteTileByGenerable(deletedItem);
        validate();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tilePanel.actionPerformed(e);
    }
}
