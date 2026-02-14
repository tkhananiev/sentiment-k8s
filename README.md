# Sentiment API in Kubernetes (Minikube)

Учебный проект по дисциплине **«Оркестрация и контейнеризация»**.

Проект представляет собой Java-приложение с REST API для анализа тональности текста
(упрощённая mock-логика), развёрнутое в Kubernetes-кластере Minikube
с балансировкой нагрузки и автоскейлингом.

---

## Цель проекта

- Контейнеризировать Java-приложение
- Развернуть его в Kubernetes (Minikube)
- Настроить Service, Ingress и HPA
- Подготовить основу для мониторинга и масштабирования

---

## Используемый стек

- Java 17
- Spring Boot
- Maven
- Docker
- Kubernetes (Minikube)

---

## Архитектура

```

Client
|
Ingress
|
Service (LoadBalancer)
|
Deployment (3 replicas)
|
Spring Boot Application

````

---

## Сборка приложения

```bash
mvn package
````

---

## Сборка Docker-образа

```bash
docker build -t sentiment-api:1.0.0 .
```

---

## Запуск в Kubernetes

```bash
kubectl apply -f k8s/
```

Проверка состояния:

```bash
kubectl -n sentiment get pods,svc,ingress,hpa
```

---

## Проверка API

```bash
curl "http://<INGRESS_IP>/api/sentiment?text=I%20love%20kubernetes"
```

Пример ответа:

```json
{ "sentiment": "positive" }
```

---

## Результат

* Приложение успешно контейнеризировано
* Развёрнуто в Kubernetes с несколькими репликами
* Настроены Service, Ingress и автоскейлинг




