package com.eatwise.eatwise_api.meal.service;

import java.util.Date;
import java.util.List;

import com.eatwise.eatwise_api.meal.dto.CreateMealCommand;
import com.eatwise.eatwise_api.meal.dto.CreateMealResult;
import com.eatwise.eatwise_api.meal.dto.MealResponse;

public interface MealService {
    CreateMealResult createMeal(CreateMealCommand command);
    List<MealResponse> getMeals(final Date date);
}
