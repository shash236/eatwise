package com.eatwise.eatwise_api.meal.dto;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMealRequest {
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

