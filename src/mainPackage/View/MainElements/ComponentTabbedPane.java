package mainPackage.View.MainElements;

import mainPackage.View.tabs.*;

import javax.swing.*;

public class ComponentTabbedPane extends JTabbedPane
{
    public ComponentTabbedPane()
    {
        add("Podstawowe", new BasicTab());
        add("Dane osobowe", new PersonalDataTab());
        add("Adres", new AddressTab());
        add("Data", new DateTab());
        add("Własne", new CustomsTab());
    }


}
