package com.face.boot.bean;

import com.face.boot.controller.okHttp;
import com.face.boot.dto.Department;
import com.face.boot.model.Employee;
import com.face.boot.repository.EmployeeRepository;
import com.google.gson.Gson;
import okhttp3.HttpUrl;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Controller
public class EmployeeBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private final EmployeeRepository employeeRepository;
    private List<Employee> employees;
    private List<com.face.boot.dto.Employee> depEmployees;
    private Employee employee = new Employee();
    private Long depId;
    private Department department;

    public EmployeeBean(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @PostConstruct
    public void load() {
        employees = employeeRepository.findAll();
    }
//    public void empByDepId() {
//        depEmployees = employeeRepository.findAllByDepartmentId(depId);
//    }
    public void loadDepById() throws IOException {
        department = okHttp.getDepById(depId);
        depEmployees = department.getEmployeeHashSet();
    }

    public Long getDepId() {
        return depId;
    }
    public void setDepId(Long depId) {
        this.depId = depId;
    }
    public void setDepEmployees(List<com.face.boot.dto.Employee> depEmployees){
        this.depEmployees = depEmployees;
    }
    public List<com.face.boot.dto.Employee> getDepEmployees() {
        return depEmployees;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    public Employee getEmployee() {
        return employee;
    }
}
