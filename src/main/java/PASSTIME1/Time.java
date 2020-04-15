/**
 *
 *  @author Redzik Mateusz S18819
 *
 */
package PASSTIME1;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Time {
    private static final int DATE_FORMAT_WITH_TIME_LENGTH=16;
    private static  Locale usersLocale = Locale.forLanguageTag("pl-PL");
    private static DateValuesFormater dateValuesFormater;

    public static String passed(String from, String to){
        try {
            return getFullTimeToDisplay(from, to);
        }
        catch (DateTimeParseException ex){
            return "*** "+ex.getMessage();
        }
    }

    private static String getFullTimeToDisplay(String from, String to) {
        if(isDateFormatWithTime(from) && isDateFormatWithTime(to)) {
            return displayDatesFromAndTo(from, to)+displayTime(from,to) + displayTimeBetweenTwoDates(from.split("T")[0], to.split("T")[0]);
        }
        return displayDatesFromAndTo(from, to) + displayTimeBetweenTwoDates(from, to);
    }

    private static String displayTimeBetweenTwoDates(String from, String to) {
        LocalDate dateFrom = makeLocalDateFromString(from);
        LocalDate dateTo = makeLocalDateFromString(to);
        double numbersOfDaysBetween = ChronoUnit.DAYS.between(dateFrom, dateTo);
        double numbersOfWeeksBetween = ChronoUnit.WEEKS.between(dateFrom, dateTo);
        double numbersOfWeeks = numbersOfDaysBetween/7;
        Period period = Period.between(dateFrom, dateTo);
        return String.format("\n- mija: %s, %s \n- kalendarzowo: %s %s %s",
                dateValuesFormater.getFormatedDayToDisplay((int)numbersOfDaysBetween),
                dateValuesFormater.getFormatedWeeksToDisplay(numbersOfWeeks),
                dateValuesFormater.getFormatedYearsFromDate(period.getYears()),
                dateValuesFormater.getFormatedMonthsFromDate(period.getMonths()),
                dateValuesFormater.getFormatedDayToDisplay(period.getDays()));
    }
    private static String displayTime(String timeFrom, String timeTo){
        Instant from = Instant.parse(addMiliseconds(timeFrom));
        Instant to = Instant.parse(addMiliseconds(timeTo));
        long numbersOfHoursBetween = ChronoUnit.HOURS.between(from,to);
        long numbersOfMinutesBetween = ChronoUnit.MINUTES.between(from,to);
        return String.format("\n- godzin: %d, minut: %d",numbersOfHoursBetween,numbersOfMinutesBetween);
    }

    private static String displayDatesFromAndTo(String from, String to){
        String dateFrom = getDateWithMonthAsStringAndDayName(from);
        String dateTo = getDateWithMonthAsStringAndDayName(to);

        if(isDateFormatWithTime(from)&&isDateFormatWithTime(to)) {
            String timeFrom = getTimeAsStringToDisplay(from);
            String timeTo = getTimeAsStringToDisplay(to);
            return "od " + dateFrom + timeFrom + " do "+ dateTo + timeTo;
        }
        return String.format("od %s do %s", dateFrom, dateTo);
    }
    private static String getTimeAsStringToDisplay(String date){
        String timeFromDate = date.split("T")[1];
        return new StringBuilder().append(" godz. ").append(timeFromDate).toString();
    }
    private static String getDateWithMonthAsStringAndDayName(String date) {
        String separatedyDate = date.split("T")[0];
        LocalDate localDate = LocalDate.parse(separatedyDate);
        return localDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy (EEEE)",usersLocale));
    }

    private static boolean isDateFormatWithTime(String string){
        return string.length()==DATE_FORMAT_WITH_TIME_LENGTH?true:false;
    }
    private static String addMiliseconds(String dateWithTime){
        return dateWithTime+":00Z";
    }

    public static LocalDate makeLocalDateFromString(String date){
        return LocalDate.parse(date);
    }
}
