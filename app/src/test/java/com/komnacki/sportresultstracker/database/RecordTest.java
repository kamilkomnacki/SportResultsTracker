package com.komnacki.sportresultstracker.database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class RecordTest {
    @Mock
    Date date;
    @InjectMocks
    Record record;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetId() throws Exception {
        record.setId(1L);
        Long result = record.getId();
        Assert.assertEquals(Long.valueOf(1), result);
    }

    @Test
    public void testGetSport_id() throws Exception {
        record.setSport_id(1L);
        Long result = record.getSport_id();
        Assert.assertEquals(Long.valueOf(1), result);
    }

    @Test
    public void testGetDate() throws Exception {
        record.setDate(new GregorianCalendar(2020, Calendar.FEBRUARY, 1, 18, 44).getTime());
        Date result = record.getDate();
        Assert.assertEquals(new GregorianCalendar(2020, Calendar.FEBRUARY, 1, 18, 44).getTime(), result);
    }

    @Test
    public void testGetDistance() throws Exception {
        record.setDistance(1L);
        Long result = record.getDistance();
        Assert.assertEquals(Long.valueOf(1), result);
    }

    @Test
    public void testGetTime() throws Exception {
        record.setTime(1L);
        Long result = record.getTime();
        Assert.assertEquals(Long.valueOf(1), result);
    }

    @Test
    public void testGetRepeat() throws Exception {
        record.setRepeat(1L);
        Long result = record.getRepeat();
        Assert.assertEquals(Long.valueOf(1), result);
    }

    @Test
    public void testGetWeight() throws Exception {
        record.setWeight(1L);
        Long result = record.getWeight();
        Assert.assertEquals(Long.valueOf(1), result);
    }
}