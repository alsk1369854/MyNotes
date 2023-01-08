package com.example.demo.controller;

import com.example.demo.dao.DepartmentDao;
import com.example.demo.dao.EmployeeDao;
import com.example.demo.entities.Department;
import com.example.demo.entities.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    // 查詢所有員工列表頁面
    @GetMapping("/emps")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();

        model.addAttribute("emps", employees);
        return "emp/list";
    }

    // 進入新增頁面
    @GetMapping("/emp")
    public String toAddPage(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }


    // 添加員工數據
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.info("Add Emp: " + employee);

        employeeDao.save(employee);
//        redirect: //地址重定向
//        forward: //內部傳發
        return "redirect:/emps";
    }

    // 進入員工數據修改頁面
    @GetMapping("/emp/{id}")
    public String toUpdateEmpPage(
            @PathVariable("id") Integer id,
            Model model
    ){
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);

        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);

        return "emp/add";
    }

    // 更新員工訊息
    @PutMapping("/emp")
    public String updateEmp(Employee employee){
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.info("Update Emp: " + employee);
        employeeDao.save(employee);

        return "redirect:/emps";
    }

    // 刪除員工
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        Logger logger = LoggerFactory.getLogger(getClass());
        logger.info("Delete Emp id: " + id);

        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
