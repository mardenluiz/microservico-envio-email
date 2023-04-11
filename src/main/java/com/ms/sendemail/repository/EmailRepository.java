package com.ms.sendemail.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ms.sendemail.model.EmailModel;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<EmailModel, UUID> {

}
