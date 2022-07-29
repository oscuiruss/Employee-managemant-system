package com.rc;

import com.rc.entitiy.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final SectionRepository sectionRepository;

    public EmployeeService(EmployeeRepository employeeRepository, SectionRepository sectionRepository) {
        this.employeeRepository = employeeRepository;
        this.sectionRepository = sectionRepository;
    }

    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    public Employee findEmployeeById(long id){
        return employeeRepository.findById(id).orElse(null);
    }

    public  List<Employee> findEmployeeByKeyword(String keyword){
        return employeeRepository.findEmployeeByKeyword(keyword);
    }

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee update(Employee employee, long id){
        Employee employeeToBeUpdated = employeeRepository.findEmployeeById(id);

//        if (employeeToBeUpdated == null){
//            throw new IllegalArgumentException();
//        }
        employeeToBeUpdated.setName(employee.getName());
        employeeToBeUpdated.setSection(employee.getSection());
        return employeeRepository.save(employeeToBeUpdated);
    }

    public void delete(long id){
        Employee oldEmployee = employeeRepository.findById(id).get();
        if (oldEmployee.getSection() == null || oldEmployee.getSection().getDirector() == null){
        } else {
            if (oldEmployee.getSection().getDirector().getId() == oldEmployee.getId()){
                oldEmployee.getSection().setDirector(null);
                sectionRepository.save(oldEmployee.getSection());
            }
        }
        employeeRepository.deleteById(id);
    }
}