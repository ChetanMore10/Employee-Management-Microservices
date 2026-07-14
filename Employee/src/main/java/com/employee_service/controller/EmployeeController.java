package com.employee_service.controller;

import com.employee_service.dto.EmployeeRequest;
import com.employee_service.dto.EmployeeResponse;
import com.employee_service.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeResponse> addEmp(@Valid @RequestBody EmployeeRequest request){
        EmployeeResponse response = employeeService.addEmployee(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> getAllEmp(){
        List<EmployeeResponse> responseList = employeeService.getAll();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmpByID(@PathVariable Long id){
        EmployeeResponse employeeResponse = employeeService.getByID(id);
        return new ResponseEntity<>(employeeResponse, HttpStatus.OK);
    }

    @GetMapping("/code/{employeeCode}")
    public ResponseEntity<EmployeeResponse> getByEmployeeCode(@PathVariable String employeeCode) {
        EmployeeResponse response = employeeService.getByEmployeeCode(employeeCode);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/name/{empName}")
    public ResponseEntity<List<EmployeeResponse>> getByEmpName(@PathVariable String empName) {

        List<EmployeeResponse> response = employeeService.getByEmpName(empName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponse> updateEmpByID(@PathVariable Long id,@Valid @RequestBody EmployeeRequest request){
        EmployeeResponse response = employeeService.updateEmployee(id,request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmp(@PathVariable Long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("Employee Deleted Successfully..!", HttpStatus.OK);
    }
}
