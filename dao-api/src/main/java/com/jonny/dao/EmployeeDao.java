package com.jonny.dao;

import com.jonny.model.Employee;

import java.sql.Date;
import java.util.List;

/**
 * DAO interface for Employee.
 * Contain CRUD and necessary methods of business logic.
 */
public interface EmployeeDao {
    /**
     * Create new employee.
     * @param employee - object on based employee's model.
     * @return 1 - employee was successfully created, 0 - couldn't to create.
     */
    int create(Employee employee);

    /**
     * Get employee by id.
     * @param id - value of field ID from employee's object.
     * @return Employee with the ID.
     */
    Employee readByID(int id);

    /**
     * Get list of employees.
     * @return Employees list.
     */
    List<Employee> getAll();

    /**
     * Update existing employee.
     * @param employee - object on based employee's model.
     * @return 1 - employee was successfully updated, 0 - couldn't to update.
     */
    int update(Employee employee);

    /**
     * Delete existing employee by ID.
     * @param id - value of field ID from employee's object.
     * @return 1 - employee was successfully deleted, 0 - couldn't to delete.
     */
    int delete(int id);

    /**
     * Searching some employees by birthday.
     * @param earlyDate - earlier date for request.
     * @param olderDate - most old date for request.
     * @return list of employees between dates.
     */
    List<Employee> findByDateRange(Date earlyDate, Date olderDate);
}
