package com.ttn.reap.repository;

import com.ttn.reap.entity.Badge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadgeRepo extends CrudRepository<Badge,Integer> {
}
