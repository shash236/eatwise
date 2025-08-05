package com.eatwise.eatwise_api.meal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eatwise.eatwise_api.infrastructure.utils.DateUtils;
import com.eatwise.eatwise_api.meal.dto.CreateMealCommand;
import com.eatwise.eatwise_api.meal.dto.CreateMealRequest;
import com.eatwise.eatwise_api.meal.dto.CreateMealResponse;
import com.eatwise.eatwise_api.meal.dto.CreateMealResult;
import com.eatwise.eatwise_api.meal.dto.MealResponse;
import com.eatwise.eatwise_api.meal.service.impl.MealServiceImpl;

@RestController
@RequestMapping("/meals")
public class MealController {

    @Autowired
    private MealServiceImpl mealService;

    @PostMapping
    public CreateMealResponse createMeal(@RequestBody CreateMealRequest request) {
        CreateMealCommand command = CreateMealCommand.builder()
            .mealId(request.getMealId())
            .mealHour(request.getMealHour())
            .mealDate(request.getMealDate())
            .mealType(request.getMealType())
            .mealAnswers(request.getAnswers())
            .build();
        CreateMealResult response = mealService.createMeal(command);
        CreateMealResponse createMealResponse = CreateMealResponse.builder()
            .mealId(response.getMealId())
            .build();
        return createMealResponse;
    }

    @GetMapping
    public List<MealResponse> getMeals(@RequestParam("date") String date) {
        return mealService.getMeals(DateUtils.parseDate(date));
    }

}

