package skypro.hw2.employeeBook.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import skypro.hw2.employeeBook.dto.Employee;
import skypro.hw2.employeeBook.exception.EmployeeAlreadyAddedException;
import skypro.hw2.employeeBook.exception.EmployeeNotFoundException;
import skypro.hw2.employeeBook.exception.EmployeeStorageIsFullException;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    private EmployeeServiceImpl underTest;

    @BeforeEach
    void beforeEach() {
        underTest = new EmployeeServiceImpl();
    }

    Employee expectedEmployee = new Employee("Alex", "Alexov", 4, 50_000);


    @Test
    void addEmployee_shouldAddEmployeeToMapAndReturnEmployee() {

        Employee result = underTest.addEmployee(expectedEmployee.getFirstName(),
                expectedEmployee.getLastName(), expectedEmployee.getDepartment(),
                expectedEmployee.getSalary());

        assertTrue(underTest.findAll().contains(expectedEmployee));
        assertEquals(expectedEmployee, result);
    }

    @Test
    void addEmployee_shouldThrowExceptionWhenNotEnoughMapSize() {
        for (int i = 0; i < 3; i++) {
            underTest.addEmployee((expectedEmployee.getFirstName() + i),
                    (expectedEmployee.getLastName() + i), expectedEmployee.getDepartment(),
                    expectedEmployee.getSalary());
        }

        EmployeeStorageIsFullException employeeStorageIsFullException = assertThrows(EmployeeStorageIsFullException.class,
                () -> underTest.addEmployee(expectedEmployee.getFirstName(), expectedEmployee.getLastName(),
                        expectedEmployee.getDepartment(), expectedEmployee.getSalary()));


    }

    @Test
    void addEmployee_shouldThrowExceptionWhenEqualEmployeeInMap() {
        underTest.addEmployee(expectedEmployee.getFirstName(), expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(), expectedEmployee.getSalary());

        assertThrows(EmployeeAlreadyAddedException.class, () -> underTest.addEmployee(
                expectedEmployee.getFirstName(), expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(), expectedEmployee.getSalary()));
    }

    @Test
    void removeEmployee_shouldRemoveAddedEmployeeAndReturnEmptyMap() {
        underTest.addEmployee(expectedEmployee.getFirstName(), expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(), expectedEmployee.getSalary());

        underTest.removeEmployee(expectedEmployee.getFirstName(), expectedEmployee.getLastName());

        assertTrue(underTest.findAll().contains(expectedEmployee) == false);
    }

    @Test
    void removeEmployee_shouldThrowExceptionWhenTryRemoveEmployeeFromEmptyMap() {
        underTest.addEmployee(expectedEmployee.getFirstName(), expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(), expectedEmployee.getSalary());

        underTest.removeEmployee(expectedEmployee.getFirstName(), expectedEmployee.getLastName());

        assertThrows(EmployeeNotFoundException.class, () -> underTest.removeEmployee(
                expectedEmployee.getFirstName(), expectedEmployee.getLastName()));
    }

    @Test
    void getEmployee_shouldReturnEmployeeWhenThisEmployeeInMap() {
        underTest.addEmployee(expectedEmployee.getFirstName(), expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(), expectedEmployee.getSalary());

        Employee employee = underTest.getEmployee(expectedEmployee.getFirstName(), expectedEmployee.getLastName());
        assertEquals(expectedEmployee, employee);

    }

    @Test
    void getEmployee_shouldThrowsExceptionWhenTryGetEmployeeFromEmptyMap() {
        assertThrows(EmployeeNotFoundException.class, () -> underTest.getEmployee(
                expectedEmployee.getFirstName(), expectedEmployee.getLastName()));
    }

    @Test
    void findAll_shouldReturnEmployeesListWhenEmployeeInMap() {
        Employee employee = new Employee("Lara", "Kroft", 1, 100_000);
        underTest.addEmployee(expectedEmployee.getFirstName(), expectedEmployee.getLastName(),
                expectedEmployee.getDepartment(), expectedEmployee.getSalary());
        underTest.addEmployee(employee.getFirstName(), employee.getLastName(),
                employee.getDepartment(), employee.getSalary());

        Collection<Employee> result = underTest.findAll();
        assertTrue(result.containsAll(List.of(expectedEmployee, employee)));


    }

    @Test
    void findAll_shouldReturnEmptyListWhenEmployeeNotInMap() {
        Collection<Employee> all = underTest.findAll();
        assertTrue(all.isEmpty());
    }
}