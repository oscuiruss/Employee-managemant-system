package com.rc.employee;

import com.rc.DepartmentService;
import com.rc.EmployeeService;
import com.rc.SectionService;
import com.rc.dto.EmployeeDTO;
import com.rc.dto.SectionDTO;
import com.rc.entitiy.Department;
import com.rc.entitiy.Employee;
import com.rc.entitiy.Section;
import org.aspectj.lang.annotation.Before;
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
public class SectionControllerTest {
    private final String ALL_SECTIONS_RESOURCE_URL = "http://localhost:8080/sections";
    private final String DELETE_RESOURCE_URL = "http://localhost:8080/sections/delete";
    private final String CREATE_RESOURCE_URL = "http://localhost:8080/sections/create";

    @MockBean
    private SectionService sectionService;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private DepartmentService departmentService;

    @Autowired
    private TestRestTemplate restTemplate;


    private Department department = null;
    private Employee employee1 = null;
    private Employee employee2 = null;
    private EmployeeDTO employeeDTO1 = null;
    private EmployeeDTO employeeDTO2 = null;
    private ArrayList<Employee> employees = null;
    private ArrayList<EmployeeDTO> employeeDTOS = null;
    private SectionDTO sectionDTO = null;
    private Section sectionWithoutDepartment = null;
    private ArrayList<SectionDTO> sectionDTOS = null;

    @BeforeEach
    public void setUp() {
        employee1 = new Employee();
        employee2 = new Employee();

        employee1.setId(1);
        employee2.setId(2);

        department = new Department();
        department.setId(1);

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

        sectionWithoutDepartment = new Section(1, "Section", employee1, null, employees);
        sectionDTO = new SectionDTO(1, "Section", 1, employeeDTOS, 1);

        given(employeeService.findEmployeeById(1)).willReturn(employee1);
        given(departmentService.findDepartmentById(1)).willReturn(department);

    }


    @Test
    void findByIdWhenExists() {
        given(sectionService.findSectionById(1))
                .willReturn(new Section("section"));
        ResponseEntity<SectionDTO> sectionResponse = restTemplate.getForEntity(ALL_SECTIONS_RESOURCE_URL + "/1", SectionDTO.class);
        assertThat(sectionResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(sectionResponse.getBody()).isEqualTo(new SectionDTO("section"));
    }


    @Test
    void canCreateSectionSuccess() {
        given(sectionService.save(sectionWithoutDepartment)).willReturn(sectionWithoutDepartment);
        ResponseEntity<SectionDTO> sectionResponse = restTemplate.postForEntity(CREATE_RESOURCE_URL, sectionDTO, SectionDTO.class);
        assertThat(sectionResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    void canCreateEmployeeBadRequest() {
        Section badSection = new Section(1, "S", employee1, department, employees);
        given(sectionService.save(badSection)).willReturn(badSection);
        ResponseEntity<SectionDTO> sectionResponse = restTemplate.postForEntity(CREATE_RESOURCE_URL,
                new SectionDTO(1, "S", 1, employeeDTOS, 1), SectionDTO.class);
        assertThat(sectionResponse.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    void update() {
        Section section = new Section(1, "Section2", employee1, department, employees);
        ArrayList<Section> sections = new ArrayList<>();
        sections.add(section);
        department.setSections(sections);
        given(sectionService.update(section, 1, 1, 1)).willReturn(section);
        ResponseEntity<SectionDTO> sectionResponse = restTemplate.postForEntity(ALL_SECTIONS_RESOURCE_URL + "/1",
                new SectionDTO(1, "Section2", 1, employeeDTOS, 1), SectionDTO.class);
        assertThat(sectionResponse.getBody().getName()).isEqualTo("Section2");
    }

    @Test
    void delete() {
        willDoNothing().given(sectionService).delete(1);
        restTemplate.getForEntity(DELETE_RESOURCE_URL + "/1", SectionDTO.class);
        verify(sectionService, times(1)).delete(1);
    }
}
