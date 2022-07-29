package com.rc.employee;

import com.rc.DepartmentService;
import com.rc.EmployeeService;
import com.rc.SectionService;
import com.rc.dto.SectionDTO;
import com.rc.entitiy.Section;
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
@RequestMapping("/sections")
public class SectionController {
    private final SectionService sectionService;
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    SectionController(SectionService sectionService, EmployeeService employeeService, DepartmentService departmentService) {
        this.sectionService = sectionService;
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @GetMapping()
    @ResponseBody
    public List<SectionDTO> findByKeyword(@RequestParam(required = false) String keyword) {
        return sectionService.findAll().stream().map(Utility::sectionConvertToDto)
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    @ResponseBody
    public SectionDTO findById(@PathVariable("id") long id) {
        return Utility.sectionConvertToDto(sectionService.findSectionById(id));
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public SectionDTO create(@Valid @RequestBody SectionDTO sectionDTO) {
        Section section = Utility.dtoConverterToSection(sectionDTO);
        section.setDirector(employeeService.findEmployeeById(sectionDTO.getId()));
        System.out.println(section);
        return Utility.sectionConvertToDto(sectionService.save(section));
    }


    @PostMapping("/{id}")
    @ResponseBody
    public SectionDTO update(@RequestBody @Valid SectionDTO sectionDTO, @PathVariable("id") long id) {
        Section section = Utility.dtoConverterToSection(sectionDTO);
        section.setDirector(employeeService.findEmployeeById(sectionDTO.getDirectorId()));
        section.setDepartment(departmentService.findDepartmentById(sectionDTO.getDepartmentId()));
        return Utility.sectionConvertToDto(sectionService.update(section, id, sectionDTO.getDirectorId(), 1));
    }

    @GetMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") int id) {
        sectionService.delete(id);
    }
}
