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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/xmlToDataBase")
public class CargarController {

	@Autowired
	JobLauncher importFile;
	
	@Autowired
	Job importFestivitiesJob;
	
	@RequestMapping(method = RequestMethod.GET)
	public String holaMundo(ModelMap model) {
		boolean isComplete=false;
		try {
			JobParameters jobParameters = 
					  new JobParametersBuilder()
					  .addLong("time",System.currentTimeMillis()).toJobParameters();
			JobExecution execution = importFile.run(importFestivitiesJob, jobParameters);
			isComplete = execution.getStatus().equals(BatchStatus.COMPLETED);
		} catch (JobExecutionAlreadyRunningException e) {
		} catch (JobRestartException e) {
		} catch (JobInstanceAlreadyCompleteException e) {
		} catch (JobParametersInvalidException e) {
		}
		return "pages/"+(isComplete?"/cargaCompleta.html":"errorCarga.html");
	}

}
