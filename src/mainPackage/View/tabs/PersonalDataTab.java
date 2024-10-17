package mainPackage.View.tabs;

import mainPackage.Model.AppendGeneratorEvent;
import mainPackage.Model.Generator;
import mainPackage.Model.Generators.*;

import javax.swing.*;

public class PersonalDataTab extends JPanel
{
    public PersonalDataTab()
    {
        JButton nameButton = new JButton("Imię");
        JButton surnameButton = new JButton("Nazwisko");
        JButton genderButton = new JButton("Płeć");
        JButton phoneNumberButton = new JButton("Numer Telefonu");
        JButton emailButton = new JButton("Adres e-mail");
        JButton peselButton = new JButton("PESEL");

        nameButton.addActionListener(new AppendGeneratorEvent<String>(new NameGenerator()));
        surnameButton.addActionListener(new AppendGeneratorEvent<String>(new SurnameGenerator()));
        genderButton.addActionListener(e-> Generator.getInstance().addToGenerables(new GenderGenerator()));
        phoneNumberButton.addActionListener(new AppendGeneratorEvent<Integer>(new PhoneNumberGenerator()));
        emailButton.addActionListener(e-> Generator.getInstance().addToGenerables(new EmailGenerator()));
        peselButton.addActionListener(e-> Generator.getInstance().addToGenerables(new PeselGenerator()));

        add(nameButton);
        add(surnameButton);
        add(genderButton);
        add(phoneNumberButton);
        add(emailButton);
        add(peselButton);
    }
}
