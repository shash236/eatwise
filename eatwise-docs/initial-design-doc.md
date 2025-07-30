# Eatwise - Backend Architecture and Design Overview

## 📌 Project Overview

Eatwise is an AI-integrated meal and weight tracking application. It provides intelligent meal suggestions, personalized reviews, and habit streaks, driven by backend services and AI logic.

---

## ✅ Features

* Add meals and rate them
* Track personal info and eating habits
* Daily reviews and suggestions via AI
* Calendar view with streaks
* Dashboard showing weight and meal trends

---

## 🧩 API Design

### POST

* `POST /api/v1/meal`

  * Request Body:

    ```json
    {
      "timestamp": "...",
      "question_template": "...",
      "user_response": "...",
      "meal_type": "...",
      "meal_time": "..."
    }
    ```

* `POST /api/v1/meal-analysis`

  * Request Body:

    ```json
    {
      "contextMessage": "..."
    }
    ```

* `POST /api/v1/trackers`

  * Query Parameters: `?type=...`
  * Body:

    ```json
    {
      "value": 0,
      "unit": "ml"
    }
    ```

### GET

* `/api/v1/calendar?months=n`
* `/api/v1/meal?date=yyyy-mm-dd`
* `/api/v1/suggestions`
* `/api/v1/trackers/{type}`

  * Types: `last_7`, `last_month`, `last_year`

---

## 🗂️ Class Structure

* `meal`: add/edit meal logic
* `service`: AI scoring, calendar sync, dashboard
* `suggestion`: generation and caching
* `dashboard`: AI reviews, weight graph
* `infra`: DB config, Redis, event system
* `user`: auth, preferences, onboarding
* `ai`: interface to LLM providers (e.g., OpenAI)

---

## 📄 DTO Layering

* `Request DTOs`: represent incoming HTTP request bodies
* `Command DTOs`: capture internal intention
* `Result DTOs`: from service to controller
* `Response DTOs`: response shape to client

---

## 🛢️ DB Design (Simplified)

| Table         | Fields                                                |
| ------------- | ----------------------------------------------------- |
| `meal`        | id, user\_id, timestamp, type, notes                  |
| `meal_review` | meal\_id, score, ai\_review, date                     |
| `day_review`  | user\_id, date, summary, ai\_generated\_review        |
| `calendar`    | user\_id, month, value                                |
| `suggestion`  | user\_id, suggestion\_text, is\_latest                |
| `trackers`    | user\_id, type (water/weight), value, unit, timestamp |

---

## 🔄 Async Design (via Event Listeners)

Use `@EventListener` to decouple and asynchronously handle post-processing logic.

### Example

```java
@Component
public class MealEventListener {
    @EventListener
    public void handleMealSaved(MealSavedEvent event) {
        // Trigger AI review logic
        // Update calendar streaks
        // Generate suggestions if needed
    }
}
```

### Benefits:

* Clean separation of concerns
* No need for external MQ if event flow is light
* Follows CQS (Command Query Separation) principle

Use `@Async` on the method to run it asynchronously:

```java
@Async
@EventListener
public void handleAsync(...) { ... }
```

---

## 🔁 Design Tradeoffs: Sync vs Async

| Option    | Pros                        | Cons                                      |
| --------- | --------------------------- | ----------------------------------------- |
| **Sync**  | Simple to implement & debug | UI waits for AI computation               |
| **Async** | Non-blocking, scalable      | UI needs polling/updates, harder to trace |

Chosen: **Async** for AI-driven tasks like review generation, suggestion updates.

---

## 🧠 AI Tasks

* Generate suggestions
* Review meal sentiment
* Summarize daily health info

---

## 📌 Next Steps

* [ ] Mock sample responses for each API
* [ ] Hook OpenAI or another model provider
* [ ] Build Android UI with Kotlin/Java
* [ ] Refine DB schema and relationships
* [ ] Implement retry/timeout flow for async events

