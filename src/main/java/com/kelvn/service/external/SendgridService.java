package com.kelvn.service.external;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.kelvn.enums.SendGridTemplate.REGISTRATION_TEMPLATE;

@Service
@RequiredArgsConstructor
public class SendgridService {
  @Value("${spring.sendgrid.email.from}")
  private String EMAIL_FROM;
  private final SendGrid sendGrid;

  public void send(String emailTo, String templateId, Map<String, Object> templateData) {
    // Use Single Sender Verification configured in setting for Email from
    Email sender = new Email(EMAIL_FROM);
    Email receiver = new Email(emailTo);
    Content content = new Content("text/html", "Empty");

    // https://stackoverflow.com/questions/53111157/sendgrid-v3-substitutions-may-not-be-used-with-dynamic-templating
    Mail mail = new Mail(sender, null, receiver, content);
    mail.setReplyTo(sender);
    mail.setTemplateId(templateId);
    templateData.forEach((key, value) -> mail.personalization.get(0).addDynamicTemplateData(key, value));

    Request request = new Request();
    Response response;
    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      response = sendGrid.api(request);
      System.out.println(response.getStatusCode());
    } catch (IOException e) {
      System.out.println(e);
    }
  }

  public void sendRegistrationEmail(String email, Object username, String link) {
    Map<String, Object> templateData = new HashMap<>();
    templateData.put("username", username);
    templateData.put("link", link);
    send(email, REGISTRATION_TEMPLATE.getTemplateId(), templateData);
  }

}
