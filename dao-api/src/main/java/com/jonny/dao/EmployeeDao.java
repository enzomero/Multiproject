package com.jonny.dao;

import com.jonny.model.Employee;

import java.sql.Date;
import java.util.List;

/**
 * Dao interface for Employees
 */
public interface EmployeeDao {
    /**
     *
     * @param employee
     * @return
     */
    int create(Employee employee);

    /**
     *
     * @param id
     * @return
     */
    Employee readByID(int id);

    /**
     *
     * @return
     */
    List<Employee> getAll();

    /**
     *
     * @param employee
     * @return
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
