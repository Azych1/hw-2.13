package skypro.hw2.employeeBook.service;

import skypro.hw2.employeeBook.dto.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee addEmployee(String firstName, String lastName, int department, double salary);

    Employee removeEmployee(String firstName, String lastName);

    Employee getEmployee(String firstName, String lastName);

    Collection<Employee> findAll();
}
