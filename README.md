# Sentiment API with AI Model in Kubernetes (Minikube)

Учебный проект по дисциплине **«Оркестрация и контейнеризация»**.

Проект реализует распределённое контейнерное приложение для анализа тональности текста с использованием модели машинного обучения и развёртыванием в Kubernetes-кластере Minikube.

---

## Архитектура

Проект состоит из двух микросервисов:

- **sentiment-api** — Java (Spring Boot) REST API  
- **ai-service** — Python (FastAPI) сервис инференса модели DistilBERT  

### Схема взаимодействия

    Client
       |
       v
    Ingress
       |
       v
    Service (sentiment-api)
       |
       v
    Java API (Spring Boot)
       |
       v
    Service (ai-service)
       |
       v
    FastAPI + DistilBERT

Java-сервис принимает HTTP-запрос и передаёт текст в AI-сервис для получения предсказания тональности.

---

## AI-модель

Используется модель `distilbert-base-uncased-finetuned-sst-2-english`.

Модель выполняет бинарную классификацию:

- positive  
- negative  

Пример ответа:

    {
      "sentiment": "positive",
      "score": 0.9997
    }

---

## Технологический стек

- Java 17  
- Spring Boot  
- Python 3.11  
- FastAPI  
- HuggingFace Transformers  
- Maven  
- Docker  
- Kubernetes (Minikube)  
- NGINX Ingress  
- Horizontal Pod Autoscaler  

---

## Сборка

Сборка Java-приложения:

    mvn package

Сборка Docker-образов:

    docker build -t sentiment-api:1.0.0 .
    docker build -t ai-sentiment:1.0.0 ./ai-service

---

## Развёртывание в Kubernetes

    kubectl apply -f k8s/

Проверка состояния:

    kubectl -n sentiment get pods,svc,ingress,hpa

---

## Проверка API

    curl "http://<INGRESS_IP>/api/sentiment?text=I%20love%20kubernetes"

---

## Результат

- Реализована интеграция Java-приложения с ML-моделью  
- Выполнена контейнеризация двух сервисов  
- Настроена маршрутизация через Ingress  
- Реализовано горизонтальное масштабирование  
- Продемонстрирована архитектура ML-инференса в Kubernetes  
