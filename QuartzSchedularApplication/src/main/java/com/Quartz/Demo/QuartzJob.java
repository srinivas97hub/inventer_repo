package com.Quartz.Demo;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzJob implements Job {
    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
System.out.println("hi............................");
        String body = ("TryingMyQuartz");
        String subject = ("Recieved?");

        String EmpEmail = ("ravinder.v@infyzterminals.com");

        String sender = ("srinivas.t@infyzterminals.com");
        String password = ("infyz@2022");

        String senderDomain = ("@infyzterminals.com");
        String senderUsername = ("srinivas.t");

        MailSenderBean mailsender = new MailSenderBean();
        System.out.println("hi............................");
        mailsender.sendEmail(sender, senderUsername, password, EmpEmail, subject, body, senderDomain);

    }

}