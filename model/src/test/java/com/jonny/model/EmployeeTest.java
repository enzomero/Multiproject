package com.jonny.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Date;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeTest {
    @Test
    public void equals_skipIdForEquals_true() throws Exception {
        Employee emp_one = new Employee(10, "DepName", "", "", Date.valueOf("1999-06-30"), 1000, "department" );
        Employee emp_two = new Employee(19, "DepName", "", "", Date.valueOf("1999-06-30"), 1000, "department" );

        Assert.assertTrue("Equals method has bad behavior.", emp_one.equals(emp_two));
    }
}
