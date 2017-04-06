package com.jonny.dao;

import com.jonny.model.Employee;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface EmployeeDao {

    int create(Employee employee);

    Employee readByID(int id);

    List<Employee> getAll();

    int update(Employee employee);

    int delete(int id);

    List<Employee> findByDateRange(Date earlyDate, Date olderDate);
}
