package com.kelvn.service.external;

import com.kelvn.enums.SendGridTemplate;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.naming.ServiceUnavailableException;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendgridService {
  @Value("${spring.sendgrid.email.from}")
  private String emailFrom;

  @Value("${server.uri}")
  private String serverURI;

  private final SendGrid sendGrid;

  @SneakyThrows
  public void send(String emailTo, String templateId, Map<String, String> templateData) {
    // Use Single Sender Verification configured in setting for Email from
    Email sender = new Email(emailFrom);
    Email receiver = new Email(emailTo);
    Content content = new Content("text/html", "Empty");

    // https://stackoverflow.com/questions/53111157/sendgrid-v3-substitutions-may-not-be-used-with-dynamic-templating
    Mail mail = new Mail(sender, null, receiver, content);
    mail.setReplyTo(sender);
    mail.setTemplateId(templateId);
    templateData.forEach(
        (key, value) -> mail.personalization.get(0).addDynamicTemplateData(key, value));

    Request request = new Request();
    Response response;
    try {
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      response = sendGrid.api(request);
      if (response.getStatusCode() >= 200 && response.getStatusCode() < 300) {
        return;
      }
      throw new ServiceUnavailableException(response.getBody());
    } catch (IOException e) {
      throw new ServiceUnavailableException(e.getMessage());
    }
  }

  public void sendRegistrationConfirmation(
      String email, String username, String password, String source, String token) {
    Map<String, String> templateData = new HashMap<>();
    templateData.put("username", username);
    templateData.put("password", password);
    templateData.put("source", source);
    templateData.put("verificationUrl", serverURI.concat("/verify?token=").concat(token));
    send(email, SendGridTemplate.SIGNUP_TEMPLATE.getTemplateId(), templateData);
  }
}
