package com.jonny.controller;


import com.jonny.model.Employee;
import com.jonny.service.EmployeeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

    @Mock
    private EmployeeService mock;

    @InjectMocks
    private EmployeeController sut;

    private static final int VALID_ID = 1;
    private static final int OVER_ID = 999;

    private static final String DATE_EARLY = "1980-01-01";
    private static final String DATE_OLDER = "2017-01-01";

    private static final Employee VALID_EMP = new Employee(
            VALID_ID,
            "Lname",
            "Mname",
            "Sname",
            Date.valueOf("1999-12-31"),
            9999,
            "Department");
    private static final Employee OVER_ID_EMP = new Employee(
            OVER_ID,
            "Lname",
            "Mname",
            "Sname",
            Date.valueOf("1999-12-31"),
            9999,
            "Department");


    /**
     * --- Testing method: public void create(@RequestBody Employee employee) ---
     */

    @Test
    public void create_validEmployee_NumberOfInvocations1(){
        when(mock.create(VALID_EMP)).thenReturn(1);
        sut.create(VALID_EMP);
        verify(mock, times(1)).create(VALID_EMP);
    }

    /**
     * --- Testing method: public List<Employee> readAll() ---
     */

    @Test
    public void readAll_notEmptyList_ListEmployeeListMoreThatZero() throws Exception {
        List<Employee> list = new ArrayList<>();
        list.add(VALID_EMP);
        when(mock.readAll()).thenReturn(list);
        Assert.assertTrue("Message", sut.readAll().size()>0);
    }

    /**
     * --- Testing method: public void update(@RequestBody Employee employee) ---
     */

    @Test
    public void update_validEmployee_NumberInvocation1() throws Exception {
        when(mock.update(VALID_EMP)).thenReturn(1);
        sut.update(VALID_EMP);
        verify(mock, times(1)).update(VALID_EMP);
    }

    @Test
    public void update_overIdEmployee_NumberInvocation1() throws Exception {
        when(mock.update(OVER_ID_EMP)).thenReturn(0);
        sut.update(OVER_ID_EMP);
        verify(mock, times(1)).update(OVER_ID_EMP);
    }

    /**
     * --- Testing method: public void delete(@RequestBody Employee employee) ---
     */

    @Test
    public void delete_validEmployee_NumberInvocation1() throws Exception {
        when(mock.delete(VALID_ID)).thenReturn(1);
        sut.delete(VALID_ID);

        verify(mock, times(1)).delete(VALID_ID);
    }

    @Test
    public void delete_overIdEmployee_JdbcTemplateThrowException() throws Exception {
        when(mock.delete(OVER_ID)).thenReturn(0);
        sut.delete(OVER_ID);
        verify(mock, times(1)).delete(OVER_ID);
    }

    /**
     * --- Testing method: public Employee read(@PathVariable("id") int id) ---
     */

    @Test
    public void read_validArg_ValidEmployeeByValidId() throws Exception {
        when(mock.readById(VALID_ID)).thenReturn(VALID_EMP);
        Assert.assertEquals("Method has wrong behavior", VALID_EMP, sut.read(VALID_ID));
        verify(mock, times(1)).readById(VALID_ID);
    }

    /**
     * --- Testing method: public List<Employee> findByDateBirthRange(@PathVariable("eDate") Date earlyDate, @PathVariable("oDate") Date olderDate) ---
     */

    @Test
    public void findByDateBirthRange_notEmptyList_ListEmployeeListMoreThatZero() throws Exception {
        List<Employee> list = new ArrayList<>();
        list.add(VALID_EMP);
        when(mock.findByDateRange(DATE_EARLY, DATE_OLDER)).thenReturn(list);
        Assert.assertTrue("Message", sut.findByDateBirthRange(DATE_EARLY, DATE_OLDER).size()>0);
    }
}
