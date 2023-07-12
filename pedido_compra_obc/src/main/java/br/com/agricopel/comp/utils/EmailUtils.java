package br.com.agricopel.comp.utils;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailUtils {

	public void enviarEmail(List<String> destinos, List<String> ccos, List<File> anexos, String titulo, String corpo)
			throws Exception {

		MimeMessage msg = new MimeMessage(this.getSession());

		msg.setFrom(new InternetAddress("ti@agricopel.com.br"));

		for (String destino : destinos) {
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(destino));
		}

		for (String cco : ccos) {
			msg.addRecipient(Message.RecipientType.BCC, new InternetAddress(cco));
		}

		msg.setSubject(titulo);

		MimeBodyPart mbp1 = new MimeBodyPart();
		mbp1.setText(corpo);

		MimeBodyPart mbp2 = new MimeBodyPart();

		for (File anexo : anexos) {
			mbp2.attachFile(anexo);
		}

		Multipart mp = new MimeMultipart();
		mp.addBodyPart(mbp1);

		if (!anexos.isEmpty()) {
			mp.addBodyPart(mbp2);
		}

		msg.setContent(mp);
		msg.setSentDate(new Date());

		Transport.send(msg);
	}

	private Session getSession() {

		Properties properties = new Properties();

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "false");
		properties.put("mail.smtp.host", "smtp.agricopel.com.br");
		properties.put("mail.smtp.port", "25");

		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("ti@agricopel.com.br", "L30P4RD0M1M3L340");
			}
		});

		return session;
	}
}
