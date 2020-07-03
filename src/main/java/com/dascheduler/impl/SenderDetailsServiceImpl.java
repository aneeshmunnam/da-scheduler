package com.dascheduler.impl;

import com.dascheduler.model.SenderDetails;
import com.dascheduler.repository.SenderDetailsRepository;
import com.dascheduler.service.SenderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SenderDetailsServiceImpl implements SenderDetailsService {

    @Autowired
    private SenderDetailsRepository senderDetailsRepository;


    @Override
    public List<SenderDetails> getSenderDetails() {
        return senderDetailsRepository.findAll();
    }

    @Override
    public Optional<SenderDetails> getSenderDetailsById(UUID senderDetailsId) {
        return senderDetailsRepository.findById(senderDetailsId);
    }

    @Override
    public SenderDetails createSenderDetail(SenderDetails createDetail) {
        return senderDetailsRepository.save(createDetail);
    }

    @Override
    public SenderDetails updateSenderDetail(SenderDetails updateDetail) {
        return senderDetailsRepository.saveAndFlush(updateDetail);
    }

    @Override
    public void deleteSenderDetail(UUID senderDetailId) {
        SenderDetails deleteSenderDEtails = senderDetailsRepository.getOne(senderDetailId);
        senderDetailsRepository.delete(deleteSenderDEtails);
    }

    @Override
    public List<SenderDetails> getSenderDetailsByEmailAddress(String emailAddress) {
        return senderDetailsRepository.getSenderDetailsByEmailAddress(emailAddress);
    }
}
