package com.bridgelabz.eureka.util;

/*******************************************************************************************
 * Created By:Medini P.D
 * Date:- 03/07/2018
 * Purpose:Consumer class for rabbitmq implementation
 ********************************************************************************************/
import javax.mail.SendFailedException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.eureka.util.Mailmodel;
@Service
public class Consumer 
{
	@Autowired
	MailSender javaMailSender;
	@RabbitListener(queues="${jsa.rabbitmq.queue}")
    public void recievedMessage(Mailmodel mail) throws SendFailedException 
	{  
    javaMailSender.sendMail(mail);
    }
}
