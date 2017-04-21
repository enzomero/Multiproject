package com.jonny.dao;

import com.jonny.model.Employee;

import java.sql.Date;
import java.util.List;

/**
 * DAO interface for Employee
 */
public interface EmployeeDao {
    /**
     * Add new employee.
     * @param employee - object on based employee's model.
     * @return 1 - employee was successfully added, 0 - couldn't to add.
     */
    int create(Employee employee);

    /**
     * Get employee by id.
     * @param id - value of field ID from employee's model.
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
     * @return 1 - employee was successfully updated, 0 - couldn't to add.
     */
    int update(Employee employee);

    /**
     *
     * @param id
     * @return
     */
    int delete(int id);

    /**
     *
     * @param earlyDate
     * @param olderDate
     * @return
     */
    List<Employee> findByDateRange(Date earlyDate, Date olderDate);
}
