package com.jonny.dao;

import com.jonny.model.AvgSalaryDepartments;
import com.jonny.model.Department;

import java.util.List;

/**
 * DAO interface for Departments.
 * Contain CRUD and necessary methods of business logic.
 */
public interface DepartmentDao {
    /**
     * Create new department.
     * @param department - object on based department's model.
     * @return 1 - department was successfully created, 0 - couldn't to create.
     */
    int create(Department department);

    /**
     * Get department by id.
     * @param id - value of field ID from department's object.
     * @return Department with the ID.
     */
    Department readById(int id);

    /**
     * Update existing employee.
     * @param department - object on based department's model.
     * @return 1 - department was successfully updated, 0 - couldn't to update.
     */
    int update(Department department);

    /**
     * Delete existing department by ID.
     * @param id - value of field ID from department's object.
     * @return 1 - department was successfully deleted, 0 - couldn't to delete.
     */
    int delete(int id);

    /**
     * Get list of departments.
     * @return Departments list.
     */
    List<Department> getAll();

    /**
     * Get list of departments by average salary into each department.
     * @return Departments list.
     */
    List<AvgSalaryDepartments> readAvgSalaryDepartments();
}
