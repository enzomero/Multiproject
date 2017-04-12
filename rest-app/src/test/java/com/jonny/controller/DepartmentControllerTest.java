package com.jonny.controller;


import com.jonny.model.Department;
import com.jonny.service.DepartmentService;
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
public class DepartmentControllerTest {
    @Mock
    private DepartmentService mock;

    @InjectMocks
    private DepartmentController sut;

    private static final int VALID_ID = 1;
    private static final int OVER_ID = 999;

    private static final Department VALID_DEP = new Department(
            VALID_ID,
            "ValidName");
    private static final Department OVER_ID_DEP = new Department(
            OVER_ID,
            "OverIdValidName");

    /**
     * --- Testing method: public void create(@RequestBody Department department) ---
     */

    @Test
    public void create_validArg_NumberInvocation1() throws Exception {
        when(mock.create(VALID_DEP)).thenReturn(1);
        sut.create(VALID_DEP);
        verify(mock, times(1)).create(VALID_DEP);
    }

    /**
     * --- Testing method: public void readAll() ---
     */

    @Test
    public void readAll_notEmptyListReturned_ListDepartmentListMoreThatZero() throws Exception {
        List<Department> departments = new ArrayList<>();
        departments.add(VALID_DEP);
        when(mock.readAll()).thenReturn(departments);

        Assert.assertTrue("Message", sut.readAll().size() > 0);
        verify(mock, times(1)).readAll();
    }

    /**
     * --- Testing method: public void update(@RequestBody Department department) ---
     */

    @Test
    public void update_validArg_NumberInvocation1() throws Exception {
        when(mock.update(VALID_DEP)).thenReturn(1);
        sut.update(VALID_DEP);

        verify(mock, times(1)).update(VALID_DEP);
    }

    @Test
    public void update_overIdDepartment_NumberInvocation1() throws Exception {
        when(mock.update(OVER_ID_DEP)).thenReturn(0);
        sut.update(OVER_ID_DEP);
        verify(mock, times(1)).update(OVER_ID_DEP);
    }

    /**
     * --- Testing method: public void delete(@RequestBody Department department) ---
     */

    @Test
    public void delete_validArg_NumberInvocation1() throws Exception {
        when(mock.delete(VALID_ID)).thenReturn(1);
        sut.delete(VALID_ID);

        verify(mock, times(1)).delete(VALID_ID);
    }

    @Test
    public void delete_overIdDepartment_JdbcTemplateThrowException() throws Exception {
        when(mock.delete(OVER_ID)).thenReturn(0);
        sut.delete(OVER_ID);
        verify(mock, times(1)).delete(OVER_ID);
    }

    /**
     * --- Testing method: public Department read(@PathVariable("id") int id) ---
     */

    @Test
    public void read_validArg_ValidDepartmentByValidId() throws Exception {
        when(mock.readById(VALID_ID)).thenReturn(VALID_DEP);
        Assert.assertEquals("Method has wrong behavior", VALID_DEP, sut.read(VALID_ID));
        verify(mock, times(1)).readById(VALID_ID);
    }
}
