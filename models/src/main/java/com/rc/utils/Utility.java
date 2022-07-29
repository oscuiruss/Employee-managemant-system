package com.rc.utils;

import com.rc.dto.DepartmentDTO;
import com.rc.dto.EmployeeDTO;
import com.rc.dto.SectionDTO;
import com.rc.entitiy.Department;
import com.rc.entitiy.Employee;
import com.rc.entitiy.Section;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class Utility {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static EmployeeDTO employeeConvertToDto(Employee employee) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        System.out.println(employee.getName());
        return modelMapper.map(employee, EmployeeDTO.class);
    }

    public static Employee dtoConverterToEmployee(EmployeeDTO employeeDTO) {
        return modelMapper.map(employeeDTO, Employee.class);
    }

    public static Section dtoConverterToSection(SectionDTO sectionDTO){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(sectionDTO, Section.class);
    }

    public static SectionDTO sectionConvertToDto(Section section) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(section, SectionDTO.class);
    }

    public static DepartmentDTO departmentConvertToDto(Department department) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        return modelMapper.map(department, DepartmentDTO.class);
    }

    public static Department dtoConverterToDepartment(DepartmentDTO departmentDTO){
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper.map(departmentDTO, Department.class);
    }
}