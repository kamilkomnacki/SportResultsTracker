package com.komnacki.sportresultstracker;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class EmptyListObserverTest {
    @Mock
    RecyclerView recyclerView;
    @Mock
    View emptyView;
    @InjectMocks
    EmptyListObserver emptyListObserver;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testOnChanged() throws Exception {
        emptyListObserver.onChanged();
    }

    @Test
    public void testOnItemRangeInserted() throws Exception {
        emptyListObserver.onItemRangeInserted(0, 0);
    }

    @Test
    public void testOnItemRangeRemoved() throws Exception {
        emptyListObserver.onItemRangeRemoved(0, 0);
    }
}