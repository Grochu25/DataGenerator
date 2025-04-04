package mainPackage.View.tabs;

import mainPackage.Model.AppendGeneratorEvent;
import mainPackage.Model.Generators.CityGenerator;
import mainPackage.Model.Generators.StreetAndNumberGenerator;
import mainPackage.Model.Generators.StreetGenerator;

import javax.swing.*;

public class AddressTab extends JPanel
{
    public AddressTab()
    {
        JButton streetButton = new JButton("Ulica");
        JButton streetAndNumberButton = new JButton("Ulica i numer lokalu");
        JButton cityButton = new JButton("Miasto");

        streetButton.addActionListener(new AppendGeneratorEvent<>(new StreetGenerator()));
        streetAndNumberButton.addActionListener(new AppendGeneratorEvent<>(new StreetAndNumberGenerator()));
        cityButton.addActionListener(new AppendGeneratorEvent<>(new CityGenerator()));

        add(streetButton);
        add(streetAndNumberButton);
        add(cityButton);
    }
}
