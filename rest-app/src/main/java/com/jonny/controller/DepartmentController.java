package com.jonny.controller;


import com.jonny.model.Department;
import com.jonny.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {

    private final DepartmentService service;

    @Autowired
    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public void create(@RequestBody Department department) {
        service.create(department);
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<Department> readAll() throws Exception {
        return service.readAll();
    }

    @RequestMapping(path = "/update", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public void update(@RequestBody Department department) {
        service.update(department);
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public void delete(@RequestBody Integer id) {
        service.delete(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Department read(@PathVariable("id") int id) throws Exception {
        return service.readById(id);
    }
}
