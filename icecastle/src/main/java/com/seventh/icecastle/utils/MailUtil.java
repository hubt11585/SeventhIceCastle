package com.seventh.icecastle.utils;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class MailUtil {
    static String FROM = "1050750201@qq.com"; // 发件人地址
    static String USER = "1050750201@qq.com"; // 用户名
    static String PWD = "fvkrzukmawixbfag"; // QQ的授权码
    static String SUBJECT = "第七冰城用户注册验证"; // 邮件标题

    /**
     * 发送邮件
     * @param mailAddress
     * @param context
     */
    public static void send(String mailAddress,String context){
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");// 连接协议
        properties.put("mail.smtp.host", "smtp.qq.com");// 主机名
        properties.put("mail.smtp.port", 465);// 端口号
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
        properties.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
        // 得到回话对象
        Session session = Session.getInstance(properties);
        // 获取邮件对象
        Message message = new MimeMessage(session);
        Transport transport = null;
        try {
            // 设置发件人邮箱地址
            message.setFrom(new InternetAddress(FROM));
            // 设置收件人邮箱地址
            InternetAddress[] sendTo = new InternetAddress[1]; // 加载收件人地址
            sendTo[0] = new InternetAddress(mailAddress);
            message.setRecipients(Message.RecipientType.TO, sendTo);
            // 设置邮件标题
            message.setSubject(SUBJECT);
            Multipart multipart = new MimeMultipart();//向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
            // 设置邮件内容
            BodyPart contentPart = new MimeBodyPart();//设置邮件的文本内容
            contentPart.setText(context);
            multipart.addBodyPart(contentPart);
            message.setContent(multipart);//将multipart对象放到message中
            // 得到邮箱对象
            transport = session.getTransport();
            // 连接自己的邮箱账户
            transport.connect(USER, PWD);// 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
            // 发送邮件
            transport.sendMessage(message, message.getAllRecipients());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(transport != null){
                try {
                    transport.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
