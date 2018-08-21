package com.anuk.demo.dao;


import org.springframework.data.repository.CrudRepository;

import com.anuk.demo.model.Employee;

public interface employeeDao extends CrudRepository<Employee, Integer>

{
}
