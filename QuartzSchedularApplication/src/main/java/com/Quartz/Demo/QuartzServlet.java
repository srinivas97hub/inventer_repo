package com.Quartz.Demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

@WebServlet("/QuartzServlet")
public class QuartzServlet extends HttpServlet {

   
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, 
      HttpServletResponse response)
      throws ServletException, IOException {
		 response.getWriter().println("======Start=========");

	        try {
	        	response.getWriter().println("================try==============");
				JobDetail job = JobBuilder.newJob(QuartzJob.class).build();
				Trigger cronTrigger = TriggerBuilder.newTrigger()
					    .withIdentity("myCronTrigger", "cronGroup")
					    .withSchedule(CronScheduleBuilder.cronSchedule("0 44 10 * * ?")) 
					    .build();
				Scheduler sc = StdSchedulerFactory.getDefaultScheduler();
				sc.start();
				sc.scheduleJob(job, cronTrigger);
			} catch (SchedulerException e) {
				 response.getWriter().println("===============catch==============");
				e.printStackTrace();
			}

	        response.getWriter().println("=============Trigger is ready=========");
    }
}
