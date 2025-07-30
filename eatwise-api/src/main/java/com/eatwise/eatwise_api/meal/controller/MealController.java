package com.eatwise.eatwise_api.meal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eatwise.eatwise_api.meal.dto.CreateMealCommand;
import com.eatwise.eatwise_api.meal.dto.CreateMealRequest;
import com.eatwise.eatwise_api.meal.dto.CreateMealResponse;
import com.eatwise.eatwise_api.meal.dto.CreateMealResult;
import com.eatwise.eatwise_api.meal.service.impl.MealServiceImpl;

@RestController
@RequestMapping("/meals")
public class MealController {

    @Autowired
    private MealServiceImpl mealService;

    @PostMapping
    public CreateMealResponse createMeal(@RequestBody CreateMealRequest request) {
        CreateMealCommand command = new CreateMealCommand(
            request.getMealTime(),
            request.getMealType(),
            request.getAnswers().stream()
                .map(a -> new CreateMealCommand.MealAnswerDTO(a.getQuestion(), a.getAnswer()))
                .toList()
        );

        CreateMealResult result = mealService.createMeal(command);

        return new CreateMealResponse(
            result.getMealId(),
            result.getTimestamp(),
            result.getMealType(),
            result.getMealTime(),
            result.getMealAnswers().stream()
                .map(a -> new CreateMealResponse.QuestionAnswer(a.getQuestion(), a.getAnswer()))
                .toList()
        );
    }
}

