package skypro.hw2.employeeBook.service;

import org.springframework.stereotype.Service;
import skypro.hw2.employeeBook.dto.Employee;
import skypro.hw2.employeeBook.exception.EmployeeNotFoundException;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee maxSalaryEmployee(int department) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .max(Comparator.comparingDouble(empl -> empl.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException());
    }

    @Override
    public Employee minSalaryEmployee(int department) {
        return employeeService.findAll().stream()
                .filter(employee -> employee.getDepartment() == department)
                .min(Comparator.comparingDouble(empl -> empl.getSalary()))
                .orElseThrow(()-> new EmployeeNotFoundException());
    }

    @Override
    public Collection<Employee> getEmployeesInDepartment(int department) {

        return employeeService.findAll().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public Map<Integer, List<Employee>> getAll() {

        return employeeService.findAll().stream()
                .collect(Collectors.groupingBy(employee -> employee.getDepartment()));
    }
}
