//package com.rc;
//
//import com.rc.entitiy.Employee;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.assertj.core.api.Java6Assertions.assertThat;
//import static org.mockito.ArgumentMatchers.any;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.BDDMockito.willDoNothing;
//import static org.mockito.Mockito.*;
//
//
//@ExtendWith(MockitoExtension.class)
//class EmployeeServiceTest {
//    @Mock
//    private EmployeeRepository employeeRepository;
//
//    EmployeeService employeeService;
//
//    @BeforeEach
//    void setUp() {
//        employeeService = new EmployeeService(employeeRepository);
//    }
//
//    @Test
//    void findAllTest() {
//        List<Employee> employees = Arrays.asList(
//                new Employee("Mike", "mike@gmail.com"),
//                new Employee("Inna", "innae@gmail.com"),
//                new Employee("Andy", "andy@gmail.com")
//        );
//        when(employeeRepository.findAll()).thenReturn(employees);
//        List<Employee> result = employeeService.findAll();
//        assertThat(result.size()).isEqualTo(3);
//    }
//
//
//    @Test
//    @DisplayName("test for save method")
//    void saveTest() {
//        Employee employee = new Employee("Denis", "denis@gmail.com");
//        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
//        Employee savedEmployee = employeeRepository.save(employee);
//        assertThat(savedEmployee.getName()).isNotNull();
//    }
//
////    @Test
////    @DisplayName("test for update method")
////    void updateTest() {
////        Employee employee = new Employee("Denis1", "denis1@gmail.com");
////        given(employeeRepository.save(employee)).willReturn(employee);
////        employee.setName("Tony");
////        employee.setEmail("stark@gmail.com");
////        Employee updatedEmployee = employeeService.update(employee);
////        assertThat(updatedEmployee.getEmail()).isEqualTo("stark@gmail.com");
////        assertThat(updatedEmployee.getName()).isEqualTo("Tony");
////    }
//
//    @Test
//    @DisplayName("test for delete method")
//    void deleteTest() {
//        long employeeId = 1L;
//        willDoNothing().given(employeeRepository).deleteById(employeeId);
//        employeeService.delete(employeeId);
//        verify(employeeRepository, times(1)).deleteById(employeeId);
//    }
//}