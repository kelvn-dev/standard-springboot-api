package com.kelvn.service.listener;

import com.kelvn.dto.request.AccountRequestDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationService {
  @RabbitListener(queues = "q.registration-confirmation-email")
  private void sendEmail(AccountRequestDTO requestDTO) {
    System.out.println("Sending email to ...");
    System.out.println(requestDTO);
  }

  // TODO: Implement service to send confirmation SMS
  private void sendSms() {}
}
