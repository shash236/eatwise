package com.eatwise.eatwise_api.mealquestions.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eatwise.eatwise_api.mealquestions.dto.MealQuestionConstants;
import com.eatwise.eatwise_api.mealquestions.dto.MealQuestionDTO;

@RestController
@RequestMapping("/questions")
public class MealQuestionsController {

    @GetMapping
    public List<MealQuestionDTO> getQuestionsByMealType(@RequestParam String mealType) {
        List<String> questions = MealQuestionConstants.QUESTION_BANK.getOrDefault(
            mealType.toUpperCase(),
            List.of("What did you eat?", "How much did you eat?")
        );

        return questions.stream()
            .map(MealQuestionDTO::new)
            .toList();
    }
}

