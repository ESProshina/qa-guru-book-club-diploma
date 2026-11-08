# Book Club Automation (Дипломный проект QA.GURU)

[![Jenkins](https://img.shields.io/badge/Jenkins-CI%2FCD-blue)](https://jenkins.qa.guru/view/java-students/job/ESPro-book-club-diploma/)
[![Allure Report](https://img.shields.io/badge/Allure-Report-green)](https://jenkins.qa.guru/view/java-students/job/ESPro-book-club-diploma/allure/)

Комплексный фреймворк для автоматизации тестирования учебного сервиса **Book Club** (`book-club.qa.guru`).
Проект представляет собой единый тестируемый контур, объединяющий **Web UI** и **API** тесты.

> **Mobile-тесты:** В соответствии с требованиями к диплому, мобильные автотесты (Appium) вынесены в отдельный независимый репозиторий.

## 🛠 Стек технологий
* **Java 21**
* **Selenide** (UI-автоматизация)
* **REST Assured** (API-тестирование)
* **JUnit 5** (тестовый фреймворк)
* **Gradle** (сборка проекта)
* **Allure Report** (отчетность и вложения)
* **Jenkins + Selenoid** (CI/CD)

## 🚀 Реализованные сценарии (Покрытие)
В проекте реализовано **50 автотестов**, покрывающих ключевые бизнес-процессы сервиса:
* **API-тесты:** Авторизация, регистрация, CRUD-операции с клубами и отзывами, проверка прав доступа (403 Forbidden), валидация данных.
* **UI-тесты:** Логин, регистрация, проверка ошибок.
* **Сквозной E2E-тест:** Создание клуба через API, авторизация в UI через Local Storage и проверка прав в интерфейсе.

## 📸 Скриншоты

### 1. Общий вид Allure Report (100% Passed)
![Allure Overview](images/img.png)

### 2. Детализация API-теста (Логи запросов и ответов)
![Allure API](images/img_1.png)

### 3. Сквозной E2E-тест (UI + API)
![Allure E2E](images/img_2.png)

### 4. Jenkins Job
![Jenkins](images/img_3.png)

[Тест-план (Manual & Automated)](TEST_PLAN.md)).
---
**Автор:** Елена Прошина (QA Automation Engineer)