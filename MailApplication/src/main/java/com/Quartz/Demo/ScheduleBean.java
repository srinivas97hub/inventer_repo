package com.Quartz.Demo;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

@Startup
@Singleton
public class ScheduleBean implements Serializable{
	@PostConstruct
	public void init() {
		System.out.println("======Start=========");

		try {
			System.out.println("================try==============");
			JobDetail job = JobBuilder.newJob(QuartzJob.class).build();
			Trigger cronTrigger = TriggerBuilder.newTrigger()
					.withIdentity("myCronTrigger", "cronGroup")
					.withSchedule(CronScheduleBuilder.cronSchedule("0 31 15 * * ?")) 
					.build();
			Scheduler sc = StdSchedulerFactory.getDefaultScheduler();
			sc.start();
			sc.scheduleJob(job, cronTrigger);
		//	sc.shutdown();
		} catch (SchedulerException e) {
			System.out.println("===============catch==============");
			e.printStackTrace();
		}

		System.out.println("=============Trigger is ready=========");
	}
}

