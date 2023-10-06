package skypro.hw2.employeeBook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import skypro.hw2.employeeBook.dto.Employee;
import skypro.hw2.employeeBook.service.DepartmentService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(value = "/max-salary")
    public Employee getMaxByDepartment(@RequestParam int departmentId) {
        return departmentService.maxSalaryEmployee(departmentId);
    }

    @GetMapping(value = "/min-salary")
    public Employee getMinByDepartment(@RequestParam int departmentId) {
        return departmentService.minSalaryEmployee(departmentId);
    }

    @GetMapping(value = "/all", params = "department")
    public Collection<Employee> getAllByDepartment(@RequestParam int department) {
        return departmentService.getEmployeesInDepartment(department);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> getAll() {
        return departmentService.getAll();
    }
}
