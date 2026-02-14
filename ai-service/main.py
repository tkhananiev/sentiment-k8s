from fastapi import FastAPI, Query
from transformers import pipeline

app = FastAPI(title="AI Sentiment Service")

# Модель тональности (SST-2): positive/negative
clf = pipeline("sentiment-analysis", model="distilbert-base-uncased-finetuned-sst-2-english")

@app.get("/predict")
def predict(text: str = Query(..., min_length=1)):
    r = clf(text)[0]  # {'label': 'POSITIVE', 'score': 0.999...}
    label = r["label"].lower()  # positive / negative
    return {"sentiment": label, "score": float(r["score"])}
