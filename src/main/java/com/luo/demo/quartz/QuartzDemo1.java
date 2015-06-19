package com.luo.demo.quartz;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;
public class QuartzDemo1 {
	public static void main2(String[] args) {
		try{
			SchedulerFactory factory = new StdSchedulerFactory();
			Scheduler scheduler = factory.getScheduler();
			scheduler.start();
			
			JobDetail job = newJob(HelloJob.class)
					.withIdentity("myJob", "group1").build();
			
			Trigger trigger = newTrigger().withIdentity("myTrigger", "group1").startNow()
					.withSchedule(simpleSchedule().withIntervalInSeconds(40).repeatForever())
					.build();
			
			
			scheduler.scheduleJob(job, trigger);
			
//			scheduler.shutdown();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
class HelloJob implements Job{

	@Override
	public void execute(JobExecutionContext arg0)
			throws JobExecutionException {
		System.out.println("hello");
	}
	
}
