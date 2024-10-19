package mainPackage.View.customControls;

import mainPackage.Model.Generable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class TilePanel extends JPanel implements ActionListener
{
    private ArrayList<InfoTile> tiles = new ArrayList<>();

    public TilePanel()
    {
        setLayout(new FlowLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        createTile(e.getActionCommand(), (Generable<?>) e.getSource());
    }

    private void createTile(String name, Generable<?> associatedGenerable)
    {
        InfoTile tile = new InfoTile(name, associatedGenerable);
        tile.setBorder(BorderFactory.createRaisedSoftBevelBorder());
        tile.addActionListener(e->{
            tile.getAssociatedGenerable().setDependencies();
            checkTileDependencies(tile);
        });
        checkTileDependencies(tile);
        tiles.add(tile);
        add(tile);
        validate();
    }

    private void checkTileDependencies(InfoTile tile)
    {
        if(!(tile.getAssociatedGenerable().isDependenceSet()))
            tile.setForeground(Color.RED);
        else
            tile.setForeground(Color.BLACK);
    }

    public void deleteTileByGenerable(Generable<?> generable)
    {
        InfoTile toDelete = null;
        for(InfoTile tile : tiles){
            if(generable == tile.getAssociatedGenerable()){
                toDelete = tile;
            }
        }

        if(toDelete != null) {
            this.remove(toDelete);
            tiles.remove(toDelete);
        }

        tiles.forEach(this::checkTileDependencies);
    }
}
