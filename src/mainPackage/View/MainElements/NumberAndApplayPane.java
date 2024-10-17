package mainPackage.View.MainElements;

import mainPackage.Model.Generator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberAndApplayPane extends JPanel implements ActionListener
{
    private JSpinner numberSpinner;
    private JButton generateButton;
    private JComboBox<Character> dataSeparatorComboBox;
    private JCheckBox bracketsCheckbox;
    private JCheckBox quotationMarksCheckbox;

    public NumberAndApplayPane()
    {
        setLayout(new GridLayout(2,1));

        createControls();

        addControls();
    }

    private void createControls()
    {
        numberSpinner = new JSpinner(new SpinnerNumberModel(1,1,100000,1));
        generateButton = new JButton("Generuj");
        dataSeparatorComboBox = new JComboBox<>(new Character[]{',', ';'});
        bracketsCheckbox = new JCheckBox("nawiasy brzeżne");
        quotationMarksCheckbox = new JCheckBox("cudzysłowia wokół tekstu");

        bracketsCheckbox.setSelected(true);
        quotationMarksCheckbox.setSelected(true);

        dataSeparatorComboBox.addActionListener(e->Generator.getInstance().setSeparator((Character) dataSeparatorComboBox.getSelectedItem()));
        bracketsCheckbox.addActionListener(e->Generator.getInstance().setBrackets(bracketsCheckbox.isSelected()));
        quotationMarksCheckbox.addActionListener(e->Generator.getInstance().setQuotation(quotationMarksCheckbox.isSelected()));
        generateButton.addActionListener(e->
                Generator.getInstance().Generate((int) numberSpinner.getValue()));
    }

    private void addControls()
    {
        JPanel flowPanel = new JPanel();
        flowPanel.setLayout(new FlowLayout());
        flowPanel.add(new JLabel("Separator danych: "));
        flowPanel.add(dataSeparatorComboBox);
        flowPanel.add(quotationMarksCheckbox);
        flowPanel.add(bracketsCheckbox);

        add(flowPanel);

        flowPanel = new JPanel();
        flowPanel.setLayout(new FlowLayout());
        flowPanel.add(numberSpinner);
        flowPanel.add(generateButton);

        add(flowPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof Generator.SaveMode)
        {
            Generator.SaveMode mode = (Generator.SaveMode) e.getSource();
            dataSeparatorComboBox.setSelectedItem(Generator.getInstance().getSeparator());
            bracketsCheckbox.setSelected(Generator.getInstance().getBrackets());
            quotationMarksCheckbox.setSelected(Generator.getInstance().getQuotation());

            if(mode == Generator.SaveMode.DataBase) {
                dataSeparatorComboBox.setEnabled(false);
                bracketsCheckbox.setEnabled(false);
                quotationMarksCheckbox.setEnabled(false);
            }
            else{
                dataSeparatorComboBox.setEnabled(true);
                bracketsCheckbox.setEnabled(true);
                quotationMarksCheckbox.setEnabled(true);
            }
        }
        validate();
        repaint();
    }
}
