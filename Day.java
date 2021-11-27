import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Day implements Cloneable, Comparable<Day> {

    private static final String MonthNames = "JanFebMarAprMayJunJulAugSepOctNovDec";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    private int year;
    private int month;
    private int day;

    public Day(String sDay) {
        set(sDay);
    }

    public void set(String sDay) {
        String[] sDayPart = sDay.split("-");
        this.year = Integer.parseInt(sDayPart[2]);
        this.month = MonthNames.indexOf(sDayPart[1]) / 3 + 1;
        this.day = Integer.parseInt(sDayPart[0]);
    }

    @Override
    public String toString() {
        return day + "-" + MonthNames.substring(3 * (month - 1), 3 * month) + "-" + year; // (month-1)*3,(month)*3
    }

    @Override
    public Day clone() {
        Day copy = null;
        try {
            copy = (Day) super.clone();
        } catch (CloneNotSupportedException e) {
            // Just to make compiler happy
            e.printStackTrace();
        }
        return copy;
    }

    public Day plus3Days() {
        LocalDate d = LocalDate.parse(String.format("%4d-%02d-%02d", year, month, day));
        return new Day(d.plusDays(3).format(formatter));
    }

    @Override
    public int compareTo(Day other) {
        if(this.year!=other.year){
            return this.year - other.year;
        } else if (this.month != other.month){
            return this.month - other.month;
        } else if (this.day != other.month) {
            return this.day - other.day;
        }
        return 0;
    }
}
