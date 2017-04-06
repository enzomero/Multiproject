package com.jonny.dao;

import com.jonny.model.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentDao {

    int create(Department department);

    Department read(int id);

    int update(Department department);

    int delete(int id);

    List<Department> getAll();
}
