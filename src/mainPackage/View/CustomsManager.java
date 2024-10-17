package mainPackage.View;

import mainPackage.Main;
import mainPackage.Model.ErrorCatcher;
import mainPackage.Model.FileLoader;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class CustomsManager
{
    public static void copyToSources(File file)
    {
        String name = getNameFromUser();

        File newCustomFile = new File("./sources/customs/"+name+".txt");
        if(newCustomFile.exists())
            ErrorCatcher.call("Pole o tej nazwie już istnieje");
        else{
            try {
                Files.copy(file.toPath(), newCustomFile.toPath());
            } catch (IOException e) {
                ErrorCatcher.call("Nie udało się dodać pola");
            }
        }
    }

    private static String getNameFromUser()
    {
        return (String) JOptionPane.showInputDialog(Main.frame,"Podaj nazwę dla nowego pola: ",
                "dodawanie pola",JOptionPane.PLAIN_MESSAGE,null,null,null);
    }

    public static void deleteFromCustoms()
    {
        List<String> files = FileLoader.getSubfilesOfPath("./sources/customs").stream().map(File::getName).toList();
        SelectionTableDialog<String> tableDialog = new SelectionTableDialog<>(Main.frame, files);
        String fileToDeleteName = tableDialog.showDialogAndGetField("Wybierz polę do usunięcia", "Usuń polę");
        File fileToDelete = new File("./sources/customs/"+fileToDeleteName);

        if(fileToDelete.exists())
            if(!fileToDelete.delete())
                ErrorCatcher.call("Usunięcie pola nie powiodło się");
    }
}
