package com.rc.employee;

import com.rc.EmployeeService;
import com.rc.dto.EmployeeDTO;
import com.rc.entitiy.Employee;
import com.rc.entitiy.Section;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.rc.utils.Utility;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Validated
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    @ResponseBody
    public List<EmployeeDTO> findByKeyword(@RequestParam(required = false) String keyword) {
        return employeeService.findAll().stream().map(Utility::employeeConvertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public EmployeeDTO findById(@PathVariable("id") long id) {
        return Utility.employeeConvertToDto(employeeService.findEmployeeById(id));
    }


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public EmployeeDTO create(@RequestBody @Valid EmployeeDTO employeeDTO) {
        Employee employee = Utility.dtoConverterToEmployee(employeeDTO);
        return  Utility.employeeConvertToDto(employeeService.save(employee));
    }



    @PostMapping("/{id}")
    @ResponseBody
    public EmployeeDTO update(@RequestBody @Valid EmployeeDTO employeeDTO, @PathVariable("id") long id) {
        Employee employee = Utility.dtoConverterToEmployee(employeeDTO);
        return Utility.employeeConvertToDto(employeeService.update(employee, id));
    }

    @GetMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") long id) {
        employeeService.delete(id);
    }

}