package mainPackage.DB;

import mainPackage.Main;
import mainPackage.Model.ErrorCatcher;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTableChooser extends JPanel
{
    private static DBTableChooser instance;
    public static DBTableChooser getInstance(){
        if(instance == null)
            instance = new DBTableChooser();
        return instance;
    }

    private JButton accept;
    private String name;
    private String field;
    private JComboBox<String> cols;
    private JComboBox<String> tables;
    private JDialog dialog;
    private boolean approved = false;

    private DBTableChooser()
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        GridBagConstraints gbCons = new GridBagConstraints();
        gbCons.insets = new Insets(20,3,5,20);
        gbCons.anchor = GridBagConstraints.EAST;

        JPanel options = createOptionsPanel(gbCons);
        JPanel buttonP = createButtonPanel();
        JPanel topText = setInfoPanel(gbCons);

        add(topText, BorderLayout.NORTH);
        add(options);
        add(buttonP, BorderLayout.SOUTH);

        createDialog();
        readTablesFromDataBase();
    }

    private JPanel createOptionsPanel(GridBagConstraints gbCons)
    {
        JPanel options = new JPanel();
        options.setLayout(new GridBagLayout());

        tables = new JComboBox<>();
        tables.addActionListener(event->{name = (String)tables.getSelectedItem();showFields();});
        cols = new JComboBox<>();
        cols.setEnabled(false);
        cols.addActionListener(event->field = (String)cols.getSelectedItem());

        options.add(new JLabel("Nazwa tabeli: "), gbCons); gbCons.gridy = 1; gbCons.insets = new Insets(5,3,20,20);
        options.add(new JLabel("Klucz główny (liczba): "), gbCons); gbCons.gridx = 1; gbCons.gridy = 0; gbCons.insets = new Insets(20,20,5,3); gbCons.anchor = GridBagConstraints.WEST;
        options.add(tables, gbCons); gbCons.gridy = 1; gbCons.insets = new Insets(5,20,20,3);
        options.add(cols, gbCons);

        return options;
    }

    private JPanel createButtonPanel()
    {
        JPanel buttonP = new JPanel();
        accept = new JButton("OK");
        accept.addActionListener(e->{dialog.setVisible(false); approved = true;});
        JButton cancel = new JButton("Anuluj");
        cancel.addActionListener(e->dialog.setVisible(false));
        buttonP.add(accept);
        buttonP.add(cancel);
        buttonP.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));

        return buttonP;
    }

    private JPanel setInfoPanel(GridBagConstraints gbCons)
    {
        JPanel topText = new JPanel();
        topText.setLayout(new GridBagLayout()); gbCons = new GridBagConstraints(); gbCons.anchor=GridBagConstraints.CENTER;
        topText.add(new JLabel("Bazę adnych należy ustawić w zakładce"),gbCons);gbCons.gridy=1;
        topText.add(new JLabel("\"Baza Danych\">\"Ustawienia Bazy Danych\""),gbCons);
        topText.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        return topText;
    }

    private void createDialog()
    {
        dialog = new JDialog(Main.frame, true);
        dialog.add(this);
        dialog.setTitle("Określ tabelę");
        dialog.pack();
        dialog.getRootPane().setDefaultButton(accept);
        dialog.setResizable(false);
    }

    private void readTablesFromDataBase()
    {
        try(Statement stat = DBConnection.getInstance().getConnection().createStatement())
        {
            ResultSet rs = stat.executeQuery("SHOW TABLES;");
            while(rs.next())
            {
                tables.addItem(rs.getString(1));
            }
        }catch(SQLException e) {ErrorCatcher.call("Błąd SQL: "+e.getMessage());
        }catch(IOException e) { ErrorCatcher.call("Błąd odczytu danych połączenia z bazą");}
    }

    private void showFields()
    {
        try(Statement stat = DBConnection.getInstance().getConnection().createStatement())
        {
            ResultSet rs = stat.executeQuery("DESC "+name);

            cols.removeAllItems();
            while(rs.next())
                cols.addItem(rs.getString(1));

            EventQueue.invokeLater(this::validate);
        }catch(SQLException e) {ErrorCatcher.call("Błąd SQL: "+e.getMessage());
        }catch(IOException e) {ErrorCatcher.call("Błąd odczytu danych połączenia z bazą");}

        if(cols.getItemCount()>0)
           cols.setEnabled(true);
    }

    public String getBaseName()
    {
        return name;
    }

    public String getID()
    {
        return field;
    }

    public boolean showDialog()
    {
        dialog.setVisible(true);
        return approved;
    }
}
