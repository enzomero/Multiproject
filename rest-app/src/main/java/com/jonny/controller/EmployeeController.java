package com.jonny.controller;


import com.jonny.model.Employee;
import com.jonny.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {

    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public void create(@RequestBody Employee employee){
        service.create(employee);
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<Employee> readAll(){
        return service.readAll();
    }

    @RequestMapping(path = "/update", method = RequestMethod.PUT, produces = "application/json", consumes = "application/json")
    public void update(@RequestBody Employee employee){
        service.update(employee);
    }

    @RequestMapping(path = "/delete", method = RequestMethod.POST)
    public void delete(@RequestBody Integer id){
        service.delete(id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Employee read(@PathVariable("id") int id){
        return service.readById(id);
    }

    @RequestMapping(path = "/findByDateRange/{eDate},{oDate}", method = RequestMethod.GET)
    public List<Employee> findByDateBirthRange(@PathVariable("eDate") Date earlyDate, @PathVariable("oDate") Date olderDate){
        return service.findByDateRange(earlyDate, olderDate);
    }
}
