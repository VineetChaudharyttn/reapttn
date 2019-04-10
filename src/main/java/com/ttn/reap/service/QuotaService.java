package com.ttn.reap.service;


import com.ttn.reap.entity.Quota;
import com.ttn.reap.repository.QuotaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuotaService {

    @Autowired
    QuotaRepo quotaRepo;

    public void saveQuota(Quota quota) {
        quotaRepo.save(quota);
    }

    public void deleteAll() {
        quotaRepo.deleteAll();
    }
}
