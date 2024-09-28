package mainPackage.View;

import mainPackage.Generator;

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

        JButton generateButton = new JButton("Generuj");
        generateButton.addActionListener(e-> Generator.getInstance().Generate());

        ChoosedInfoPane cip = new ChoosedInfoPane();
        Generator.getInstance().addActionListener(cip);

        add(new ComponentTabbedPane(), BorderLayout.NORTH);
        add(cip, BorderLayout.CENTER);
        add(generateButton, BorderLayout.SOUTH);
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
