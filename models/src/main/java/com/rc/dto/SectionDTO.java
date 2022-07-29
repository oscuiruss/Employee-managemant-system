package com.rc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectionDTO {
    private long id;
    @NotEmpty(message = "Name can't be empty")
    @Size(min = 2, max = 20, message = "Name can't consist of less than 2 and more than 20 letters")
    private String name;

    private long directorId;

    private List<EmployeeDTO> employees;

    private long departmentId;

    public SectionDTO(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
    }
}