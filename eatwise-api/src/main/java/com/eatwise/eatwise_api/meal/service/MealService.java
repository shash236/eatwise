package com.eatwise.eatwise_api.meal.service;

import com.eatwise.eatwise_api.meal.dto.CreateMealCommand;
import com.eatwise.eatwise_api.meal.dto.CreateMealResult;

public interface MealService {
    CreateMealResult createMeal(CreateMealCommand command);
}
