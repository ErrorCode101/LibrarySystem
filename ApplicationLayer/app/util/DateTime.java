package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTime {

    private int day;
    private int month;
    private int year;

    public DateTime(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }



    public DateTime(){}

    public static DateTime setDateString (String date){
        try {
            String[] dateArray = date.split("/");
            return setDate(Integer.parseInt(dateArray[0]),Integer.parseInt(dateArray[1]),Integer.parseInt(dateArray[2]));

        }catch (Exception ex){
            return null;
        }
    }

    //Date time validation before creating an object
    public static DateTime setDate(int day, int month, int year){
        if(year > 0 && year <=9999){
            if(month>0 && month<=12){
                if(month == 4 || month == 6 || month == 9 || month == 11){
                    if(day>0 && day<=30){
                        return new DateTime(day,month,year);
                    }
                }else if(month ==2){
                    if(year % 4 ==0 || (year % 4 ==0 && (year % 100 != 0))){
                        if(day>0 && day<=29){
                            return new DateTime(day,month,year);
                        }
                    }else{
                        if(day>0 && day<=28){
                            return new DateTime(day,month,year);
                        }
                    }
                }else{
                    if(day>0 && day<=31){
                        return new DateTime(day,month,year);
                    }
                }
            }
        }
        return null;
    }

    public static int getDays(String from, String to) throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = format.parse(from);
        Date date2 = format.parse(to);
        long diff = date2.getTime() - date1.getTime();
        return Math.round(diff/(1000*60*60*24));
    }

    public static DateTime addDays(String setDate, int days) throws ParseException{
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = format.parse(setDate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return new DateTime(cal.get(Calendar.DAY_OF_WEEK),cal.get(Calendar.MONTH),cal.get(Calendar.YEAR));
    }

    public String getDate(){
        return  this.day + "/" + this.month + "/" + this.year ;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


}
