package com.eatwise.eatwise_api.meal.service.impl;

import org.springframework.stereotype.Service;

import com.eatwise.eatwise_api.meal.dto.CreateMealCommand;
import com.eatwise.eatwise_api.meal.dto.CreateMealResult;
import com.eatwise.eatwise_api.meal.repository.Meal;
import com.eatwise.eatwise_api.meal.repository.MealRepository;
import com.eatwise.eatwise_api.meal.service.MealService;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.UUID;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository mealRepository;

    @Override
    public CreateMealResult createMeal(CreateMealCommand command) {
        UUID mealId = UUID.randomUUID();
        Instant timestamp = Instant.now();

        Meal.MealType mealType = Meal.MealType.valueOf(command.getMealType().toUpperCase());

        List<Meal.MealQA> mealQAList = command.getMealAnswers().stream()
            .map(a -> new Meal.MealQA(a.getQuestion(), a.getAnswer()))
            .collect(Collectors.toList());

        Meal meal = new Meal(
            mealId,
            getCurrentUserId(), // TODO : Replace with actual user resolution (e.g., from security context)
            timestamp,
            mealType,
            command.getMealTime(),
            mealQAList
        );

        mealRepository.save(meal);

        List<CreateMealCommand.MealAnswerDTO> resultAnswers = command.getMealAnswers();

        return new CreateMealResult(
            mealId,
            timestamp,
            mealType.name(),
            command.getMealTime(),
            resultAnswers
        );
    }

    private UUID getCurrentUserId() {
        // TODO : Replace with real user resolution logic (e.g., from SecurityContext)
        return UUID.fromString("00000000-0000-0000-0000-000000000001");
    }
}


