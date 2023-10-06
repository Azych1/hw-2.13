package skypro.hw2.employeeBook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Превышен лимит сотрудников в фирме")
public class EmployeeStorageIsFullException extends RuntimeException {

}