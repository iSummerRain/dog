package com.dog.service;

import com.dog.service.email.IEMailSV;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EMailTest {

    private String toEmailAddress = "liliang11@asiainfo.com";

    @Autowired
    private IEMailSV mailService;

//    @Autowired
//    private TemplateEngine templateEngine;

//    @Test
    public void testSimpleMail() throws Exception {
        mailService.sendSimpleMail(toEmailAddress, "Test spring-boot mail", "Hallo,this is simple mail from Snoopy Project.");
    }

    @Test
    public void testHtmlMail() throws Exception {

        String content="<html>\n" +
                "<body>\n" +
                "    <h3>你好：\n\t &nbsp;&nbsp;和尚用飘柔，下雨不用愁，这是来自SpringBoot官网的问候，请查收！</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail(toEmailAddress, "Test spring-boot mail", content);
    }

//    @Test
    public void sendAttachmentsMail() {
        String filePath="e:\\tmp\\application.log";
        mailService.sendAttachmentsMail(toEmailAddress, "主题：带附件的邮件", "有附件，请查收！", filePath);
    }


//    @Test
    public void sendInlineResourceMail() {
        String rscId = "9527";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "C:\\Users\\summer\\Pictures\\favicon.png";

        mailService.sendInlineResourceMail(toEmailAddress, "主题：这是有图片的邮件", content, imgPath, rscId);
    }


//    @Test
    public void sendTemplateMail() {
        //创建邮件正文
//        Context context = new Context();
//        context.setVariable("id", "006");
//        String emailContent = templateEngine.process("emailTemplate", context);
//
//        mailService.sendHtmlMail("ityouknow@126.com","主题：这是模板邮件",emailContent);
    }
}
