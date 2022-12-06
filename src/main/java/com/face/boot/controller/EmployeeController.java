package com.face.boot.controller;

import com.face.boot.model.Department;
import com.face.boot.model.Employee;
import com.face.boot.service.DepartmentService;
import com.face.boot.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    public EmployeeController(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    //not needed
    //@ResponseBody
    @GetMapping("/employees")
    public List<Employee> getEmployeesList(){
        return employeeService.getAllEmployees();
    }

    @RequestMapping("/dep-employees")
    public List<Employee> retrieveEmpFromDep(@RequestParam("id") Long id){
        if(id <= 0 || departmentService.findById(id).isEmpty()) {
            throw new NoSuchElementException();
        }
        return employeeService.getAllEmployeesByDepartmentId(id);
    }

    @RequestMapping(value = "/id", method = RequestMethod.GET, params = "id")
    public ResponseEntity<?> findById(@RequestParam("id") Long id){
        Optional<Employee> employee = employeeService.findById(id);
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.OK).body(employee.orElseThrow(NullPointerException::new));
        }
        if (id > 0) {
            return ResponseEntity.status(HttpStatus.OK).body(employee.orElseThrow(NoSuchElementException::new));
        }
        return ResponseEntity.status(HttpStatus.OK).body(employee.orElseThrow());
    }

    @PostMapping("/{id}/department/{depId}")
    public Employee assignDepToEmployee(@PathVariable("id") Long id, @PathVariable("depId") Long depId){
        Employee employee = employeeService.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException();
        });
        Department department = departmentService.findById(depId).orElseThrow(() -> {
            throw new NoSuchElementException();
        });
        employee.setDepartment(department);
        return employeeService.saveEmployees(employee);
    }

    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee){
        return employeeService.saveEmployees(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id){
        employeeService.deleteEmployees(id);
    }
}
