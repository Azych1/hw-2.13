package skypro.hw2.employeeBook.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import skypro.hw2.employeeBook.dto.Employee;
import skypro.hw2.employeeBook.exception.EmployeeNotFoundException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService; //new DepartmentServiceImpl(employeeService);

    private List<Employee> employees = List.of(
            new Employee("Alex", "Alexov", 4, 50_000),
            new Employee("Sasha", "Shanovna", 4, 100_000),
            new Employee("Fill", "Fillov", 1, 150_000));

    @Test
    void maxSalaryEmployee_shouldReturnEmployeeWithMaxSalaryWhenEmployeesInDepartment() {
        when(employeeService.findAll()).thenReturn(employees);

        Employee result = departmentService.maxSalaryEmployee(employees.get(0).getDepartment());

        assertEquals(employees.get(1), result);
    }

    @Test
    void maxSalaryEmployee_shouldThrowExceptionWhenNotEmployeeInDepartment() {
        when(employeeService.findAll()).thenReturn(Collections.emptyList());

        assertThrows(EmployeeNotFoundException.class, () -> departmentService.maxSalaryEmployee(1));
    }

    @Test
    void minSalaryEmployee_shouldReturnEmployeeWithMinSalaryWhenEmployeesInDepartment() {
        when(employeeService.findAll()).thenReturn(employees);

        Employee result = departmentService.minSalaryEmployee(employees.get(0).getDepartment());

        assertEquals(employees.get(0), result);
    }

    @Test
    void minSalaryEmployee_shouldThrowExceptionWhenNotEmployeeInDepartment() {
        when(employeeService.findAll()).thenReturn(Collections.emptyList());

        assertThrows(EmployeeNotFoundException.class, () -> departmentService.minSalaryEmployee(1));
    }

    @Test
    void getEmployeesInDepartment_shouldReturnEmployeesListWhenEmployeesInDepartment() {
        when(employeeService.findAll()).thenReturn(employees);

        Collection<Employee> result = departmentService.getEmployeesInDepartment(employees.get(0).getDepartment());

        assertEquals(List.of(employees.get(0), employees.get(1)), result);
    }

    @Test
    void getEmployeesInDepartment_shouldReturnEmptyListWhenNotEmployeeInDepartment() {
        when(employeeService.findAll()).thenReturn(Collections.emptyList());

        assertEquals(List.of(), departmentService.getEmployeesInDepartment(1));
    }

    @Test
    void getAll_shouldReturnMapWithEmployeeWhenEmployeeInMap() {
        when(employeeService.findAll()).thenReturn(employees);

        Map<Integer, List<Employee>> expectedMap = Map.of(
                employees.get(2).getDepartment(), List.of(employees.get(2)),
                employees.get(0).getDepartment(), List.of(employees.get(0), employees.get(1)));

        Map<Integer, List<Employee>> result = departmentService.getAll();

        assertEquals(expectedMap, result);
    }

    @Test
    void getAll_shouldReturnEmptyListWhenNotEmployeeInMap() {
        when(employeeService.findAll()).thenReturn(Collections.emptyList());

        Map<Integer, List<Employee>> expectedMap = Map.of();

        Map<Integer, List<Employee>> result = departmentService.getAll();

        assertEquals(expectedMap, result);
    }
}