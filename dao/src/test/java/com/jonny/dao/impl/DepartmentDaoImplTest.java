package com.jonny.dao.impl;

import com.jonny.exception.DaoLayerException;
import com.jonny.model.Department;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@SpringBootTest(classes = DepartmentDaoImpl.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class, loader = AnnotationConfigContextLoader.class)
public class DepartmentDaoImplTest {

    @Autowired
    private DepartmentDaoImpl departmentDao;//Bug in IDE, because @TestConfiguration(in TestConfig.class) is wrong annotation for it.

    public static final int VALID_ID = 1;
    public static final int DELETE_ID = 2;
    public static final int ZERO_ID = 0;
    public static final int OVER_ID = 999;

    public static final Department EMPTY_DEP = new Department();
    public static final Department NULL_DEP = null;
    public static final Department VALID_DEP = new Department(
            VALID_ID,
            "ValidName");
    public static final Department OVER_ID_DEP = new Department(
            OVER_ID,
            "OverIdValidName");
    public static final Department CREATE_DEP = new Department(
            15,
            "NewName");
    public static final Department INVALID_NAME_DEP = new Department(
            4,
            "");
    public static final Department ZERO_ID_DEP = new Department(
            0,
            "ZeroIdName");

    /**
     * --- Testing method: public int create(Department department) ---
     */

    @Test
    public void create_createNewDepartment_Int1() throws Exception {
        Assert.assertEquals("Method for creating new department in DB has wrong behavior.", 1, departmentDao.create(CREATE_DEP));
    }

    @Test(expected = DaoLayerException.class)
    public void create_nullDepartmentArg_DaoLayerException() throws Exception {
        departmentDao.create(NULL_DEP);
    }

    @Test(expected = DaoLayerException.class)
    public void create_emptyDepartmentArg_DaoLayerException() throws Exception {
        departmentDao.create(EMPTY_DEP);
    }

    @Test(expected = DaoLayerException.class)
    public void create_duplicateDepartmentArg_DaoLayerException() throws Exception {
        departmentDao.create(CREATE_DEP);
        departmentDao.create(CREATE_DEP);
    }

    /**
     * --- Testing method: public Department read(int id) ---
     */

    @Test
    public void read_byValidId_Department() throws Exception {
        Assert.assertEquals("Method for reading department by ID has wrong behavior.", 1, departmentDao.read(VALID_ID).getId());
    }

    @Test(expected = DaoLayerException.class)
    public void read_byZeroId_DaoLayerException() throws Exception {
        departmentDao.read(ZERO_ID);
    }

    @Test(expected = DaoLayerException.class)
    public void read_byOverId_DaoLayerException() throws Exception {
        departmentDao.read(OVER_ID);
    }

    /**
     * --- Testing method: public int update(Department department) ---
     */

    @Test
    public void update_validDepartment_Int1() throws Exception {
        Assert.assertEquals("Method for updating department has wrong behavior.",
                1, departmentDao.update(VALID_DEP));
    }

    @Test
    public void update_notExistingDepartment_Int0() throws Exception {
        Assert.assertEquals("Updating isn't possible. Because wasn't found target Department.",
                0,departmentDao.update(EMPTY_DEP));
        Assert.assertEquals("Updating isn't possible. Because wasn't found target Department.",
                0,departmentDao.update(OVER_ID_DEP));
    }

    @Test(expected = DaoLayerException.class)
    public void update_nullDepartment_DaoLayerException() throws Exception {
        departmentDao.update(NULL_DEP);
    }

    /**
     * --- Testing method: public int delete(int id) ---
     */

    @Test
    public void delete_existingDepartment_Int1() throws Exception {
        Assert.assertEquals("Method for deleting department has wrong behavior.",
                1, departmentDao.delete(DELETE_ID));
    }

    @Test
    public void delete_notExistingDepartment_Int0() throws Exception {
        Assert.assertEquals("Delete isn't possible. Because wasn't found target Department by ID = 0.",
                0, departmentDao.delete(ZERO_ID));
        Assert.assertEquals("Updating isn't possible. Because wasn't found target Department by over range ID.",
                0, departmentDao.delete(OVER_ID));
    }

    /**
     * --- Testing method: public List<Department> getAll() ---
     */

    @Test
    public void getAll_gotSize_biggerThatZero() throws Exception {
        Assert.assertTrue("Method for got departments list has wrong behavior.",
                departmentDao.getAll().size() > 0);
    }
}
