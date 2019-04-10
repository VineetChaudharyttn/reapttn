package com.ttn.reap.repository;

import com.ttn.reap.entity.Badge;
import com.ttn.reap.entity.Quota;
import com.ttn.reap.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuotaRepo extends CrudRepository<Quota,Integer> {
    Optional<Quota> findByUserIdAndBadgeIdOrderByBadgeId(User user,Badge badge );
}
