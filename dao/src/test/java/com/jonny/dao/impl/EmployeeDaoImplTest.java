package com.jonny.dao.impl;

import com.jonny.exception.DaoLayerException;
import com.jonny.model.Employee;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.sql.Date;

@SpringBootTest(classes = EmployeeDaoImpl.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EmployeeConfig.class, loader = AnnotationConfigContextLoader.class)
public class EmployeeDaoImplTest {

    @Autowired
    private EmployeeDaoImpl employeeDao;//Bug in IDE, because @TestConfiguration(in DepartmentConfig.class) is wrong annotation for it.

    private static final int VALID_ID = 1;
    private static final int ZERO_ID = 0;
    private static final int OVER_ID = 999;
    private static final int DELETE_ID = 2;

    private static final Date DATE_EARLY = Date.valueOf("1980-01-01");
    private static final Date DATE_OLDER = Date.valueOf("2017-01-01");

    private static final Employee EMPTY_EMP = new Employee();
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
    private static final Employee CREATE_EMP = new Employee(
            15,
            "newLname",
            "newMname",
            "newSname",
            Date.valueOf("1998-11-31"),
            9988,
            "NewDepartment");

    /**
     * --- Testing method: public int create(Employee employee) ---
     */

    @Test
    public void create_createValidEmployee_Int1() throws Exception {
        Assert.assertEquals("Method for creating new employee in DB has wrong behavior.",
                1, employeeDao.create(CREATE_EMP));
    }

    @Test(expected = DaoLayerException.class)
    public void create_emptyEmployee_DaoLayerException() throws Exception {
        employeeDao.create(EMPTY_EMP);
    }

    @Test(expected = DaoLayerException.class)
    public void create_nullEmployee_DaoLayerException() throws Exception {
        employeeDao.create(NULL_EMP);
    }

    /**
     * --- Testing method: public Employee readByID(int id) ---
     */

    @Test
    public void readByID_validEmployee_validId() throws Exception {
        Assert.assertEquals("Method for reading employee by ID has wrong behavior.", VALID_ID, employeeDao.readByID(VALID_ID).getId());
    }

    @Test(expected = DaoLayerException.class)
    public void readByID_zeroIdEmployee_DaoLayerException() throws Exception {
        employeeDao.readByID(ZERO_ID);
    }

    @Test(expected = DaoLayerException.class)
    public void readByID_overIdEmployee_DaoLayerException() throws Exception {
        employeeDao.readByID(OVER_ID);
    }

    /**
     * --- Testing method: public int update(Employee employee) ---
     */

    @Test
    public void update_validEmployee_Int1() throws Exception {
        Assert.assertEquals("Method for updating employee has wrong behavior.", 1, employeeDao.update(VALID_EMP));
    }

    @Test
    public void update_notExistingEmployee_Int0() throws Exception {
        Assert.assertEquals("Updating isn't possible. Because wasn't found target Employee.",
                0, employeeDao.update(EMPTY_EMP));
        Assert.assertEquals("Updating isn't possible. Because wasn't found target Employee.",
                0, employeeDao.update(OVER_ID_EMP));
    }

    @Test(expected = DaoLayerException.class)
    public void update_nullEmployee_DaoLayerException() throws Exception {
        employeeDao.update(NULL_EMP);
    }
    /**
     * --- Testing method: public int create(Employee employee) ---
     */

    @Test
    public void delete_validId_Int1() throws Exception {
        Assert.assertEquals("Method for deleting employee has wrong behavior.",
                1, employeeDao.delete(DELETE_ID));
    }

    @Test
    public void delete_notExistingEmployee_Int0() throws Exception {
        Assert.assertEquals("Delete isn't possible. Because wasn't found target Employee by ID = 0.",
                0, employeeDao.delete(ZERO_ID));
        Assert.assertEquals("Updating isn't possible. Because wasn't found target Employee by over range ID.",
                0, employeeDao.delete(OVER_ID));
    }

    /**
     * --- Testing method: public List<Employee> findByDateRange(Date earlyDate, Date olderDate) ---
     */

    @Test
    public void findByDateRange_getNotEmptyList_SizeBiggerZero() throws Exception {
        Assert.assertTrue("Method for got employees list by date range has wrong behavior.",
                employeeDao.findByDateRange(DATE_EARLY, DATE_OLDER).size() > 0);
    }

    /**
     * --- Testing method: public List<Employee> getAll() ---
     */

    @Test
    public void getAll__getNotEmptyList_SizeBiggerZero() throws Exception {
        Assert.assertTrue("Method for got employees list has wrong behavior.",
                employeeDao.getAll().size() > 0);
    }

}
