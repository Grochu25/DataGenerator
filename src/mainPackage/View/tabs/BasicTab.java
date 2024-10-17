package mainPackage.View.tabs;

import mainPackage.Model.AppendGeneratorEvent;
import mainPackage.Model.Generator;
import mainPackage.Model.Generators.OrdinalNumberGenerator;
import mainPackage.Model.Generators.RandomNumberGenerator;

import javax.swing.*;

public class BasicTab extends JPanel
{
    private JSpinner rangeStart;
    private JSpinner rangeEnd;

    public BasicTab()
    {
        JButton ordinalNumberButton = new JButton("Liczba porządkowa");
        ordinalNumberButton.addActionListener(new AppendGeneratorEvent<>(new OrdinalNumberGenerator()));

        JPanel rangePanel = createRangePanel();

        JButton randomNumberButton = new JButton("Liczba losowa");
        randomNumberButton.addActionListener(
                e-> Generator.getInstance().addToGenerables(new RandomNumberGenerator((Integer) rangeStart.getValue(), (Integer) rangeEnd.getValue())));

        add(ordinalNumberButton);
        add(randomNumberButton);
        add(rangePanel);
    }

    private JPanel createRangePanel()
    {
        SpinnerModel min = new SpinnerNumberModel(0, 0, 1000, 1);
        SpinnerModel max = new SpinnerNumberModel(100, 0, 1000, 1);

        rangeStart = new JSpinner(min);
        rangeEnd = new JSpinner(max);
        JPanel rangePanel = new JPanel();
        rangePanel.add(rangeStart);
        rangePanel.add(rangeEnd);
        rangePanel.setBorder(BorderFactory.createTitledBorder("Przedział liczby losowej"));

        return rangePanel;
    }
}
