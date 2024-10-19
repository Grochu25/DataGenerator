package mainPackage.Model;

import mainPackage.DB.DBConnection;
import mainPackage.DB.DBTableChooser;
import mainPackage.Model.Generators.EmailGenerator;
import mainPackage.Model.Generators.GenderGenerator;
import mainPackage.Model.Generators.OrdinalNumberGenerator;
import mainPackage.Model.Generators.PeselGenerator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Generator
{
    public enum SaveMode {File, DataBase}
    private static Generator instance;

    private final Random random;
    private final ArrayList<Generable<?>> generables;
    private String dateTimePattern = "dd.MM.yyyy";
    private DateTimeFormatter dateTimeFormatter;
    private ActionListener listener;
    private ActionListener saveModeListener;
    private boolean quotation = true;
    private boolean brackets = true;
    private char separator = ',';
    private SaveMode saveMode = SaveMode.File;
    private boolean dataBaseOverride = false;

    public static Generator getInstance()
    {
        if(instance == null)
            instance = new Generator();

        return instance;
    }

    private Generator()
    {
        random = new Random();
        generables = new ArrayList<>();
        dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimePattern);
    }

    public void Generate(int number)
    {
        if(resetOrdinalNumbers() < 0)
            return;

        ArrayDeque<GenerationOrderPair> generableQueue = orderGenerables();
        String[] line = new String[generables.size()];
        StringBuilder sBuilder = new StringBuilder();

        for(int i=0; i<number; i++) {
            sBuilder.append((brackets)?"(":"");

            for(GenerationOrderPair pair : generableQueue)
                line[pair.order] = generateSingle(pair.generable);

            for(String piece : line)
                sBuilder.append(piece);

            sBuilder.append((brackets)?")":"");
            sBuilder.append((i<number-1) ? separator+"\n" : ";\n");
        }

        if(saveMode == SaveMode.DataBase)
            generateToDataBase(sBuilder.toString());
        else
            generateToFile(sBuilder.toString());
    }

    private int resetOrdinalNumbers()
    {
        int number = 0;
        if(saveMode == SaveMode.DataBase)
            number = prepareDatabase();

        for(Generable<?> generabe : generables)
            if(generabe instanceof OrdinalNumberGenerator)
                ((OrdinalNumberGenerator) generabe).set(number);

        return number;
    }

    private ArrayDeque<GenerationOrderPair> orderGenerables()
    {
        ArrayDeque<GenerationOrderPair> deque = new ArrayDeque<>();
        for(int i=0; i<generables.size(); i++){
            if(generables.get(i) instanceof EmailGenerator || generables.get(i) instanceof PeselGenerator || generables.get(i) instanceof GenderGenerator)
                deque.addLast(new GenerationOrderPair(i, generables.get(i)));
            else
                deque.addFirst(new GenerationOrderPair(i, generables.get(i)));
        }

        return deque;
    }

    private void generateToDataBase(String data)
    {
        try(Statement stat  = DBConnection.getInstance().getConnection().createStatement())
        {
            stat.executeUpdate("INSERT INTO "+ DBTableChooser.getInstance().getBaseName()+" VALUES "+data);
        }
        catch (SQLException e) {ErrorCatcher.call("Błąd SQL: "+e.getMessage());}
        catch (IOException e) {ErrorCatcher.call("Błąd odczytu parametrów połączenia do bazy");}
    }

    private int prepareDatabase(){
        try(Statement stat  = DBConnection.getInstance().getConnection().createStatement())
        {
            if(DBTableChooser.getInstance().showDialog()) {
                if (dataBaseOverride) {
                    stat.execute("TRUNCATE TABLE " + DBTableChooser.getInstance().getBaseName());
                }
                else
                    return ordinalNumberFromDB(stat);
            }
        }
        catch (SQLException e) { ErrorCatcher.call("Błąd SQL: "+e.getMessage());}
        catch (IOException e) {ErrorCatcher.call("Błąd odczytu parametrów połączenia do bazy");}
        return -1;
    }

    private int ordinalNumberFromDB(Statement stat) throws SQLException {
        ResultSet rs = stat.executeQuery("SELECT " + DBTableChooser.getInstance().getID() + " FROM " + DBTableChooser.getInstance().getBaseName() + " ORDER BY " + DBTableChooser.getInstance().getID() + " DESC LIMIT 1");
        rs.next();
        return rs.getInt(1);
    }

    private void generateToFile(String data)
    {
        try(FileWriter file = FileLoader.openToSave()){
            if(file != null)
                file.write(data);
        } catch (IOException e) {ErrorCatcher.call("Zapisywanie danych nie powiodło się");}
    }

    private String generateSingle(Generable<?> generable)
    {
        String singleGenerated = "";

        var generationResult = generable.generate();
        if(generationResult instanceof String && quotation)
            singleGenerated += String.format("%c%s%c",'\"',generationResult,'\"');
        else
            singleGenerated += generationResult.toString();

        if(generable != generables.get(generables.size()-1))
            singleGenerated += separator;

        return  singleGenerated;
    }

    public void addToGenerables(Generable<?> generable)
    {
        generables.add(generable);
        listener.actionPerformed(new ActionEvent(generable,0 , generable.getGeneratorLabel()));
    }

    public void removeFromGenerables(Generable<?> generable)
    {
        generables.remove(generable);
    }

    public void addActionListener(ActionListener actionListener)
    {
        instance.listener = actionListener;
    }

    public void addSaveModeListener(ActionListener actionListener)
    {
        instance.saveModeListener = actionListener;
    }

    public Random getRandom() {return random;}
    public DateTimeFormatter getDateTimeFormatter() {return dateTimeFormatter;}
    public String getDateTimePattern() {return dateTimePattern;}
    public List<Generable<?>> getGenerablesList() {return generables;}
    public boolean getQuotation(){ return quotation;}
    public boolean getBrackets(){ return brackets;}
    public char getSeparator(){ return separator;}

    public void setQuotation(boolean quotation){ this.quotation = quotation;}
    public void setBrackets(boolean brackets){ this.brackets = brackets;}
    public void setSeparator(char separator){ this.separator = separator;}
    public void setSaveMode(SaveMode saveMode){ this.saveMode = saveMode; saveModeListener.actionPerformed(new ActionEvent(this.saveMode,0,""));}
    public void setDataBaseOverride(boolean override){ this.dataBaseOverride = override;}
}
