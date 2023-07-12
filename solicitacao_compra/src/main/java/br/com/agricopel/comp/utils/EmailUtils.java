package br.com.agricopel.comp.utils;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

public class EmailUtils {

	public void enviarEmail(String destino, String cco, byte[] planilha, String titulo, String corpo) throws Exception {

		MimeMessage msg = new MimeMessage(this.getSession());

		msg.setFrom(new InternetAddress("ti@agricopel.com.br"));

		if (destino != null && !destino.isEmpty()) {
			msg.addRecipients(Message.RecipientType.TO, destino);
		}

		if (cco != null && !cco.isEmpty()) {
			msg.addRecipients(Message.RecipientType.BCC, cco);
		}

		msg.setSubject(titulo);

		MimeBodyPart mbp1 = new MimeBodyPart();
		mbp1.setText(corpo);

		MimeBodyPart att = new MimeBodyPart();
		ByteArrayDataSource bds = new ByteArrayDataSource(planilha, "application/vnd.ms-excel");
		att.setDataHandler(new DataHandler(bds));
		att.setFileName(bds.getName());

		Multipart mp = new MimeMultipart();
		mp.addBodyPart(mbp1);
		mp.addBodyPart(att);

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
				return new PasswordAuthentication("ti@agricopel.com.br", "123!@#as");
			}
		});

		return session;
	}
}
