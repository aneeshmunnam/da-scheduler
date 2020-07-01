package com.dascheduler.repository;

import com.dascheduler.model.SenderDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SenderDetailsRepository extends JpaRepository<SenderDetails, UUID> {
}
