package com.ttn.reap.repository;

import com.ttn.reap.entity.BadgeTransaction;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BadgeTransactionRepo extends JpaRepository<BadgeTransaction, Integer> {

    List<BadgeTransaction> findAll(Sort sort);
}
