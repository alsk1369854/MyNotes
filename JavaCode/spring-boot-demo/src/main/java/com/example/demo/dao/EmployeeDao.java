package com.example.demo.dao;

import com.example.demo.entities.Department;
import com.example.demo.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {

    private static Map<Integer, Employee> employees = null;

    @Autowired
    private DepartmentDao departmentDao;

    static{
        employees = new HashMap<Integer, Employee>();
        employees.put(1001, new Employee(1001, "Ming", "123@123", 1, new Department(111, "AAA"), new Date()));
        employees.put(1002, new Employee(1002, "Han", "124@123", 1, new Department(111, "AAA"), new Date()));
        employees.put(1003, new Employee(1003, "Susan", "125@123", 0, new Department(112, "AAB"), new Date()));
        employees.put(1004, new Employee(1004, "Mark", "126@123", 1, new Department(113, "AAC"), new Date()));
    }

    private static Integer initId = 1005;

    public void save(Employee employee){
        if(employee.getId() == null){
            employee.setId(initId++);
        }

        employee.setDepartment(departmentDao.getDepartment(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    public Collection<Employee> getAll(){
        return employees.values();
    };

    public Employee get(Integer id){
        return employees.get(id);
    }

    public void delete(Integer id){
        employees.remove(id);
    }
}
