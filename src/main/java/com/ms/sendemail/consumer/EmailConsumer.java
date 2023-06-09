package com.ms.sendemail.consumer;

import com.ms.sendemail.dto.EmailDto;
import com.ms.sendemail.model.EmailModel;
import com.ms.sendemail.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "${spring.rabbitmq.queue}")
    public void listen(@Payload EmailDto emailDto) {
        EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDto, emailModel);
        emailService.sendingEmail(emailModel);
        System.out.println("Email Status " + emailModel.getStatusEmail().toString());
    }

}
