package mainPackage.View;

import mainPackage.View.tabs.BasicTab;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ComponentTabbedPane extends JTabbedPane
{
    public ComponentTabbedPane()
    {
        add("Podstawowe", new BasicTab());
    }


}
