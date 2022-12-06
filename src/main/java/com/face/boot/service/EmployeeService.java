package com.face.boot.service;

import com.face.boot.model.Employee;
import com.face.boot.repository.EmployeeRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import java.util.List;
import java.util.Optional;
@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //read
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> findById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee saveEmployees(Employee employee) {
        return employeeRepository.saveAndFlush(employee);
    }

    public List<Employee> getAllEmployeesByDepartmentId(Long departmentId) {
        return employeeRepository.findAllByDepartmentId(departmentId);
    }

    public void deleteEmployees(Long id) {
        employeeRepository.deleteById(id);
    }
}
