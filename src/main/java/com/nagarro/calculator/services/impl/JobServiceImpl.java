package com.nagarro.calculator.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private static final Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);
	
	@Autowired
	private JobRepository jobRepository;
	
	/**
	 * Method to add job for every time when result is calculated
	 */
	@Override
	public void addJob(Job job) {
		
		logger.info("start: addJob");
		jobRepository.save(job);
		
	}
}
