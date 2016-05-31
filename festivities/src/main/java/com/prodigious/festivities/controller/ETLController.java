package com.prodigious.festivities.controller;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.prodigious.festivities.service.FestivityService;


/**
 * This Controller handle the ETL process to storage information about festivities  
 * @author Juan Joya
 *
 */
@Controller
@RequestMapping("/xmlToDataBase")
public class ETLController {

	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	Job importFestivitiesJob;
	
	@Autowired
	FestivityService festivityService;
	
	/**
	 * Entry point to storage festivities.xml into database
	 * @return url
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String loadInformation() {
		boolean isComplete=false;
		try {
			this.festivityService.deleteAllFestivities();
			
			JobParameters jobParameters = 
					  new JobParametersBuilder()
					  .addLong("time",System.currentTimeMillis()).toJobParameters();
			JobExecution execution = jobLauncher.run(importFestivitiesJob, jobParameters);
			isComplete = execution.getStatus().equals(BatchStatus.COMPLETED);
		} catch (JobExecutionAlreadyRunningException e) {
		} catch (JobRestartException e) {
		} catch (JobInstanceAlreadyCompleteException e) {
		} catch (JobParametersInvalidException e) {
		}
		return "pages/"+(isComplete?"/complete.html":"error.html");
	}

}
