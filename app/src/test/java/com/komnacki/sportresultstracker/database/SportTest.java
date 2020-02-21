package com.komnacki.sportresultstracker.database;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SportTest {
    private Sport sport = new Sport();

    @Before
    public void setUp() {
    }

    @Test
    public void testGetId() throws Exception {
        sport.setId(1L);
        Long result = sport.getId();
        Assert.assertEquals(Long.valueOf(1), result);
    }

    @Test
    public void testGetUser_id() throws Exception {
        sport.setUser_id(1L);
        Long result = sport.getUser_id();
        Assert.assertEquals(Long.valueOf(1), result);
    }

    @Test
    public void testGetName() throws Exception {
        sport.setName("Kamil");
        String result = sport.getName();
        Assert.assertEquals("Kamil", result);
    }

    @Test
    public void testSetHasTime() throws Exception {
        sport.setHasTime(Boolean.TRUE);
        Assert.assertEquals(true, sport.getHasTime());
    }

    @Test
    public void testGetHasDistance() throws Exception {
        sport.setHasDistance(true);
        Boolean result = sport.getHasDistance();
        Assert.assertEquals(Boolean.TRUE, result);
    }

    @Test
    public void testGetHasRepeat() throws Exception {
        sport.setHasRepeat(true);
        Boolean result = sport.getHasRepeat();
        Assert.assertEquals(Boolean.TRUE, result);
    }

    @Test
    public void testGetHasWeight() throws Exception {
        sport.setHasWeight(true);
        Boolean result = sport.getHasWeight();
        Assert.assertEquals(Boolean.TRUE, result);
    }

    @Test
    public void testGetRecordsAmount() throws Exception {
        sport.setRecordsAmount(1L);
        Long result = sport.getRecordsAmount();
        Assert.assertEquals(Long.valueOf(1), result);
    }
}