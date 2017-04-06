package com.jonny.service;

import com.jonny.dao.EmployeeDao;
import com.jonny.exception.InvalidParameterException;
import com.jonny.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNoneBlank;

@Service
public class EmployeeService {

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    public int create(Employee employee) {
        if (employee == null || !isNoneBlank(employee.getlName(),employee.getmName(), employee.getsName(), employee.getDepartment()))
            throw new InvalidParameterException("Can't create employee. Because was got invalid employee ("+employee+").");
        return employeeDao.create(employee);
    }

    public Employee readById(int id) {
        if (id == 0)
            throw new InvalidParameterException("Employee wasn't found by ID = "+id+".");
        return employeeDao.readByID(id);
    }

    public int update(Employee employee) {
        if (employee == null || employee.getId() == 0 || !isNoneBlank(employee.getlName(),employee.getmName(), employee.getsName(), employee.getDepartment()))
            throw new InvalidParameterException("Can't update employee. Because was got invalid employee ("+employee+").");
        return employeeDao.update(employee);
    }

    public int delete(int id) {
        if (id == 0)
            throw new InvalidParameterException("Can't delete employee. Because was got invalid employee with ID = "+id+".");
        return employeeDao.delete(id);
    }

    public List<Employee> readAll() {
        return employeeDao.getAll();
    }

    public List<Employee> findByDateRange(Date earlyDate, Date olderDate) {
        return employeeDao.findByDateRange(earlyDate, olderDate);
    }
}

