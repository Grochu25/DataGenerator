package mainPackage.View;

import mainPackage.Model.Generator;
import mainPackage.View.MainElements.ChoosedInfoPanel;
import mainPackage.View.MainElements.ComponentTabbedPane;
import mainPackage.View.MainElements.MenuBar;
import mainPackage.View.MainElements.NumberAndApplayPane;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame
{
    private int windowHeight;
    private int windowWidth;

    public MainFrame()
    {
        computeSize();
        setTitle("Generator danych");

        ChoosedInfoPanel cip = new ChoosedInfoPanel();
        NumberAndApplayPane naap = new NumberAndApplayPane();
        Generator.getInstance().addActionListener(cip);
        Generator.getInstance().addSaveModeListener(naap);

        add(new ComponentTabbedPane(), BorderLayout.NORTH);
        add(cip, BorderLayout.CENTER);
        add(naap, BorderLayout.SOUTH);
        setJMenuBar(new MenuBar());
    }

    private void computeSize()
    {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        windowHeight = screenSize.height/2;
        windowWidth = screenSize.width/2;
        setSize(windowWidth, windowHeight);
    }
}
