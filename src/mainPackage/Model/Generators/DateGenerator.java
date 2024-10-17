package mainPackage.Model.Generators;

import mainPackage.Model.Generator;
import mainPackage.Model.Generable;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateGenerator implements Generable<String>
{
    private LocalDate fromDate = LocalDate.of(1900,1,1);
    private LocalDate toDate = LocalDate.now();

    private LocalDate lastGenerated;

    public DateGenerator(LocalDate fromDate, LocalDate toDate)
    {
        if(fromDate.isAfter(toDate)) {
            this.fromDate = toDate;
            this.toDate = fromDate;
        }
        else {
            this.fromDate = fromDate;
            this.toDate = toDate;
        }
    }

    public DateGenerator() {}

    @Override
    public String generate() {
        lastGenerated = fromDate.plusDays(Generator.getInstance().getRandom().nextLong(ChronoUnit.DAYS.between(fromDate, toDate)));
        return lastGenerated.format(Generator.getInstance().getDateTimeFormatter());
    }

    @Override
    public String getLastGenerated() {
        return lastGenerated.format(Generator.getInstance().getDateTimeFormatter());
    }

    @Override
    public String getGeneratorLabel() {
        return "Data "+fromDate+" -> "+toDate;
    }

    @Override
    public void setDependencies() {}

    @Override
    public boolean isDependenceSet() {
        return fromDate != null && toDate != null;
    }

    @Override
    public String toString() {return getGeneratorLabel();}
}
