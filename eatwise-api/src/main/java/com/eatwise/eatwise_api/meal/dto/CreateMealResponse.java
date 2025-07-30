package com.eatwise.eatwise_api.meal.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMealResponse {
    private UUID mealId;
    private Instant timestamp;
    private String mealType;
    private String mealTime;
    private List<QuestionAnswer> answers;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QuestionAnswer {
        private String question;
        private String answer;
    }
}

