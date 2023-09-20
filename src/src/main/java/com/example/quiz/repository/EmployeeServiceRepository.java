package com.example.quiz.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.quiz.entity.Employee;

public interface EmployeeServiceRepository extends CrudRepository<Employee, Integer> {

	@Query("select * from employee where name = :name limit 1")
	Employee selectByName(@Param("name") String name);
}
