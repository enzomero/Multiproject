package com.jonny.model;

import org.springframework.stereotype.Repository;

@Repository
public class Department {
    private int id;
    private String name;

    public Department(){}

    public Department(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public String toString() {
        return " Department { ID = "+id+", NAME = '"+name+"' }";
    }

    public boolean isValid(){
        if(this.getName() != null)
            if(!this.getName().isEmpty())
                return true;
        return false;
    }
}
