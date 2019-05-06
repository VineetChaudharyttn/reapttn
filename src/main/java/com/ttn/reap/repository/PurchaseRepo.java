package com.ttn.reap.repository;

import com.ttn.reap.entity.Purchase;
import com.ttn.reap.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepo extends CrudRepository<Purchase,Integer> {
    Purchase findByUser(User user);
}
