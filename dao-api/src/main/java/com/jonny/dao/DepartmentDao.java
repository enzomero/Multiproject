package com.jonny.dao;

import com.jonny.model.AvgSalaryDepartments;
import com.jonny.model.Department;

import java.util.List;

/**
 * DAO interface for Departments
 */
public interface DepartmentDao {
    /**
     * Create new department
     * @param department - 
     * @return
     */
    int create(Department department);

    /**
     *
     * @param id
     * @return
     */
    Department read(int id);

    /**
     *
     * @param department
     * @return
     */
    int update(Department department);

    /**
     *
     * @param id
     * @return
     */
    int delete(int id);

    /**
     *
     * @return
     */
    List<Department> getAll();

    /**
     *
     * @return
     */
    List<AvgSalaryDepartments> readAvgSalaryDepartments();
}
