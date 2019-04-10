package com.ttn.reap.repository;

import com.ttn.reap.entity.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends CrudRepository<Item, Integer> {

    List<Item> findAll();

    Optional<Item> findById(Integer id);


}
