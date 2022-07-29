package com.rc.employee;

import com.rc.DepartmentService;
import com.rc.EmployeeService;
import com.rc.dto.DepartmentDTO;
import com.rc.dto.EmployeeDTO;
import com.rc.dto.SectionDTO;
import com.rc.entitiy.Department;
import com.rc.entitiy.Employee;
import com.rc.entitiy.Section;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class DepartmentControllerTest {
    private final String ALL_DEPARTMENTS_RESOURCE_URL = "http://localhost:8080/departments";
    private final String DELETE_RESOURCE_URL = "http://localhost:8080/departments/delete";
    private final String CREATE_RESOURCE_URL = "http://localhost:8080/departments/create";

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private DepartmentService departmentService;

    @Autowired
    private TestRestTemplate restTemplate;


    private Department department = null;
    private DepartmentDTO departmentDTO = null;
    private Employee employee1 = null;
    private Employee employee2 = null;
    private EmployeeDTO employeeDTO1 = null;
    private EmployeeDTO employeeDTO2 = null;
    private ArrayList<Employee> employees = null;
    private ArrayList<EmployeeDTO> employeeDTOS = null;
    private ArrayList<Section> sections = null;
    private ArrayList<SectionDTO> sectionDTOS = null;

    @BeforeEach
    public void setUp() {
        employee1 = new Employee();
        employee2 = new Employee();

        employee1.setId(1);
        employee2.setId(2);

        employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);

        employeeDTO1 = new EmployeeDTO();
        employeeDTO2 = new EmployeeDTO();
        employeeDTO1.setId(1);
        employeeDTO2.setId(2);

        employeeDTOS = new ArrayList<>();
        employeeDTOS.add(employeeDTO1);
        employeeDTOS.add(employeeDTO2);
        department = new Department(1, "department", employee1, sections);
        sections = new ArrayList<>();
        department.setSections(sections);
        sectionDTOS = new ArrayList<>();

        department = new Department(1, "department", employee1, sections);
        departmentDTO = new DepartmentDTO(1, "department", 1, sectionDTOS);

        given(employeeService.findEmployeeById(1)).willReturn(employee1);
        given(departmentService.findDepartmentById(1)).willReturn(department);

    }

    @Test
    void findById() {
        ResponseEntity<DepartmentDTO> departmentResponse =
                restTemplate.getForEntity(ALL_DEPARTMENTS_RESOURCE_URL + "/1", DepartmentDTO.class);
        assertThat(departmentResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(departmentResponse.getBody()).isEqualTo(departmentDTO);
    }

    @Test
    void create() {
        given(departmentService.save(department)).willReturn(department);
        ResponseEntity<DepartmentDTO> departmentResponse =
                restTemplate.postForEntity(CREATE_RESOURCE_URL, departmentDTO, DepartmentDTO.class);
        System.out.println(departmentDTO);
        assertThat(departmentResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void update() {
        Department department = new Department(1, "Dep2", employee1, sections);
        given(departmentService.update(department, 1,  1)).willReturn(department);
        ResponseEntity<DepartmentDTO> departmentResponse = restTemplate.postForEntity(ALL_DEPARTMENTS_RESOURCE_URL + "/1",
                new DepartmentDTO(1, "Dep2", 1, sectionDTOS), DepartmentDTO.class);
        System.out.println("section response = " + departmentResponse.getBody());
        System.out.println(departmentResponse.getStatusCode());
        System.out.println(departmentResponse.getStatusCodeValue());
        assertThat(departmentResponse.getBody().getName()).isEqualTo("Dep2");
    }

    @Test
    void delete() {
        willDoNothing().given(departmentService).delete(1);
        restTemplate.getForEntity(DELETE_RESOURCE_URL + "/1", DepartmentDTO.class);
        verify(departmentService, times(1)).delete(1);
    }
}