package com.nagarro.calculator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.calculator.models.Job;

/**
 * Interface for Job repo
 * @author parasgautam
 *
 */
public interface JobRepository extends JpaRepository<Job,Long>{

}

