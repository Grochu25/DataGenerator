package mainPackage.View.tabs;

import mainPackage.Model.Generator;
import mainPackage.Model.Generators.DateGenerator;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateTab extends JPanel
{
    public DateTab()
    {
        JSpinner dateFrom = new JSpinner(new SpinnerDateModel());
        JSpinner dateTo = new JSpinner(new SpinnerDateModel());
        dateFrom.setEditor(new JSpinner.DateEditor(dateFrom, "dd.MM.yyyy"));
        dateTo.setEditor(new JSpinner.DateEditor(dateTo, "dd.MM.yyyy"));

        Font font = dateFrom.getEditor().getFont();
        Font n = new Font(font.getName(), font.getStyle(), (int) (font.getSize()*1.5));
        dateFrom.getEditor().getComponent(0).setFont(n);
        dateTo.getEditor().getComponent(0).setFont(n);

        JButton addDateButton = new JButton("Dodaj datę");
        addDateButton.addActionListener(
                e->{
                    LocalDate from = LocalDate.ofInstant(((Date)dateFrom.getValue()).toInstant(), ZoneId.systemDefault());
                    LocalDate to = LocalDate.ofInstant(((Date)dateTo.getValue()).toInstant(), ZoneId.systemDefault());
                    Generator.getInstance().addToGenerables(
                            new DateGenerator(from, to));
                });

        add(dateFrom);
        add(dateTo);
        add(addDateButton);
    }
}
