package com.jonny.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentTest {

    @Test
    public void equals_skipIdForEquals_true() throws Exception {
        Department dep_one = new Department(10, "DepName");
        Department dep_two = new Department(17, "DepName");

        Assert.assertTrue("Equals method has bad behavior.", dep_one.equals(dep_two));
    }
}
