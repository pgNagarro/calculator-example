package com.nagarro.calculator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.calculator.models.Result;

/**
 * Interface for Result repo
 * @author parasgautam
 *
 */
public interface ResultRepository extends JpaRepository<Result,String>{

}
