package com.ttn.reap.service;

import com.ttn.reap.entity.Bag;
import com.ttn.reap.repository.BagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BagService {

    @Autowired
    BagRepo bagRepo;
    public Bag findByUserAndItem(Integer itemId, Integer userId) {
        return bagRepo.findByItemIdAndUserId(itemId,userId).orElse(null);
    }

    public void saveItem(Bag bag) {
         bagRepo.save(bag);
    }

    public void deleteItem(Bag bag) {
        bagRepo.delete(bag);
    }

    public List<Bag> findByUser(Integer userId) {
        return bagRepo.findByUserId(userId);
    }
}
