package com.employee_service.serviceImpl;

import com.employee_service.dto.EmployeeRequest;
import com.employee_service.dto.EmployeeResponse;
import com.employee_service.entity.Employee;
import com.employee_service.exception.DuplicateResourceException;
import com.employee_service.exception.ResourceNotFoundException;
import com.employee_service.repository.EmployeeRepo;
import com.employee_service.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    // convert Request DTO to Entity
    private Employee mapToEntity(EmployeeRequest request){
        Employee employee = new Employee();

        employee.setEmpName(request.getEmpName());
        employee.setEmail(request.getEmail());;
        employee.setPhoneNo(request.getPhoneNo());
        employee.setDesignation(request.getDesignation());
        employee.setDepartment(request.getDepartment());
        employee.setSalary(request.getSalary());
        employee.setHireDate(request.getHireDate());

        return employee;
    }

    // convert Entity to Response DTO
    private EmployeeResponse mapToResponse(Employee employee) {

        EmployeeResponse response = new EmployeeResponse();

        response.setId(employee.getId());
        response.setEmployeeCode(employee.getEmployeeCode());
        response.setEmpName(employee.getEmpName());
        response.setEmail(employee.getEmail());
        response.setPhoneNo(employee.getPhoneNo());
        response.setDepartment(employee.getDepartment());
        response.setDesignation(employee.getDesignation());
        response.setSalary(employee.getSalary());
        response.setHireDate(employee.getHireDate());

        return response;
    }

    @Override
    public EmployeeResponse addEmployee(EmployeeRequest request) {

        if (employeeRepo.existsByEmail(request.getEmail())){
            throw new DuplicateResourceException("Email Already Exists...");
        }

        Employee employee = mapToEntity(request);
        employee.setEmployeeCode("EMP-" + UUID.randomUUID().toString().substring(0,5).toUpperCase());
        Employee saveEmployee = employeeRepo.save(employee);

        return mapToResponse(saveEmployee);
    }

    @Override
    public List<EmployeeResponse> getAll() {

        List<Employee> employees = employeeRepo.findAll();

        if (employees.isEmpty()){
            throw new ResourceNotFoundException("No Employees Found..!");
        }

        return employees.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeResponse getByID(Long id) {

        Employee employee = employeeRepo.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Employee Not Found with ID : " + id));
        return mapToResponse(employee);
    }

    @Override
    public EmployeeResponse updateEmployee(Long id, EmployeeRequest request) {

        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee Not Found with ID : " + id));

        employee.setEmpName(request.getEmpName());
        employee.setEmail(request.getEmail());
        employee.setPhoneNo(request.getPhoneNo());
        employee.setDepartment(request.getDepartment());
        employee.setDesignation(request.getDesignation());
        employee.setSalary(request.getSalary());
        employee.setHireDate(request.getHireDate());

        Employee updateEmp = employeeRepo.save(employee);

        return mapToResponse(updateEmp);
    }

    @Override
    public void deleteEmployee(Long id) {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee Not Found with ID : " + id));
        employeeRepo.delete(employee);
    }
}