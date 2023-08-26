package com.kelvn.service.listener;

import com.kelvn.dto.request.AccountRequestDTO;
import com.kelvn.enums.Source;
import com.kelvn.service.external.SendgridService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConfirmationService {
  private final SendgridService sendgridService;

  @RabbitListener(queues = "q.registration-confirmation-email")
  private void sendEmail(AccountRequestDTO requestDTO) {
    String token = UUID.randomUUID().toString();
    sendgridService.sendRegistrationConfirmation(
        requestDTO.getEmail(),
        requestDTO.getUsername(),
        requestDTO.getPassword(),
        Source.LOCAL.getLabel(),
        token);
    log.info("Sent email to {}", requestDTO.getEmail());
  }

  // TODO: Implement service to send confirmation SMS
  private void sendSms() {}
}
