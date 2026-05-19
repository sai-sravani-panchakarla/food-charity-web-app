package com.charity.food_service.repository;

import com.charity.food_service.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
    List<MenuItem> findByMonth(String month);
    List<MenuItem> findByServedYear(int year);
    List<MenuItem> findByMonthAndServedYear(String month, int year);
}
