//package com.rc;
//
//import com.rc.entitiy.Employee;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.concurrent.atomic.AtomicInteger;
//
//import static org.assertj.core.api.Java6Assertions.assertThat;
//
//
//@ExtendWith(SpringExtension.class)
//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//class EmployeeRepositoryTest {
//
//    @Autowired
//    private EmployeeRepository employeeRepository;
//
//    @BeforeEach
////default employee
//    void initUseCase() {
//        List<Employee> employees = Arrays.asList(
//                new Employee("Mikel", "mikel@gmail.com")
//        );
//        employeeRepository.saveAll(employees);
//    }
//
//    @AfterEach
//    public void destroyAll() {
//        employeeRepository.deleteAll();
//    }
//
//    @Test
//    void saveAll_success() {
//        List<Employee> employees = Arrays.asList(
//                new Employee("Mike", "mike@gmail.com"),
//                new Employee("Inna", "innae@gmail.com"),
//                new Employee("Andy", "andy@gmail.com")
//        );
//
//        Iterable<Employee> allCustomer = employeeRepository.saveAll(employees);
//
//        AtomicInteger validIdFound = new AtomicInteger();
//        allCustomer.forEach(customer -> {
//            if (customer.getId() > 0) {
//                validIdFound.getAndIncrement();
//            }
//        });
//        assertThat(validIdFound.intValue()).isEqualTo(3);
//    }
//
//
//    @Test
//    void findAll_success() {
//        List<Employee> allEmployees = employeeRepository.findAll();
//        assertThat(allEmployees.size()).isGreaterThanOrEqualTo(1);
//    }
//
//    @Test
//    void findEmployeeByKeyword() {
//        List<Employee> result = employeeRepository.findEmployeeByKeyword("mikel");
//        assertThat(result.size()).isEqualTo(1);
//        assertThat(result.get(0).getName()).isEqualTo("Mikel");
//    }
//
//}