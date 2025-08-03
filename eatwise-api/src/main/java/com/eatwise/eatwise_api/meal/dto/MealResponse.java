package com.eatwise.eatwise_api.meal.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealResponse {
    private String mealId;
    private String mealType;
    private String mealDate;
    private Integer mealHour;
    private List<QuestionAnswer> answers;
}
