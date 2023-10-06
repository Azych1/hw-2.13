package skypro.hw2.employeeBook.service;

import org.springframework.stereotype.Service;
import skypro.hw2.employeeBook.dto.Employee;
import skypro.hw2.employeeBook.exception.EmployeeAlreadyAddedException;
import skypro.hw2.employeeBook.exception.EmployeeNotFoundException;
import skypro.hw2.employeeBook.exception.EmployeeStorageIsFullException;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private Map<String, Employee> employeeMap;

    private static final int EMPLOYEE_SIZE = 3;

    public EmployeeServiceImpl() {

        this.employeeMap = new HashMap<>();
    }

    @Override
    public Employee addEmployee(String firstName, String lastName, int department, double salary) {
        if (employeeMap.size() == EMPLOYEE_SIZE) {
            throw new EmployeeStorageIsFullException();
        }

        Employee employee = new Employee(firstName, lastName, department, salary);
        String key = generateKey(firstName, lastName);

        if (employeeMap.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }

        employeeMap.put(key, employee);

        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {

        String key = generateKey(firstName, lastName);

        Employee employee = employeeMap.remove(key);

        if (employee == null) {
            throw new EmployeeNotFoundException();
        }

        return employee;
    }

    @Override
    public Employee getEmployee(String firstName, String lastName) {
        String key = generateKey(firstName, lastName);

        Employee employee = employeeMap.get(key);

        if (employee == null) {
            throw new EmployeeNotFoundException();
        }

        return employee;
    }

    @Override
    public Collection<Employee> findAll() {
        return employeeMap.values();
    }

    private String generateKey(String firstName, String lastName) {
        return firstName + lastName;
    }

}
