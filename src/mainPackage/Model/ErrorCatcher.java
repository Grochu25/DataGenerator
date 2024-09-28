package mainPackage.Model;

import javax.swing.*;

public class ErrorCatcher
{
    public static void call(String errorContent)
    {
        JOptionPane.showMessageDialog(null, errorContent, "Nastąpił błąd", JOptionPane.ERROR_MESSAGE);
    }
}
