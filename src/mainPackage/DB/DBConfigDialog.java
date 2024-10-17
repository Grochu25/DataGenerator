package mainPackage.DB;

import mainPackage.Main;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class DBConfigDialog extends JPanel
{
    private static DBConfigDialog instance;
    public static DBConfigDialog getInstance(){
        if(instance == null)
            instance = new DBConfigDialog();
        return instance;
    }

    private JButton accept;
    private JButton cancel;
    private JTextField address;
    private JTextField port;
    private JTextField user;
    private JPasswordField password;
    private JTextField baseName;
    private Properties props;
    private JDialog dialog;

    private DBConfigDialog()
    {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        initializeFields();

        JPanel inputs = setupInputPanel();
        add(inputs, BorderLayout.CENTER);

        JPanel buttons = setupButtonPanel();
        add(buttons, BorderLayout.SOUTH);

        dialog = new JDialog(Main.frame, true);
        dialog.add(this);
        dialog.setTitle("Dane Logowania");
        dialog.pack();
        dialog.setResizable(false);
        dialog.getRootPane().setDefaultButton(accept);
    }

    private void initializeFields()
    {
        address = new JTextField();
        port = new JTextField();
        user = new JTextField();
        password = new JPasswordField();
        baseName = new JTextField();
    }

    private JPanel setupInputPanel()
    {
        JPanel inputs = new JPanel();
        inputs.setLayout(new GridLayout(5,2,2,2));
        inputs.add(new JLabel("adres bazy: "));
        inputs.add(address);
        inputs.add(new JLabel("potr: "));
        inputs.add(port);
        inputs.add(new JLabel("nazwa użytkownika: "));
        inputs.add(user);
        inputs.add(new JLabel("hasło: "));
        inputs.add(password);
        inputs.add(new JLabel("nazwa bazy danych: "));
        inputs.add(baseName);
        return inputs;
    }

    private JPanel setupButtonPanel()
    {
        JPanel buttons = new JPanel();
        buttons.setLayout(new FlowLayout());
        accept = new JButton("Zatwierdz");
        accept.addActionListener(event->{saveChanges(); dialog.setVisible(false);});
        cancel = new JButton("Anuluj");
        cancel.addActionListener(event->{dialog.setVisible(false);});
        buttons.add(accept);
        buttons.add(cancel);
        return buttons;
    }

    private void fillFields()
    {
        props = new Properties();
        try(InputStream in = Files.newInputStream(Paths.get("db.properties")))
        {
            props.load(in);
        }catch(IOException e){e.printStackTrace();}

        address.setText(props.getProperty("jdbc.address", "localhost"));
        port.setText(props.getProperty("jdbc.port", "3306"));
        user.setText(props.getProperty("jdbc.user", "root"));
        password.setText(props.getProperty("jdbc.password", ""));
        baseName.setText(props.getProperty("jdbc.dbname","mysql"));
    }

    private void saveChanges()
    {
        props.setProperty("jdbc.address", address.getText());
        props.setProperty("jdbc.port", port.getText());
        props.setProperty("jdbc.user", user.getText());
        props.setProperty("jdbc.password", new String(password.getPassword()));
        props.setProperty("jdbc.dbname", baseName.getText());
        props.setProperty("jdbc.url", "jdbc:mysql://"+address.getText()+":"+port.getText()+"/"+baseName.getText());

        try {
            props.store(Files.newOutputStream(Paths.get("db.properties")),"Database configuration");
        }catch(IOException e) {e.printStackTrace();}
    }

    public void showDialog()
    {
        fillFields();
        dialog.setVisible(true);
    }
}
