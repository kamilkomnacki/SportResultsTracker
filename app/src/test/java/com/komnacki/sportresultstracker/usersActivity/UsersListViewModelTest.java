package com.komnacki.sportresultstracker.usersActivity;

import android.arch.lifecycle.LiveData;

import com.komnacki.sportresultstracker.App;
import com.komnacki.sportresultstracker.database.User;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

public class UsersListViewModelTest {
    //Field userRepository of type UserRepository - was not mocked since Mockito doesn't mock a Final class when 'mock-maker-inline' option is not set
    @Mock
    LiveData<List<User>> listOfUsers;
    @Mock
    App mApplication = new App();


    @InjectMocks
    UsersListViewModel usersListViewModel = new UsersListViewModel(mApplication);

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInsert() throws Exception {
        usersListViewModel.insert(new User());
    }

    @Test
    public void testGetUserList() throws Exception {
        LiveData<List<User>> result = usersListViewModel.getUserList();
        Assert.assertEquals(null, result);
    }

    @Test
    public void testUpdate() throws Exception {
        usersListViewModel.update(new User());
    }

    @Test
    public void testGetUser() throws Exception {
        User result = usersListViewModel.getUser(Long.valueOf(1));
        Assert.assertEquals(new User(), result);
    }

    @Test
    public void testIsNameExist() throws Exception {
        boolean result = usersListViewModel.isNameExist("name");
        Assert.assertEquals(true, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme