package com.komnacki.sportresultstracker.database;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ConvertersTest {
    private Converters converters = new Converters();

    @Test
    public void testFromTimestampToDate() throws Exception {
        Date result = converters.fromTimestampToDate(1581188820000L);
        Assert.assertEquals(new GregorianCalendar(2020, Calendar.FEBRUARY, 8, 20, 7).getTime(), result);
    }

    @Test
    public void testFromDateToTimestamp() throws Exception {
        Long result = converters.fromDateToTimestamp(new GregorianCalendar(2020, Calendar.FEBRUARY, 8, 20, 7).getTime());
        Assert.assertEquals(Long.valueOf(1581188820000L), result);
    }
}