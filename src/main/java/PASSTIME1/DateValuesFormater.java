package PASSTIME1;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class DateValuesFormater {
    private static DecimalFormat df = new DecimalFormat("0.00",
            DecimalFormatSymbols.getInstance(Locale.US));

    public static String getFormatedDayToDisplay(int numberOfDays){
        if(numberOfDays==1)
            return numberOfDays+" dzień";
        else
            return numberOfDays+" dni";
    }
    public static String getFormatedWeeksToDisplay(double numberOfWeeks){
        return numberOfWeeks % 1 == 0 ? "tygodni " + (int) numberOfWeeks : "tygodni " + df.format(numberOfWeeks);
    }
    public static String getFormatedYearsFromDate(int years){
        if(years==0)
            return "";
        else if(years==1)
            return years+" rok";
        else if(years>=2 && years<=4)
            return  years+" lata";
        else
            return years+ " lat";
    }
    public static String getFormatedMonthsFromDate(int months){
        if(months==0)
            return "";
        else if(months==1)
            return months+" miesiąc";
        else if(months>=2 && months<=4)
            return months+" miesiące";
        else
            return months+" miesięcy";
    }

}
