package com.rc;


import com.rc.entitiy.Section;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SectionService {
    private final SectionRepository sectionRepository;
    private final EmployeeRepository employeeRepository;

    public SectionService(SectionRepository sectionRepository, EmployeeRepository employeeRepository) {
        this.sectionRepository = sectionRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Section> findAll() {
        return sectionRepository.findAll();
    }

    public Section findSectionById(long id) {
        return sectionRepository.findById(id).orElse(null);
    }

    public Section save(Section section) {
        return sectionRepository.save(section);
    }

    public Section update(Section section, long id, long directorId, long depId) {
        Section sectionToBeUpdated = sectionRepository.findSectionById(id);
        if (sectionToBeUpdated == null) {
            throw new IllegalArgumentException();
        }
        sectionToBeUpdated.setDirector(employeeRepository.findEmployeeById(directorId));
        sectionToBeUpdated.setEmployees(section.getEmployees());
        sectionToBeUpdated.setName(section.getName());
        return sectionRepository.save(sectionToBeUpdated);
    }

    public void delete(long id) {
        sectionRepository.deleteById(id);
    }
}
