package com.jonny.service;

import com.jonny.model.AvgSalaryDepartments;
import com.jonny.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Component
@PropertySource(value="classpath:service.properties")
public class DepartmentModelService {

    @Value(value = "${service.port}")
    private String PORT;
    @Value(value = "${service.host}")
    private String HOST;
    @Value(value = "${service.root.dep}")
    private String ROOT;

    private final RestTemplate rest;

    @Autowired
    private DepartmentModelService(final RestTemplateBuilder builder) {
        this.rest = builder.setConnectTimeout(2000)
                .setReadTimeout(2000)
                .build();
    }

    public void create(Model model) {
        model.addAttribute("department", new Department());
    }

    public void readAll(ModelMap modelMap) {
        modelMap.put("all", rest.getForObject(HOST + PORT + ROOT + "/all", Department[].class));
    }

    public void save(int id, String name) {
        Department department = new Department();
        department.setId(id);
        department.setName(name);
        if (id == 0)
            rest.postForLocation(HOST + PORT + ROOT + "/create", department);
        else
            rest.put(HOST + PORT + ROOT + "/update", department);
    }

    public void delete(int id) {
        rest.postForLocation(HOST + PORT + ROOT + "/delete", id);
    }

    public void edit(int id, Model model) {
        model.addAttribute("department", readById(id));
    }

    private Department readById(@PathVariable int id) {
        return rest.getForObject(HOST + PORT + ROOT + "/{id}", Department.class, id);
    }

    public void readAvgSalary(ModelMap modelMap) {
        AvgSalaryDepartments[] dep = rest.getForObject(HOST + PORT + ROOT + "/avg", AvgSalaryDepartments[].class);
        modelMap.put("avgsalary", dep);
    }
}
