package com.eatwise.eatwise_api.meal.repository;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.time.DateUtils;

import com.eatwise.eatwise_api.infrastructure.utils.GsonUtils;
import com.eatwise.eatwise_api.meal.dto.MealResponse;
import com.eatwise.eatwise_api.meal.dto.MealType;
import com.eatwise.eatwise_api.meal.dto.QuestionAnswer;
import com.google.gson.JsonSyntaxException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "meal")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meal {

    @Id
    private String mealId;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private MealType mealType;

    private Integer mealHour;

    private Date mealDate;

    @Column(columnDefinition = "jsonb")
    private String mealAnswersJson;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public List<QuestionAnswer> getMealAnswers() {
        if (mealAnswersJson == null || mealAnswersJson.isEmpty()) {
            return new ArrayList<>();
        }
        try {
            return GsonUtils.fromJsonList(mealAnswersJson, QuestionAnswer.class);
        } catch (JsonSyntaxException e) {
            // TODO : Handle JSON parsing error
            return new ArrayList<>();
        }
    }
}
