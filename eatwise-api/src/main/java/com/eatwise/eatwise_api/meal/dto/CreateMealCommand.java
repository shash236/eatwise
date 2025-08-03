package com.eatwise.eatwise_api.meal.dto;


import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMealCommand {
    private UUID mealId;
    private Integer mealHour;
    private String mealDate; // Format : DD-MM-YYYY
    private String mealType;
    private List<QuestionAnswer> mealAnswers;
}

