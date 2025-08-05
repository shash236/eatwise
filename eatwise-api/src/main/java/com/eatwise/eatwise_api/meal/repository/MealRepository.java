package com.eatwise.eatwise_api.meal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface MealRepository extends JpaRepository<Meal, UUID> {
    List<Meal> findByUserIdAndMealDate(Long userId, Date mealDate);
}
