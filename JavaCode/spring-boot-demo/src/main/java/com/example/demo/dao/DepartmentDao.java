package com.example.demo.dao;

import com.example.demo.entities.Department;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class DepartmentDao {
    private static Map<Integer, Department> departments = null;

    static{
        departments = new HashMap<Integer, Department>();
        departments.put(111, new Department(111,"AAA"));
        departments.put(112, new Department(112,"AAB"));
        departments.put(113, new Department(113,"AAC"));
    }

    public Collection<Department> getDepartments(){
        return departments.values();
    }

    public Department getDepartment(Integer id){
        return departments.get(id);
    }

}
