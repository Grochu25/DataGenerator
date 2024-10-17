package mainPackage.View.tabs;

import mainPackage.AppendGeneratorEvent;
import mainPackage.Model.FileLoader;
import mainPackage.Model.Generators.CustomGenerator;
import mainPackage.View.CustomsManager;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

public class CustomsTab extends JPanel
{
    private JPanel customFieldRow;
    private JPanel buttonsRow;

    public CustomsTab()
    {
        setLayout(new GridLayout(2,1));
        customFieldRow = new JPanel();
        customFieldRow.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));
        buttonsRow = new JPanel();

        scanForCustomFiles();
        createControlButtons();

        add(customFieldRow);
        add(buttonsRow);
    }

    private void scanForCustomFiles()
    {
        customFieldRow.removeAll();
        List<File> files = FileLoader.getSubfilesOfPath("./sources/customs");
        files.forEach(f->createButtonWithCustom(f.getName()));
        this.validate();
        this.repaint();
    }

    private void createButtonWithCustom(String fullFileName)
    {
        JButton customCreated = new JButton(fullFileName.substring(0,fullFileName.length()-4));
        customCreated.addActionListener(new AppendGeneratorEvent<String>(new CustomGenerator(fullFileName)));
        customFieldRow.add(customCreated);
    }

    private void createControlButtons()
    {
        JButton addNewFieldButton = new JButton("Dodaj pole");
        JButton deleteFieldButton  = new JButton("Usuń pole");

        addNewFieldButton.addActionListener(e->{
            CustomsManager.copyToSources(FileLoader.chooseFileToRead());
            scanForCustomFiles();
        });

        deleteFieldButton.addActionListener(e->{
            CustomsManager.deleteFromCustoms();
            scanForCustomFiles();
        });

        buttonsRow.add(addNewFieldButton);
        buttonsRow.add(deleteFieldButton);
    }
}
