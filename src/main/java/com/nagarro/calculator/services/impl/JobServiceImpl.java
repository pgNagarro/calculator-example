package com.nagarro.calculator.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.calculator.models.Job;
import com.nagarro.calculator.repositories.JobRepository;
import com.nagarro.calculator.services.JobService;
/**
 * Service Implementation Class for Job Service
 * @author parasgautam
 *
 */
@Service
public class JobServiceImpl implements JobService{
	
	@Autowired
	private JobRepository jobRepository;
	
	@Override
	public void addJob(Job job) {
		jobRepository.save(job);
	}
}
