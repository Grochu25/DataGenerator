package mainPackage.Model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileLoader
{
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
}
