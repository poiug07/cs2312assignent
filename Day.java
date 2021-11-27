import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Day implements Cloneable, Comparable<Day> {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    LocalDate date;

    public Day(String sDay) {
        set(sDay);
    }

    public void set(String sDay) {
        date = LocalDate.parse(sDay, formatter);
    }

    @Override
    public String toString() {
        String str = date.format(formatter);
        if(str.charAt(0)=='0')
            return str.substring(1);
        return str;
    }

    @Override
    public Day clone() {
        Day copy = null;
        try {
            copy = (Day) super.clone();
        } catch (CloneNotSupportedException e) {
            // Just to make compiler happy
        }
        return copy;
    }

    public Day plus3Days() {
        return new Day(date.plusDays(3).format(formatter));
    }

    @Override
    public int compareTo(Day other) {
        return this.date.compareTo(other.date);
    }
}
