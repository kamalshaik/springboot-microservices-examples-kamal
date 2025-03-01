package com.skh;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.skh.O2M.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
