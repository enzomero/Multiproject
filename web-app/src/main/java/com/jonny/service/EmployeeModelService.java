package com.jonny.service;

import com.jonny.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.client.RestTemplate;

import java.sql.Date;

@Component
public class EmployeeModelService {
    @Value(value = "${service.port}")
    private String PORT;
    @Value(value = "${service.host}")
    private String HOST;
    @Value(value = "${service.root.emp}")
    private String ROOT;

    private final RestTemplate rest;

    @Autowired
    private EmployeeModelService(final RestTemplateBuilder builder) {
        this.rest = builder.setConnectTimeout(2000)
                .setReadTimeout(2000)
                .build();
    }

    public void create(Model model) {
        model.addAttribute("employee", new Employee());
    }

    public void readAll(ModelMap modelMap) {
        Employee[] emp = rest.getForObject(HOST + PORT + ROOT + "/all", Employee[].class);
        modelMap.put("all", emp);
    }

    public void save(int id, String lName, String mName, String sName, Date date, double salary, String department) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setlName(lName);
        employee.setmName(mName);
        employee.setsName(sName);
        employee.setDate(date);
        employee.setSalary(salary);
        employee.setDepartment(department);
        if (id == 0)
            rest.postForLocation(HOST + PORT + ROOT + "/create", employee);
        else
            rest.put(HOST + PORT + ROOT + "/update", employee);
    }

    public void delete(int id) {
        rest.postForLocation(HOST + PORT + ROOT + "/delete", id);
    }

    public void edit(int id, Model model) {
        model.addAttribute("employee", readById(id));
    }

    private Employee readById(int id) {
        return rest.getForObject(HOST + PORT + ROOT + "/{id}", Employee.class, id);
    }

    public void findByDateRange(String earlyDate, String olderDate, ModelMap modelMap) {
        modelMap.put("all", rest.getForObject(HOST + PORT + ROOT + "/findByDateRange/{earlyDate},{olderDate}",
                Employee[].class, earlyDate, olderDate));
    }
}
