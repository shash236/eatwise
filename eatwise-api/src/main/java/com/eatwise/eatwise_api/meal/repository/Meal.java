package com.eatwise.eatwise_api.meal.repository;


import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "meal")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Meal {

    @Id
    private UUID mealId;

    private UUID userId;

    private Instant timestamp;

    @Enumerated(EnumType.STRING)
    private MealType mealType;

    private String mealTime; // HH:mm format

    @Column(columnDefinition = "jsonb")
    private List<MealQA> mealAnswers;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MealQA {
        private String question;
        private String answer;
    }

    public enum MealType {
        BREAKFAST,
        LUNCH,
        DINNER,
        BEVERAGE,
        SNACK
    }
}
