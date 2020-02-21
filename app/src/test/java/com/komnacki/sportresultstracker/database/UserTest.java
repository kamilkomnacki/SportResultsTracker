package com.komnacki.sportresultstracker.database;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {
    private User user = new User();

    @Test
    public void testGetId() throws Exception {
        user.setId(1L);
        Long result = user.getId();
        Assert.assertEquals(Long.valueOf(1), result);
    }

    @Test
    public void testGetName() throws Exception {
        user.setName("Kamil");
        String result = user.getName();
        Assert.assertEquals("Kamil", result);
    }

    @Test
    public void testGetSportsAmount() throws Exception {
        user.setSportsAmount(1L);
        Long result = user.getSportsAmount();
        Assert.assertEquals(Long.valueOf(1), result);
    }

    @Test
    public void testToString() throws Exception {
        user.setId(1L);
        user.setName("Kamil");
        user.setSportsAmount(1L);
        String result = user.toString();
        Assert.assertEquals(
                user.getId().toString() + " " + user.getName() + " " + user.getSportsAmount().toString(), result);
    }
}