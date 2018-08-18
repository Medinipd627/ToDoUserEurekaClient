package com.bridgelabz.eureka.util;

/**************************************************************************************
 * Created By:Medini P.D
 * Date:- 03/07/2018
 * Purpose:MailModel class for the mailSendeing
 **************************************************************************************/
import org.springframework.stereotype.Service;

@Service
public class Mailmodel 
{
	private String to;
	private String subject;
	private String text;
	
	public String getTo() 
	{
		return to;
	}
	public void setTo(String to) 
	{
		this.to = to;
	}
	public String getSubject() 
	{
		return subject;
	}
	public void setSubject(String subject) 
	{
		this.subject = subject;
	}
	public String getText() 
	{
		return text;
	}
	public void setText(String text) 
	{
		this.text = text;
	}
}