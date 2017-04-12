package com.jonny.service;

import com.jonny.model.AvgSalaryDepartments;
import com.jonny.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Component
public class DepartmentModelService {

    private final static String PORT = "9000";
    private final static String LOCALHOST = "http://localhost:";

    private final RestTemplate rest;

    @Autowired
    private DepartmentModelService(final RestTemplateBuilder builder){
        this.rest = builder.setConnectTimeout(2000)
                .setReadTimeout(2000)
                .build();
    }

    public void create(Model model){
        model.addAttribute("department", new Department());
    }

    public void readAll(ModelMap modelMap){
        modelMap.put("all", rest.getForObject(LOCALHOST+PORT+"/department/all", Department[].class));
    }

    public void save(int id, String name){
            Department department = new Department();
            department.setId(id);
            department.setName(name);
        if(id==0)
            rest.postForLocation(LOCALHOST+PORT+"/department/create", department);
        else
            rest.put(LOCALHOST+PORT+"/department/update", department);
    }

    public void delete(int id){
        rest.postForLocation(LOCALHOST+PORT+"/department/delete", id);
    }

    public void edit(int id, Model model){
        model.addAttribute("department", readById(id));
    }

    private Department readById(@PathVariable int id){
        return rest.getForObject(LOCALHOST+PORT+"/department/{id}", Department.class, id);
    }

    public void readAvgSalary(ModelMap modelMap){
        AvgSalaryDepartments[] dep = rest.getForObject("http://localhost:9000/department/avg", AvgSalaryDepartments[].class);
        modelMap.put("avgsalary", dep);
    }
}
