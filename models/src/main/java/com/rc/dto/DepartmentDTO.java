package com.rc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
    private long id;
    private String name;
    private long directorId;
    private List<SectionDTO> sections;

    public DepartmentDTO(String name) {
        this.name = name;
    }
}
