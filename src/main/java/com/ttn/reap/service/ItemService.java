package com.ttn.reap.service;

import com.ttn.reap.entity.Item;
import com.ttn.reap.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    public Optional<Item> findItem(Integer id) {
        return itemRepository.findById(id);
    }
}
