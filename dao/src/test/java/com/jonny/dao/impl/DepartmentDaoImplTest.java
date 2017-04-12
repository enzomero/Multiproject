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
@ContextConfiguration(classes = DepartmentConfig.class, loader = AnnotationConfigContextLoader.class)
public class DepartmentDaoImplTest {

    @Autowired
    private DepartmentDaoImpl departmentDao;//Bug in IDE, because @TestConfiguration(in DepartmentConfig.class) is wrong annotation for it.

    private static final int VALID_ID = 1;
    private static final int DELETE_ID = 2;
    private static final int ZERO_ID = 0;
    private static final int OVER_ID = 999;

    private static final Department EMPTY_DEP = new Department();
    private static final Department NULL_DEP = null;
    private static final Department VALID_DEP = new Department(
            VALID_ID,
            "ValidName");
    private static final Department OVER_ID_DEP = new Department(
            OVER_ID,
            "OverIdValidName");
    private static final Department CREATE_DEP = new Department(
            15,
            "NewName");

    /**
     * --- Testing method: public int create(Department department) ---
     */

    @Test
    public void create_createNewDepartment_Int1() throws Exception {
        Assert.assertEquals("Method for creating new department in DB has wrong behavior.",
                1, departmentDao.create(CREATE_DEP));
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
