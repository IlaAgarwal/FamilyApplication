package com.wagawin.family.controller.batch;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.SimpleBatchConfiguration;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.core.step.tasklet.TaskletStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * @Author Ila Agarwal
 * Batch Configuration File.Uses @{@link Tasklet} to perform the job
 */
@Configuration
@EnableBatchProcessing
public class JobBatchConfiguration {

	//Logger
	private final Logger logger = LoggerFactory
			.getLogger(JobBatchConfiguration.class);

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	private SimpleBatchConfiguration jobLauncher;

	@Autowired
	Tasklet parentSummaryTasklet;


	/**
	 * Scheduler to scheule the job, runs every 15 minutes
	 * @throws Exception
	 */
	@Scheduled(fixedDelay = 900000)
	public void perform() throws Exception {

		logger.info("Job has started", new Date());

		JobParameters param = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();

		JobExecution execution = jobLauncher.jobLauncher().
				run(parentSummaryJob(step()), param);

		logger.info("job finished with executoion Status:",execution.getStatus() , new Date());
	}


	/**
	 * This job involves single step
	 * @param step
	 * @return
	 */
	@Bean
	public Job parentSummaryJob(Step step) {
		Job job = jobBuilderFactory.get("parentSummaryJob")
				.flow(step)
				.end()
				.build();
		return job;
	}

	/**
	 * Step to perform the Task
	 * @return Step
	 */
	@Bean
	public Step step(){
		TaskletStep step = stepBuilderFactory.get("step")
				.tasklet(parentSummaryTasklet)
				.build();
		return step;
	}

}
