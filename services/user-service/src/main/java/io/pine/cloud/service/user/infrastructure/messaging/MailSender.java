package io.pine.cloud.service.user.infrastructure.messaging;

import io.pine.cloud.service.user.domain.Mailer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class MailSender {
	@Autowired
	private static JavaMailSender mailSender;
	
	public static void sendSimpleMail(Mailer mailer) throws Exception {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(mailer.getFrom());
		message.setTo(mailer.getTo());
		message.setSubject(mailer.getSubject());
		message.setText(mailer.getText());
		mailSender.send(message);
	}
	
	public static void sendAttachmentsMail(Mailer mailer) throws Exception {
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
		helper.setFrom(mailer.getFrom());
		helper.setTo(mailer.getTo());
		helper.setSubject(mailer.getSubject());
		helper.setText(mailer.getText());
		for(Mailer.Attachment a: mailer.getAttachments()){
			FileSystemResource file = new FileSystemResource(new File(a.getPath()));
			helper.addAttachment(a.getName(), file);
		}
		mailSender.send(mimeMessage);
	}

//	public static void sendTemplateMail(Mailer mailer, String templateName, Object model) throws Exception{
//		MimeMessage mimeMessage = mailSender.createMimeMessage();
//		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
//		helper.setFrom(mailer.getFrom());
//		helper.setTo(mailer.getTo());
//		helper.setSubject(mailer.getSubject());
//		Configuration tconf = new Configuration(Configuration.VERSION_2_3_23);
//		String text = FreeMarkerTemplateUtils.processTemplateIntoString(tconf.getTemplate(templateName, "UTF-8"), model);
//		helper.setText(text, true);
//		mailSender.send(mimeMessage);
//	}
}
