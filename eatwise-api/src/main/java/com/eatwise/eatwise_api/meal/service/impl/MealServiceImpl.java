package com.eatwise.eatwise_api.meal.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eatwise.eatwise_api.infrastructure.auth.service.AuthService;
import com.eatwise.eatwise_api.infrastructure.utils.DateUtils;
import com.eatwise.eatwise_api.infrastructure.utils.GsonUtils;
import com.eatwise.eatwise_api.meal.dto.CreateMealCommand;
import com.eatwise.eatwise_api.meal.dto.CreateMealResult;
import com.eatwise.eatwise_api.meal.dto.MealResponse;
import com.eatwise.eatwise_api.meal.dto.MealType;
import com.eatwise.eatwise_api.meal.dto.QuestionAnswer;
import com.eatwise.eatwise_api.meal.repository.Meal;
import com.eatwise.eatwise_api.meal.repository.MealRepository;
import com.eatwise.eatwise_api.meal.service.MealService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MealServiceImpl implements MealService {

    private final MealRepository mealRepository;
    private final AuthService authService;

    @Override
    @Transactional
    public CreateMealResult createMeal(CreateMealCommand command) {
        UUID mealId = command.getMealId() == null ? UUID.randomUUID() : command.getMealId();

        MealType mealType = MealType.valueOf(command.getMealType().toUpperCase());

        String mealAnswersJson = GsonUtils.toJson(command.getMealAnswers());

        Meal meal = Meal.builder()
            .mealId(mealId.toString())
            .userId(authService.getCurrentUserId())
            .mealType(mealType)
            .mealHour(command.getMealHour())
            .mealDate(DateUtils.parseDate(command.getMealDate()))
            .mealAnswersJson(mealAnswersJson)
            .build();


        mealRepository.save(meal);

        return new CreateMealResult(mealId);
    }

    @Override
    public List<MealResponse> getMeals(Date date) {
        List<Meal> meals = mealRepository.findByUserIdAndMealDate(authService.getCurrentUserId(), date);
        return meals.stream()
            .map(meal -> new MealResponse(
                meal.getMealId(),
                meal.getMealType().name(),
                DateUtils.formatDate(meal.getMealDate()),
                meal.getMealHour(),
                GsonUtils.fromJsonList(meal.getMealAnswersJson(), QuestionAnswer.class)
            ))
            .toList();
    }
}


