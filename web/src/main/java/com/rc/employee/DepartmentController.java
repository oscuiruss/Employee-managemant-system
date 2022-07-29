package com.rc.employee;


import com.rc.DepartmentService;
import com.rc.EmployeeService;
import com.rc.dto.DepartmentDTO;
import com.rc.entitiy.Department;
import com.rc.utils.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    DepartmentController(DepartmentService departmentService, EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
    }

    @GetMapping()
    @ResponseBody
    public List<DepartmentDTO> findByKeyword(@RequestParam(required = false) String keyword) {
        return departmentService.findAll().stream().map(Utility::departmentConvertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public DepartmentDTO findById(@PathVariable("id") long id) {
        return Utility.departmentConvertToDto(departmentService.findDepartmentById(id));
    }


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public DepartmentDTO create(@Valid @RequestBody DepartmentDTO departmentDTO) {
        Department department = Utility.dtoConverterToDepartment(departmentDTO);
        department.setDirector(employeeService.findEmployeeById(departmentDTO.getDirectorId()));
        return  Utility.departmentConvertToDto(departmentService.save(department));
    }


    @ResponseBody
    @PostMapping("/{id}")
    public DepartmentDTO update(@RequestBody DepartmentDTO departmentDTO, @PathVariable("id") long id) {
        Department department = Utility.dtoConverterToDepartment(departmentDTO);
        department.setDirector(employeeService.findEmployeeById(departmentDTO.getDirectorId()));
        return  Utility.departmentConvertToDto(departmentService.update(department, id, departmentDTO.getDirectorId()));
    }

    @GetMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") long id) {
        departmentService.delete(id);
    }

}

