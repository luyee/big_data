package com.caiw.log4j;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CreateData {
    public static void main(String[] args) {
        Date date= new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,3);

        System.out.println(calendar.getTime());
    }
}
