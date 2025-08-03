package com.eatwise.eatwise_api.infrastructure.utils;

import java.util.Date;

public class DateUtils {
    
    // Assuming the date format is "dd-MM-yyyy"
    public static Date parseDate(String mealDate) {
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(mealDate, "dd-MM-yyyy");
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format. Expected format: dd-MM-yyyy", e);
        }
    }

    // Format Date to String in "dd-MM-yyyy" format
    public static String formatDate(Date date) {
        return org.apache.commons.lang3.time.DateFormatUtils.format(date, "dd-MM-yyyy");
    }
}
