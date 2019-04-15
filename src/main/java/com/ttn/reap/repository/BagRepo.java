package com.ttn.reap.repository;

import com.ttn.reap.entity.Bag;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BagRepo extends CrudRepository<Bag,Integer> {
    Optional<Bag> findByItemIdAndUserId(Integer itemId,Integer userId);

    List<Bag> findByUserId(Integer userId);
}
