package com.dascheduler.repository;

import com.dascheduler.model.SenderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface SenderDetailsRepository extends JpaRepository<SenderDetails, UUID> {
    Optional<SenderDetails> findById(UUID id);
    List<SenderDetails> getSenderDetailsByEmailAddress(String emailAddress);
}
