package com.eatwise.eatwise_api.meal.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMealCommand {
    private String mealTime; // HH:mm format
    private String mealType;
    private List<MealAnswerDTO> mealAnswers;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MealAnswerDTO {
        private String question;
        private String answer;
    }
}

