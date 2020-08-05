package com.cheng.utils;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailUtils {

	/**
	 * 发送邮件的工具类
	 * @param email 收件地址
	 * @param title 邮件标题
	 * @param body  邮件内容
	 * @return
	 */
	public static boolean sendMail(String email,String title, String body) {

		String from = "498449509@qq.com"; // 邮件发送人的邮件地址
		String to = email; // 邮件接收人的邮件地址
		final String username = "498449509@qq.com"; // 发件人的邮件帐户
		final String password = "obyniqpcdkpvbhce"; // 发件人的邮件密码或授权码

		// 定义Properties对象,设置环境信息
		Properties props = System.getProperties();

		// 设置发送邮件服务器的地址及协议
		props.setProperty("mail.smtp.host", "smtp.qq.com"); // 指定的smtp服务器
		props.setProperty("mail.smtp.auth", "false");
		props.setProperty("mail.smtp.timeout", "25000");
		props.setProperty("mail.transport.protocol", "smtp");// 设置发送邮件使用的协议
	    // 指定的端口连接到在使用指定的套接字工厂。如果没有设置,将使用默认端口。  
	    props.setProperty("mail.smtp.socketFactory.port", "465");  
	    
		// 创建Session对象,session对象表示整个邮件的环境信息
		Session session = Session.getInstance(props);
		// 设置输出调试信息
		session.setDebug(true);
		try {
			// Message的实例对象表示一封电子邮件
			MimeMessage message = new MimeMessage(session);
			// 设置发件人的地址
			message.setFrom(new InternetAddress(from));
			// 设置标题
			message.setSubject(title);
			// 设置邮件的文本内容
			// message.setText("Welcome to JavaMail World!");
			message.setContent((body), "text/html;charset=utf-8");
			// 从session的环境中获取发送邮件的对象
			Transport transport = session.getTransport();

			// 连接邮件服务器指定地址，端口，用户名和口令
			transport.connect("smtp.qq.com", 587, username, password); //默认25端口

		    // 如果设置,指定实现javax.net.SocketFactory接口的类的名称,这个类将被用于创建SMTP的套接字。  
		    props.setProperty("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");  
		    // 如果设置为true,未能创建一个套接字使用指定的套接字工厂类将导致使用java.net.Socket创建的套接字类。默认值为true。  
		    props.setProperty("mail.smtp.socketFactory.fallback", "false");  
		    
			// 设置收件人地址,并发送消息
			transport.sendMessage(message, new Address[] { new InternetAddress(to) });
			transport.close();
			return true;
		} catch (MessagingException e) {
			e.printStackTrace();
			return false;
		}
	}
}
