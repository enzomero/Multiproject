package com.jonny.dao.impl;

import com.jonny.dao.EmployeeDao;
import com.jonny.exception.DaoLayerException;
import com.jonny.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployeeDao {
    private static final String CREATE = "INSERT INTO EMPLOYEE (id, lname, mname, sname, date, salary, department) VALUES(?, ?, ?, ?, ?, ?, ?);";
    private static final String READ = "SELECT * FROM EMPLOYEE;";
    private static final String UPDATE = "UPDATE EMPLOYEE SET LNAME = ?, MNAME = ?, SNAME = ?, DATE = ?, SALARY = ?, DEPARTMENT = ?  WHERE ID = ? ;";
    private static final String DELETE = "DELETE FROM EMPLOYEE WHERE ID = ? ;";

    private static final String SELECT_BY_ID = "SELECT * FROM EMPLOYEE WHERE ID = ? ;";
    private static final String SELECT_BY_DATE_RANGE = "SELECT * FROM EMPLOYEE WHERE DATE>=? AND DATE<=? ORDER BY DEPARTMENT;";

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int create(Employee employee) {
        try {
            return jdbcTemplate.update(CREATE, getPreparedStatementSetter(employee));
        }catch (DataAccessException | NullPointerException e){
            throw new DaoLayerException(e.getMessage());
        }
    }

    @Override
    public Employee readByID(int id){
        try {
            return jdbcTemplate.queryForObject(SELECT_BY_ID, rowMapper, id);
        }catch (DataAccessException e){
            throw new DaoLayerException(e.getMessage());
        }
    }

    @Override
    public int update(Employee employee){
        try {
            return jdbcTemplate.update(UPDATE, employee.getlName(),
                    employee.getmName(),
                    employee.getsName(),
                    employee.getDate(),
                    employee.getSalary(),
                    employee.getDepartment(),
                    employee.getId());
        }catch (DataAccessException | NullPointerException e){
            throw new DaoLayerException(e.getMessage());
        }
    }

    @Override
    public int delete(int id){
        try {
            return jdbcTemplate.update(DELETE, id);
        }catch (DataAccessException e){
            throw new DaoLayerException(e.getMessage());
        }
    }

    @Override
    public List<Employee> findByDateRange(Date earlyDate, Date olderDate){
        try {
            return jdbcTemplate.query(SELECT_BY_DATE_RANGE, rowMapper, earlyDate, olderDate);
        }catch (DataAccessException e){
            throw new DaoLayerException(e.getMessage());
        }
    }

    @Override
    public List<Employee> getAll(){
        try {
            return jdbcTemplate.query(READ, rowMapper);
        }catch (DataAccessException e){
            throw new DaoLayerException(e.getMessage());
        }
    }

    private RowMapper<Employee> rowMapper = (rs, rowNum) -> {
        try {
            Employee employee = new Employee();
            employee.setId(rs.getInt("ID"));
            employee.setlName(rs.getString("LNAME"));
            employee.setmName(rs.getString("MNAME"));
            employee.setsName(rs.getString("SNAME"));
            employee.setDate(rs.getDate("DATE"));
            employee.setSalary(rs.getDouble("SALARY"));
            employee.setDepartment(rs.getString("DEPARTMENT"));
            return employee;
        }catch (SQLException e){
            throw new DaoLayerException(e.getMessage());
        }
    };

    private PreparedStatementSetter getPreparedStatementSetter(final Employee employee) {
        return ps -> {
            try {
                int i = 0;
                ps.setNull(++i, employee.getId());
                ps.setString(++i, employee.getlName());
                ps.setString(++i, employee.getmName());
                ps.setString(++i, employee.getsName());
                ps.setDate(++i, employee.getDate());
                ps.setDouble(++i, employee.getSalary());
                ps.setString(++i, employee.getDepartment());
            }catch (SQLException e){
                throw new DaoLayerException(e.getMessage());
            }
        };
    }
}
