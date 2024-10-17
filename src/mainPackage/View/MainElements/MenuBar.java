package mainPackage.View.MainElements;

import mainPackage.DB.DBConfigDialog;
import mainPackage.Model.Generator;

import javax.swing.*;

public class MenuBar extends JMenuBar
{
    public MenuBar()
    {
        addOutputMenu();
        addDataBaseOptions();
    }

    private void addOutputMenu()
    {
        JMenu outputMenu = new JMenu("Wyjścia");
        JRadioButtonMenuItem toFile = new JRadioButtonMenuItem("plik tekstowy");
        JRadioButtonMenuItem toDataBase = new JRadioButtonMenuItem("Baza Danych");
        ButtonGroup radioGroup = new ButtonGroup();
        radioGroup.add(toFile);
        radioGroup.add(toDataBase);

        toFile.addActionListener(e-> Generator.getInstance().setSaveMode(Generator.SaveMode.File));
        toDataBase.addActionListener(e-> {Generator.getInstance().setSaveMode(Generator.SaveMode.DataBase);
            Generator.getInstance().setQuotation(true);
            Generator.getInstance().setBrackets(true);
            Generator.getInstance().setSeparator(',');
        });

        toFile.setSelected(true);
        outputMenu.add(toFile);
        outputMenu.add(toDataBase);
        add(outputMenu);
    }

    private void addDataBaseOptions()
    {
        JMenu dataBaseMenu = new JMenu("Baza danych");
        JMenuItem dataBaseOptions = new JMenuItem("Opcje bazy danych");
        JCheckBoxMenuItem overrideData = new JCheckBoxMenuItem("napisuj dane");

        dataBaseOptions.addActionListener(e-> DBConfigDialog.getInstance().showDialog());
        overrideData.addActionListener(e -> Generator.getInstance().setDataBaseOverride(overrideData.isSelected()));

        dataBaseMenu.add(dataBaseOptions);
        dataBaseMenu.add(overrideData);
        add(dataBaseMenu);
    }

}
