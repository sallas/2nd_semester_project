package utility;

import java.sql.Date;
import java.util.Calendar;

public class DateLogic {

    static public Date getCurrentDateInSQLDate() {
        Calendar rightNow = Calendar.getInstance();
        Date today = new Date(rightNow.getTimeInMillis());
        today = Date.valueOf(today.toString());
        return today;
    }

    static public Date addDayToSQLDate(Date date) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.DATE, 1);
        Date newDate = new Date(rightNow.getTimeInMillis());
        return newDate;
    }

    static public Calendar getCurrentDateCalendarDate() {
        Calendar rightNow = Calendar.getInstance();
        Date today = new Date(rightNow.getTimeInMillis());
        today = Date.valueOf(today.toString());
        rightNow.setTime(today);
        return rightNow;
    }

    static public Calendar utilDateToCalendarDate(java.util.Date util) {
        Calendar date = Calendar.getInstance();
        date.setTime(util);
        return date;
    }

    static public Calendar sqlDateToCalendarDate(Date sqlDate) {
        Calendar date = Calendar.getInstance();
        date.setTime(sqlDate);
        return date;
    }

    static public Date addDaysToSQLDate(Date date, int days) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.DATE, days);
        Date newDate = new Date(rightNow.getTimeInMillis());
        return newDate;
    }

}
