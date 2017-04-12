package com.jonny.model;

import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class Employee {
    private int id;
    private String lName;
    private String mName;
    private String sName;
    private Date date;
    private double salary;
    private String department;

    public Employee() {
    }

    public Employee(int id, String lName, String mName, String sName, Date date, double salary, String department) {
        this.id = id;
        this.lName = lName;
        this.mName = mName;
        this.sName = sName;
        this.date = date;
        this.salary = salary;
        this.department = department;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Employee { ID = " + id + "," +
                " LONG NAME = '" + lName + "'," +
                " MIDDLE NAME = '" + mName + "'," +
                " SHORT NAME = '" + lName + "'," +
                " DATE = '" + date + "'," +
                " SALARY = " + salary + "," +
                " DEPARTMENT = '" + department + "' }";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        return lName.equals(employee.lName) &&
                mName.equals(employee.mName) &&
                sName.equals(employee.sName) &&
                date.equals(employee.date) &&
                department.equals(employee.department);
    }
}
