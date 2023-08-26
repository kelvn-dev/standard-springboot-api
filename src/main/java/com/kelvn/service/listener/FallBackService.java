package com.kelvn.service.listener;

import com.kelvn.dto.request.AccountRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FallBackService {
  @RabbitListener(queues = {"q.fall-back-registration-confirmation-email"})
  public void onRegistrationFailure(AccountRequestDTO requestDTO) {
    log.info("Executing fallback for failed registration confirmation email {}", requestDTO);
  }
}
