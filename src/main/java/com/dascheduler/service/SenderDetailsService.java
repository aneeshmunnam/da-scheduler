package com.dascheduler.service;

import com.dascheduler.model.SenderDetails;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SenderDetailsService {
    List<SenderDetails> getSenderDetails();
    Optional<SenderDetails> getSenderDetailsById(UUID senderDetailsId);
    SenderDetails createSenderDetail(SenderDetails createDetail);
    SenderDetails updateSenderDetail(SenderDetails updateDetail);
    void deleteSenderDetail(UUID senderDetailId);
    List<SenderDetails> getSenderDetailsByEmailAddress(String emailAddress);
}
