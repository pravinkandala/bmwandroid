package com.pk.bmwandroid.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;

/**
 * Created by Pravin on 10/24/16.
 * Project: bmwandroid
 */

public class DateUtil {

    public static String getDuration(DateTime end) {
        DateTime start = DateTime.now(DateTimeZone.UTC);
        final int hours = Hours.hoursBetween(new DateTime(start), new DateTime(end)).getHours();
        final int minutes = Minutes.minutesBetween(new DateTime(start), new DateTime(end)).getMinutes() - (hours * 60);
        final int seconds = Seconds.secondsBetween(new DateTime(start), new DateTime(end)).getSeconds() - ((hours * 3600) + (minutes * 60));

        if(hours>0){
            return hours + "hrs " + minutes + "mins " + seconds + "secs";
        }else if(minutes>0){
            return minutes + "mins " + seconds + "secs" ;
        }else{
            return seconds + "sec";
        }
    }


}
