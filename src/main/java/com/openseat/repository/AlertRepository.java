package com.openseat.repository;

import com.openseat.model.Alert;
import com.openseat.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByUserOrderByCreatedAtDesc(AppUser user);
    List<Alert> findTop5ByUserOrderByCreatedAtDesc(AppUser user);
}