package com.jonny.dao;

import com.jonny.model.Department;

import java.util.List;

public interface DepartmentDao {

    int create(Department department);

    Department read(int id);

    int update(Department department);

    int delete(int id);

    List<Department> getAll();
}
