package com.jonny.dao.impl;

import com.jonny.dao.DepartmentDao;
import com.jonny.exception.DaoLayerException;
import com.jonny.model.AvgSalaryDepartments;
import com.jonny.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@PropertySource(value="classpath:requests.properties")
public class DepartmentDaoImpl implements DepartmentDao {

    @Value(value = "${sql.dep.create}")
    private String CREATE;
    @Value(value = "${sql.dep.read}")
    private String READ;
    @Value(value = "${sql.dep.update}")
    private String UPDATE;
    @Value(value = "${sql.dep.delete}")
    private String DELETE;
    @Value(value = "${sql.dep.read.id}")
    private String SELECT_BY_ID;
    @Value(value = "${sql.dep.salary.avg}")
    private String READ_AVG_SALARY;
    @Value(value = "#{'${sql.dep.fields}'.split(',')}")
    private String[] TABLE_FIELD;
    @Value(value = "#{'${sql.dep.fields.avg}'.split(',')}")
    private String[] AVG_TABLE_FIELD;

    private final NamedParameterJdbcTemplate template;

    @Autowired
    public DepartmentDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public int create(Department department) {
        try {
            Map<String, java.io.Serializable> map = new HashMap<>();
            map.put(TABLE_FIELD[0], null);
            map.put(TABLE_FIELD[1], department.getName());
            return template.update(CREATE, map);
        } catch (DataAccessException | NullPointerException e) {
            throw new DaoLayerException(e.getMessage());
        }
    }

    @Override
    public Department read(int id) {
        try {
            Map<String, java.io.Serializable> map = new HashMap<>();
            map.put(TABLE_FIELD[0], id);
            return template.queryForObject(SELECT_BY_ID, map, rowMapper);
        } catch (DataAccessException e) {
            throw new DaoLayerException(e.getMessage());
        }
    }

    @Override
    public int update(Department department) {
        try {
            Map<String, Serializable> map = new HashMap<>();
            map.put(TABLE_FIELD[0], department.getId());
            map.put(TABLE_FIELD[1], department.getName());
            return template.update(UPDATE, map);
        } catch (DataAccessException | NullPointerException e) {
            throw new DaoLayerException(e.getMessage());
        }
    }

    @Override
    public int delete(int id) {
        try {
            Map<String, java.io.Serializable> map = new HashMap<>();
            map.put(TABLE_FIELD[0], id);
            return template.update(DELETE, map);
        } catch (DataAccessException e) {
            throw new DaoLayerException(e.getMessage());
        }
    }

    @Override
    public List<Department> getAll() {
        try {
            return template.query(READ, rowMapper);
        } catch (DataAccessException e) {
            throw new DaoLayerException(e.getMessage());
        }
    }

    @Override
    public List<AvgSalaryDepartments> readAvgSalaryDepartments() {
        try {
            return template.query(READ_AVG_SALARY, AvgSalaryDepartmentsRowMapper);
        } catch (DataAccessException e) {
            throw new DaoLayerException(e.getMessage());
        }
    }

    private final RowMapper<AvgSalaryDepartments> AvgSalaryDepartmentsRowMapper = (rs, rowNum) -> {
        try {
            AvgSalaryDepartments avgSalary = new AvgSalaryDepartments();
            avgSalary.setDepartmentName(rs.getString(AVG_TABLE_FIELD[0]));
            avgSalary.setAvgSalary(rs.getDouble(AVG_TABLE_FIELD[1]));
            return avgSalary;
        } catch (SQLException e) {
            throw new DaoLayerException(e.getMessage());
        }
    };

    private final RowMapper<Department> rowMapper = (rs, rowNum) -> {
        try {
            Department department = new Department();
            department.setId(rs.getInt(TABLE_FIELD[0]));
            department.setName(rs.getString(TABLE_FIELD[1]));
            return department;
        } catch (SQLException e) {
            throw new DaoLayerException(e.getMessage());
        }
    };
}
