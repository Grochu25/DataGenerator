package mainPackage;

import mainPackage.Model.Generators.*;
import mainPackage.View.MainFrame;

import java.awt.*;
import javax.swing.*;

public class Main {
    public static void main(String[] args)
    {
        DateGenerator dg = new DateGenerator();

        System.out.println(dg.generate());

        EventQueue.invokeLater(()->
        {
            JFrame frame = new MainFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}