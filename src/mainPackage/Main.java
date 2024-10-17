package mainPackage;

import mainPackage.Model.Generators.*;
import mainPackage.View.MainFrame;

import java.awt.*;
import javax.swing.*;

public class Main {
    public static JFrame frame;

    public static void main(String[] args)
    {
        EventQueue.invokeLater(()->
        {
            frame = new MainFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            frame.setVisible(true);
        });
    }
}