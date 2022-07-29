//package com.rc.utils;
//
//import com.rc.dto.EmployeeDTO;
//import com.rc.entitiy.Employee;
//import com.rc.entitiy.Section;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
//
//class UtilityTest {
//
//    @Test
//    void testEmployeeConvertToDto() {
//        Section section = new Section(1, "Section1", new ArrayList<>());
//        Employee employee = new Employee(1, "Mike", "mike@gmail.com", section);
//        EmployeeDTO employeeDTO = Utility.employeeConvertToDto(employee);
//        assertEquals(new EmployeeDTO(1, "Mike", "mike@gmail.com", 1), employeeDTO);
//    }
////
////    @Test
////    void testDtoConverterToEmployee() {
////        Section section = new Section(1, "Section1", new ArrayList<>());
////        EmployeeDTO employeeDTO = new EmployeeDTO(1, "Mike", "mike@gmail.com", 1);
////        Employee employee = Utility.dtoConverterToEmployee(employeeDTO);
////        assertThat(employee.getName()).isEqualTo("Mike");
////        assertThat(employee.getId()).isEqualTo(1);
////        assertThat(employee.getSection().getId()).isEqualTo(1);
////    }
//}