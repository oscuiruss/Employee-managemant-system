package com.rc;


import com.rc.entitiy.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;



public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findAll();

    Department findDepartmentById(long id);

    @Query(value = "SELECT * FROM Department WHERE lower(concat(name))"
            + "LIKE lower(concat('%', ?1, '%'))",
            nativeQuery = true)
    List<Department> findDepartmentByKeyword(String keyword);
}

