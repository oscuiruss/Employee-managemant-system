package com.rc;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.rc.entitiy.Employee;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAll();

    Employee findEmployeeById(long id);

    @Query(value = "SELECT * FROM Employees WHERE lower(concat(name))"
            + "LIKE lower(?1)",
            nativeQuery = true)
    List<Employee> findEmployeeByKeyword(String keyword);
}
