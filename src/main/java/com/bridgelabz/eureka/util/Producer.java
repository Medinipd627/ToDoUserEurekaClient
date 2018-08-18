package com.bridgelabz.eureka.util;

import com.bridgelabz.eureka.util.Mailmodel;
/*****************************************************************************************
 * Created By:Medini P.D
 * Date:- 03/07/2018
 * Purpose:Producer class for the rabbitmq implementation
 ****************************************************************************************/
public interface Producer
{
	void produceMail(Mailmodel mail);
}