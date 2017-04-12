package com.jonny.service;


import com.jonny.dao.EmployeeDao;
import com.jonny.exception.InvalidParameterException;
import com.jonny.model.Employee;
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
public class EmployeeServiceTest {
    @Mock
    private EmployeeDao mock;

    @InjectMocks
    private EmployeeService sut;

    private static final int VALID_ID = 1;
    private static final int ZERO_ID = 0;
    private static final int OVER_ID = 999;

    private static final String DATE_EARLY = "1980-01-01";
    private static final String DATE_OLDER = "2017-01-01";

    private static final Employee NULL_EMP = null;
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
    private static final Employee INVALID_EMP = new Employee(
            VALID_ID,
            "Lname",
            "Mname",
            "",
            Date.valueOf("1999-12-31"),
            9999,
            "");
    private static final Employee ZERO_ID_EMP = new Employee(
            ZERO_ID,
            "Lname",
            "Mname",
            "Sname",
            Date.valueOf("1999-12-31"),
            9999,
            "Department"
    );

    /**
     * --- Testing method: public int create(Employee employee) ---
     */

    @Test
    public void create_validArg_Int1() throws Exception {
        when(mock.create(VALID_EMP)).thenReturn(1);
        Assert.assertEquals("Method for creating employee has wrong behavior.",
                1, sut.create(VALID_EMP));
        verify(mock, times(1)).create(VALID_EMP);
    }

    @Test(expected = InvalidParameterException.class)
    public void create_nullEmployee_InvalidParameterException() throws Exception {
        sut.create(NULL_EMP);
    }

    @Test(expected = InvalidParameterException.class)
    public void create_invalidEmployee_InvalidParameterException() throws Exception {
        sut.create(INVALID_EMP);
    }

    /**
     * --- Testing method: public Employee readById(int id) ---
     */

    @Test
    public void readById_validId_ValidEmployee() throws Exception {
        when(mock.readByID(VALID_ID)).thenReturn(VALID_EMP);
        Assert.assertEquals("Method for reading department by id="+VALID_ID+" has wrong behavior.",
                VALID_EMP, sut.readById(VALID_ID));
        verify(mock, times(1)).readByID(VALID_ID);
    }

    @Test(expected = InvalidParameterException.class)
    public void readById_zeroId_InvalidParameterException() throws Exception {
        sut.readById(ZERO_ID);
    }

    /**
     * --- Testing method: public int update(Employee employee) ---
     */

    @Test
    public void update_validEmployee_Int1() throws Exception {
        when(mock.update(VALID_EMP)).thenReturn(1);
        Assert.assertEquals("Method for updating employee has wrong behavior.",
                1, sut.update(VALID_EMP));
        verify(mock, times(1)).update(VALID_EMP);
    }

    @Test
    public void update_overIdEmployee_Int0() throws Exception {
        when(mock.update(OVER_ID_EMP)).thenReturn(0);
        Assert.assertEquals("Method for updating employee has wrong behavior.",
                0, sut.update(OVER_ID_EMP));
        verify(mock, times(1)).update(OVER_ID_EMP);
    }

    @Test(expected = InvalidParameterException.class)
    public void update_zeroIdEmployee_InvalidParameterException() throws Exception {
        sut.update(ZERO_ID_EMP);
    }

    @Test(expected = InvalidParameterException.class)
    public void update_nullEmployee_InvalidParameterException() throws Exception {
        sut.update(NULL_EMP);
    }

    @Test(expected = InvalidParameterException.class)
    public void update_invalidEmployee_InvalidParameterException() throws Exception {
        sut.update(INVALID_EMP);
    }

    /**
     * --- Testing method: public int delete(Employee employee) ---
     */

    @Test
    public void delete_validEmployee_Int1() throws Exception {
        when(mock.delete(VALID_ID)).thenReturn(1);
        Assert.assertEquals("Method for delete employee has wrong behavior.",
                1, sut.delete(VALID_ID));
        verify(mock, times(1)).delete(VALID_ID);
    }

    @Test
    public void delete_overIdEmployee_Int1() throws Exception {
        when(mock.delete(OVER_ID)).thenReturn(0);
        Assert.assertEquals("Method for delete employee has wrong behavior.",
                0, sut.delete(OVER_ID));
        verify(mock, times(1)).delete(OVER_ID);
    }

    @Test(expected = InvalidParameterException.class)
    public void delete_zeroIdEmployee_InvalidParameterException() throws Exception {
        sut.delete(ZERO_ID);
    }


    /**
     * --- Testing method: public List<Employee> readAll() ---
     */

    @Test
    public void readAll_notEmptyList_listEmployeesListMoreThatZero() throws Exception {
        List<Employee> list = new ArrayList<>();
        list.add(VALID_EMP);
        when(mock.getAll()).thenReturn(list);
        Assert.assertTrue("Method has to return size bigger of zero, but he didn't.",
                sut.readAll().size() > 0);
        verify(mock, times(1)).getAll();
    }

    /**
     * --- Testing method: List<Employee> findByDateRange(Date earlyDate, Date olderDate) ---
     */

    @Test
    public void findByDateRange_validDates_listEmployeesListMoreThatZero() throws Exception {
        List<Employee> list = new ArrayList<>();
        list.add(VALID_EMP);
        when(mock.findByDateRange(Date.valueOf(DATE_EARLY), Date.valueOf(DATE_OLDER))).thenReturn(list);
        Assert.assertTrue("Method has to return size bigger of zero, but he didn't.",
                sut.findByDateRange(DATE_EARLY, DATE_OLDER).size() > 0);
        verify(mock, times(1)).findByDateRange(Date.valueOf(DATE_EARLY), Date.valueOf(DATE_OLDER));
    }
}
