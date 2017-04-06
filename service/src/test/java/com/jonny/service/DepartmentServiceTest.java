package com.jonny.service;

import com.jonny.dao.DepartmentDao;
import com.jonny.exception.InvalidParameterException;
import com.jonny.model.Department;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentServiceTest {
    @Mock
    private DepartmentDao mock;

    @InjectMocks
    private DepartmentService sut;

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
    public void create_validArg_Int1() throws Exception {
        when(mock.create(VALID_DEP)).thenReturn(1);
        Assert.assertEquals("Method for creating department has wrong behavior.",
                1, sut.create(VALID_DEP));
        verify(mock, times(1)).create(VALID_DEP);
    }

    @Test(expected = InvalidParameterException.class)
    public void create_nullDep_InvalidParameterException() throws Exception {
        sut.create(NULL_DEP);
    }

    @Test(expected = InvalidParameterException.class)
    public void create_invalidNameDep_InvalidParameterException() throws Exception {
        sut.create(INVALID_NAME_DEP);
    }

    /**
     * --- Testing method: public Department readById(int id) ---
     */

    @Test
    public void readById_validIdArg_ValidDepartment() throws Exception {
        when(mock.read(VALID_ID)).thenReturn(VALID_DEP);
        Assert.assertEquals("Method for reading department by id="+VALID_ID+" has wrong behavior.",
                VALID_DEP, sut.readById(VALID_ID));
        verify(mock, times(1)).read(VALID_ID);
    }

    @Test(expected = InvalidParameterException.class)
    public void readById_zeroId_InvalidParameterException() throws Exception {
        sut.readById(ZERO_ID);
    }

    /**
     * --- Testing method: public int update(Department department) ---
     */

    @Test
    public void update_existingDepartment_Int1() throws Exception {
        when(mock.update(VALID_DEP)).thenReturn(1);
        Assert.assertEquals("Method for updating department has wrong behavior.",
                1, sut.update(VALID_DEP));
        verify(mock, times(1)).update(VALID_DEP);
    }

    @Test
    public void update_overIdDepartment_Int0() throws Exception {
        when(mock.update(OVER_ID_DEP)).thenReturn(0);
        Assert.assertEquals("Method for updating department has wrong behavior.",
                0, sut.update(OVER_ID_DEP));
        verify(mock, times(1)).update(OVER_ID_DEP);
    }


    @Test(expected = InvalidParameterException.class)
    public void update_nullDepartment_InvalidParameterException() throws Exception {
        sut.update(NULL_DEP);
    }

    @Test(expected = InvalidParameterException.class)
    public void update_zeroIdDepartment_InvalidParameterException() throws Exception {
        sut.update(ZERO_ID_DEP);
    }

    @Test(expected = InvalidParameterException.class)
    public void update_invalidDepartment_InvalidParameterException() throws Exception {
        sut.update(INVALID_NAME_DEP);
    }

    /**
     * --- Testing method: public int update(Department department) ---
     */

    @Test
    public void delete_existingDepartment_Int1() throws Exception {
        when(mock.delete(VALID_ID)).thenReturn(1);
        Assert.assertEquals("Method for delete department has wrong behavior.",
                1, sut.delete(VALID_ID));
        verify(mock).delete(VALID_ID);
    }

    @Test
    public void delete_overIdDepartment_Int0() throws Exception {
        when(mock.delete(OVER_ID)).thenReturn(0);
        Assert.assertEquals("Method for delete department has wrong behavior.",
                0, sut.delete(OVER_ID));
        verify(mock).delete(OVER_ID);
    }

    @Test(expected = InvalidParameterException.class)
    public void delete_zeroIdDepartment_InvalidParameterException() throws Exception {
        sut.delete(ZERO_ID);
    }

    /**
     * --- Testing method: public List<Department> readAll() ---
     */

    @Test
    public void readAll_getNotEmptyList_listDepartmentListMoreThatZero() throws Exception {
        List<Department> list = new ArrayList<>();
        list.add(VALID_DEP);
        when(mock.getAll()).thenReturn(list);
        Assert.assertTrue("Method has to return size bigger of zero, but he didn't.",
                sut.readAll().size() > 0);
        verify(mock, times(1)).getAll();
    }
}
