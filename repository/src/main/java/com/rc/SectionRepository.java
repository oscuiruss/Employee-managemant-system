package com.rc;

import com.rc.entitiy.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SectionRepository extends JpaRepository<Section, Long> {
    List<Section> findAll();

    Section findSectionById(long id);
}
