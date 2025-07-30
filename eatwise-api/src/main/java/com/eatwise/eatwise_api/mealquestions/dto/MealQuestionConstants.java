package com.eatwise.eatwise_api.mealquestions.dto;

import java.util.List;
import java.util.Map;

public class MealQuestionConstants {
    public static final Map<String, List<String>> QUESTION_BANK = Map.of(
        "BREAKFAST", List.of(
            "What did you eat for breakfast?",
            "Did you have a beverage?",
            "How filling was your breakfast?"
        ),
        "LUNCH", List.of(
            "What did you eat for lunch?",
            "How heavy was the meal?",
            "Did you eat with someone?"
        ),
        "DINNER", List.of(
            "What did you eat for dinner?",
            "Did you eat late?",
            "Did you feel full before sleep?"
        ),
        "JUST_FOOD", List.of(
            "What did you eat?",
            "How much?",
            "Why did you eat it?"
        )
        // Add more meal types as needed
    );
}
