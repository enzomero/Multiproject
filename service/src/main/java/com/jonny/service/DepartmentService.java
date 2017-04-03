package com.jonny.service;

import com.jonny.dao.DepartmentDao;
import com.jonny.exception.ServiceLayerException;
import com.jonny.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentDao departmentDao;

    @Autowired
    public DepartmentService(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public int create(Department department) {
        if (department == null || !department.isValid())
            throw new InvalidParameterException("Can't create department. Because was got invalid department ("+department+").");
        return departmentDao.create(department);
    }

    public Department readById(int id) {
        if (id == 0)
            throw new InvalidParameterException("Department wasn't found by ID = "+id+".");
        return departmentDao.read(id);
    }

    public int update(Department department) {
        if (department == null || department.getId() == 0 || !department.isValid())
            throw new InvalidParameterException("Can't update department. Because was got invalid department ("+department+").");
        return departmentDao.update(department);
    }

    public int delete(Department department) {
        if (department == null || department.getId() == 0 )
            throw new InvalidParameterException("Can't delete department. Because was got invalid department ("+department+").");
        return departmentDao.delete(department.getId());
    }

    public List<Department> readAll() {
        List<Department> departments = departmentDao.getAll();
        if(departments.size()==0)
            throw new ServiceLayerException("List of employees is empty. Can't use it further.");
        return departments;
    }
}
