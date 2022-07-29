package com.rc.employee;

import com.rc.EmployeeService;
import com.rc.dto.EmployeeDTO;
import com.rc.entitiy.Employee;
import com.rc.entitiy.Section;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class EmployeeControllerTest {
    private final String ALL_EMPLOYEES_RESOURCE_URL = "http://localhost:8080/employees";
    private final String DELETE_RESOURCE_URL = "http://localhost:8080/employees/delete";
    private final String CREATE_RESOURCE_URL = "http://localhost:8080/employees/create";

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    void findByIdWhenExists() {
        given(employeeService.findEmployeeById(1))
                .willReturn(new Employee("Mike", "mike@gmail.com"));
        ResponseEntity<EmployeeDTO> employeeResponse = restTemplate.getForEntity(ALL_EMPLOYEES_RESOURCE_URL + "/1", EmployeeDTO.class);
        assertThat(employeeResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(employeeResponse.getBody()).isEqualTo(new EmployeeDTO("Mike", "mike@gmail.com"));
    }


    @Test
    void canCreateEmployeeSuccess() {
        Section section = new Section();
        section.setId(1L);
        Employee employee = new Employee( 1, "Mike", "mike@gmail.com", section);
        given(employeeService.save(employee))
                .willReturn(employee);
        ResponseEntity<EmployeeDTO> employeeResponse = restTemplate.postForEntity(CREATE_RESOURCE_URL,
                new EmployeeDTO(1, "Mike", "mike@gmail.com", 1), EmployeeDTO.class);
        assertThat(employeeResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void canCreateEmployeeBadRequest() {
        Section section = new Section();
        section.setId(1);
        Employee employee = new Employee( 1, "M", "mike@gmail.com", section);
        given(employeeService.save(employee))
                .willReturn(employee);
        ResponseEntity<EmployeeDTO> employeeResponse = restTemplate.postForEntity(CREATE_RESOURCE_URL,
                new EmployeeDTO(1, "M", "mike@gmail.com", 1), EmployeeDTO.class);
        assertThat(employeeResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void update() {
        Section section = new Section();
        section.setId(1);
        Employee employee = new Employee( 1, "Mike2", "mike2@gmail.com", section);
        given(employeeService.update(employee, 1)).willReturn(employee);
        ResponseEntity<EmployeeDTO> employeeResponse = restTemplate.postForEntity(ALL_EMPLOYEES_RESOURCE_URL + "/1",
                new EmployeeDTO(1,"Mike2", "mike2@gmail.com", 1), EmployeeDTO.class);
        assertThat(employeeResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(employeeResponse.getBody().getName()).isEqualTo("Mike2");
    }

    @Test
    void delete() {
        willDoNothing().given(employeeService).delete(1);
        restTemplate.getForEntity(DELETE_RESOURCE_URL + "/1", EmployeeDTO.class);
        verify(employeeService, times(1)).delete(1);
    }
}