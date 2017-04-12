package com.jonny.dao.impl;

import com.jonny.dao.DepartmentDao;
import com.jonny.exception.DaoLayerException;
import com.jonny.model.AvgSalaryDepartments;
import com.jonny.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public class DepartmentDaoImpl implements DepartmentDao {

    private static final String CREATE = "INSERT INTO DEPARTMENT (id, name) VALUES(?, ?) ;";
    private static final String READ = "SELECT * FROM DEPARTMENT;";
    private static final String UPDATE = "UPDATE DEPARTMENT SET NAME=? WHERE ID=? ;";
    private static final String DELETE = "DELETE FROM DEPARTMENT WHERE ID = ? ;";

    private static final String SELECT_BY_ID = "SELECT * FROM DEPARTMENT WHERE ID = ? ;";

    private static final String READ_AVG_SALARY = "SELECT DEPARTMENT, AVG(SALARY) AS SALARY FROM EMPLOYEE GROUP BY DEPARTMENT;";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public DepartmentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(Department department) {
        try {
            return jdbcTemplate.update(CREATE, getPreparedStatementSetter(department));
        } catch (DataAccessException | NullPointerException e) {
            throw new DaoLayerException(e.getMessage());
        }
    }

    @Override
    public Department read(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_BY_ID, rowMapper, id);
        } catch (DataAccessException e) {
            throw new DaoLayerException(e.getMessage());
        }
    }

    @Override
    public int update(Department department) {
        try {
            return jdbcTemplate.update(UPDATE, department.getName(),
                    department.getId());
        } catch (DataAccessException | NullPointerException e) {
            throw new DaoLayerException(e.getMessage());
        }
    }

    @Override
    public int delete(int id) {
        try {
            return jdbcTemplate.update(DELETE, id);
        } catch (DataAccessException e) {
            throw new DaoLayerException(e.getMessage());
        }
    }

    @Override
    public List<Department> getAll() {
        try {
            return jdbcTemplate.query(READ, rowMapper);
        } catch (DataAccessException e) {
            throw new DaoLayerException(e.getMessage());
        }
    }

    @Override
    public List<AvgSalaryDepartments> readAvgSalaryDepartments() {
        try {
            return jdbcTemplate.query(READ_AVG_SALARY, AvgSalaryDepartmentsRowMapper);
        } catch (DataAccessException e) {
            throw new DaoLayerException(e.getMessage());
        }
    }

    private final RowMapper<AvgSalaryDepartments> AvgSalaryDepartmentsRowMapper = (rs, rowNum) -> {
        try {
            AvgSalaryDepartments avgSalary = new AvgSalaryDepartments();
            avgSalary.setDepartmentName(rs.getString("DEPARTMENT"));
            avgSalary.setAvgSalary(rs.getDouble("SALARY"));
            return avgSalary;
        } catch (SQLException e) {
            throw new DaoLayerException(e.getMessage());
        }
    };

    private final RowMapper<Department> rowMapper = (rs, rowNum) -> {
        try {
            Department department = new Department();
            department.setId(rs.getInt("ID"));
            department.setName(rs.getString("NAME"));
            return department;
        } catch (SQLException e) {
            throw new DaoLayerException(e.getMessage());
        }
    };

    private PreparedStatementSetter getPreparedStatementSetter(final Department department) {
        return ps -> {
            try {
                int i = 0;
                ps.setNull(++i, department.getId());
                ps.setString(++i, department.getName());
            } catch (SQLException e) {
                throw new DaoLayerException(e.getMessage());
            }
        };
    }
}
