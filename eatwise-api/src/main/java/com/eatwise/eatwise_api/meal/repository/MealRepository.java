package com.eatwise.eatwise_api.meal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface MealRepository extends JpaRepository<Meal, UUID> {
    // TODO : You can later add custom queries if needed (e.g., find by userId, date range)
}
