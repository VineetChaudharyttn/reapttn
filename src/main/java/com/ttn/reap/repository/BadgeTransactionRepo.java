package com.ttn.reap.repository;

import com.ttn.reap.entity.BadgeTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadgeTransactionRepo extends JpaRepository<BadgeTransaction, Integer> {

}
