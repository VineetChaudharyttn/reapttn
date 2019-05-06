package com.ttn.reap.service;


import com.ttn.reap.entity.Purchase;
import com.ttn.reap.entity.User;
import com.ttn.reap.repository.PurchaseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {
    @Autowired
    PurchaseRepo purchaseRepo;

    public void save(Purchase purchase) {
        purchaseRepo.save(purchase);
    }

    public Purchase getHistory(User user) {
        return purchaseRepo.findByUser(user);
    }
}
