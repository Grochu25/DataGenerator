package mainPackage.View;

import mainPackage.Main;
import mainPackage.Model.Generable;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SelectFromListDialog<T> extends JPanel
{
    private JTextArea infoTextArea;
    private JDialog dialog;
    private JTable fieldTable;
    private List<T> elements;

    public SelectFromListDialog(Component owner, List<T> elements)
    {
        this.elements = elements;
        dialog = new JDialog(Main.frame,true);
        fieldTable = createTableWithFields();
        infoTextArea = createInfoTextArea();

        JButton apply = new JButton("Zatwierdź");
        apply.addActionListener(e-> dialog.setVisible(false));

        dialog.add(infoTextArea, BorderLayout.NORTH);
        dialog.add(new JScrollPane(fieldTable), BorderLayout.CENTER);
        dialog.add(apply, BorderLayout.SOUTH);
        dialog.setSize((int) owner.getSize().getWidth()/2, (int) owner.getSize().getHeight()/2);
        dialog.getRootPane().setDefaultButton(apply);
    }

    private JTable createTableWithFields()
    {
        String[] columnHeaders = {"LP.","Pole"};
        String[][] data = new String[elements.size()][2];

        for(int i=0;i<elements.size();i++) {
            data[i][0] = ""+(i+1);
            data[i][1] = elements.get(i).toString();
        }

        return new JTable(data,columnHeaders);
    }

    private JTextArea createInfoTextArea()
    {
        JTextArea textArea = new JTextArea(2, 60);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setMargin(new Insets(5,10,5,10));
        return textArea;
    }

    public int showDialogAndGetFieldIndex(String content, String title)
    {
        fieldTable.clearSelection();
        dialog.setTitle(title);
        infoTextArea.setText(content);
        dialog.setVisible(true);

        return fieldTable.getSelectedRow();
    }
}
