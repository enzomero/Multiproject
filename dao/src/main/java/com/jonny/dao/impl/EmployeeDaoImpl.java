package com.jonny.dao.impl;

import com.jonny.dao.EmployeeDao;
import com.jonny.exception.DaoLayerException;
import com.jonny.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@PropertySource(value="classpath:requests.properties")
public class EmployeeDaoImpl implements EmployeeDao {

    @Value(value = "${sql.emp.create}")
    private String CREATE;
    @Value(value = "${sql.emp.read}")
    private String READ;
    @Value(value = "${sql.emp.update}")
    private String UPDATE;
    @Value(value = "${sql.emp.delete}")
    private String DELETE;
    @Value(value = "${sql.emp.read.id}")
    private String SELECT_BY_ID;
    @Value(value = "${sql.emp.date.range}")
    private String SELECT_BY_DATE_RANGE;
    @Value(value = "#{'${sql.emp.fields}'.split(',')}")
    private String[] TABLE_FIELD;

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public EmployeeDaoImpl(NamedParameterJdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    @Override
    public int create(Employee employee) {
        try {
            Map<String, java.io.Serializable> map = new HashMap<>();
            map.put(TABLE_FIELD[0], null);
            map.put(TABLE_FIELD[1], employee.getlName());
            map.put(TABLE_FIELD[2], employee.getmName());
            map.put(TABLE_FIELD[3], employee.getsName());
            map.put(TABLE_FIELD[4], employee.getDate());
            map.put(TABLE_FIELD[5], employee.getSalary());
            map.put(TABLE_FIELD[6], employee.getDepartment());
            return jdbcTemplate.update(CREATE, map);
        }catch (DataAccessException | NullPointerException e){
            throw new DaoLayerException(e.getMessage());
        }
    }

    @Override
    public Employee readByID(int id){
        try {
            Map<String, Integer> map = new HashMap<>();
            map.put(TABLE_FIELD[0], id);
            return jdbcTemplate.queryForObject(SELECT_BY_ID, map, rowMapper);
        }catch (DataAccessException e){
            throw new DaoLayerException(e.getMessage());
        }
    }

    @Override
    public int update(Employee employee){
        try {
            Map<String, java.io.Serializable> map = new HashMap<>();
            map.put(TABLE_FIELD[0], employee.getId());
            map.put(TABLE_FIELD[1], employee.getlName());
            map.put(TABLE_FIELD[2], employee.getmName());
            map.put(TABLE_FIELD[3], employee.getsName());
            map.put(TABLE_FIELD[4], employee.getDate());
            map.put(TABLE_FIELD[5], employee.getSalary());
            map.put(TABLE_FIELD[6], employee.getDepartment());
            return jdbcTemplate.update(UPDATE, map);
        }catch (DataAccessException | NullPointerException e){
            throw new DaoLayerException(e.getMessage());
        }
    }

    @Override
    public int delete(int id){
        try {
            Map<String, Integer> map = new HashMap<>();
            map.put(TABLE_FIELD[0], id);
            return jdbcTemplate.update(DELETE, map);
        }catch (DataAccessException e){
            throw new DaoLayerException(e.getMessage());
        }
    }

    @Override
    public List<Employee> findByDateRange(Date earlyDate, Date olderDate){
        try {
            Map<String, Date> map = new HashMap<>();
            map.put("earlyDate", earlyDate);
            map.put("olderDate", olderDate);
            return jdbcTemplate.query(SELECT_BY_DATE_RANGE, map, rowMapper);
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

    private final RowMapper<Employee> rowMapper = (rs, rowNum) -> {
        try {
            Employee employee = new Employee();
            employee.setId(rs.getInt(TABLE_FIELD[0]));
            employee.setlName(rs.getString(TABLE_FIELD[1]));
            employee.setmName(rs.getString(TABLE_FIELD[2]));
            employee.setsName(rs.getString(TABLE_FIELD[3]));
            employee.setDate(rs.getDate(TABLE_FIELD[4]));
            employee.setSalary(rs.getDouble(TABLE_FIELD[5]));
            employee.setDepartment(rs.getString(TABLE_FIELD[6]));
            return employee;
        }catch (SQLException e){
            throw new DaoLayerException(e.getMessage());
        }
    };
}
