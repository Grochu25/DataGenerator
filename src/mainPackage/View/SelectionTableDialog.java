package mainPackage.View;

import mainPackage.Main;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class SelectionTableDialog<T> extends JPanel
{
    private JTextArea infoTextArea;
    private JDialog dialog;
    private JTable fieldTable;
    private List<T> elements;

    public SelectionTableDialog(Component owner, List<T> elements)
    {
        this.elements = elements;
        dialog = new JDialog(Main.frame,true);
        fieldTable = createTableWithFields(elements);
        infoTextArea = createInfoTextArea();

        JButton apply = new JButton("Zatwierdź");
        apply.addActionListener(e-> {
            dialog.setVisible(false);
        });

        dialog.add(infoTextArea, BorderLayout.NORTH);
        dialog.add(new JScrollPane(fieldTable), BorderLayout.CENTER);
        dialog.add(apply, BorderLayout.SOUTH);
        dialog.setSize((int) owner.getSize().getWidth()/2, (int) owner.getSize().getHeight()/2);
        dialog.getRootPane().setDefaultButton(apply);
    }

    private JTable createTableWithFields(List<T> fields)
    {
        String[] columnHeaders = {"LP.","Pole"};
        String[][] data = new String[fields.size()][2];

        for(int i=0;i<fields.size();i++) {
            data[i][0] = ""+(i+1);
            data[i][1] = fields.get(i).toString();
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

    public T showDialogAndGetField(String content, String title)
    {
        fieldTable.clearSelection();
        dialog.setTitle(title);
        infoTextArea.setText(content);
        dialog.setVisible(true);

        if(fieldTable.getSelectedRow() >= 0)
            return elements.get(fieldTable.getSelectedRow());
        return null;
    }
}
