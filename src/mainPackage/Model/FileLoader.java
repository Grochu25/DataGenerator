package mainPackage.Model;

import mainPackage.Main;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class FileLoader
{
    private static JFileChooser chooser;

    public static List<String> loadFromFile(String path)
    {
        ArrayList<String> returnList = new ArrayList<>();
        try {
            String content = Files.readString(Paths.get(path));
            returnList.addAll(Stream.of(content.split("\\n")).map(line->line.substring(0,line.length()-1)).toList());
        } catch (FileNotFoundException e) {
            ErrorCatcher.call("Nie znaleziono pliku "+path);
        } catch (IOException e) {
            ErrorCatcher.call("Błąd odczytu pliku "+path);
        }
        return returnList;
    }

    public static File chooseFileToRead()
    {
        if(chooser == null)
            chooser = getNewChooser();

        if(chooser.showOpenDialog(Main.frame) == JFileChooser.APPROVE_OPTION)
            return chooser.getSelectedFile();

        return null;
    }

    public static FileWriter openToSave()
    {
        if(chooser == null)
            chooser = getNewChooser();

        try {
            if(chooser.showSaveDialog(Main.frame) == JFileChooser.APPROVE_OPTION)
                return new FileWriter(chooser.getSelectedFile());

        } catch (IOException e) {
            ErrorCatcher.call("Nie udało się otworzyć lub stworzyć pliku");
        }

        return null;
    }

    private static JFileChooser getNewChooser()
    {
        JFileChooser chooser = new JFileChooser();
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("Plik sql", "sql", "SQL"));
        chooser.setFileFilter(new FileNameExtensionFilter("Plik tekstowy", "txt"));
        chooser.setCurrentDirectory(new File("./"));
        return chooser;
    }

    public static List<File> getSubfilesOfPath(String path) {
        List<File> subfiles = new ArrayList<>();

        File root = new File(path);
        File[] files = root.listFiles();
        if(files != null)
            subfiles = Arrays.asList(files);

        return subfiles;
    }
}
