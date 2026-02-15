from fastapi import FastAPI, Query
from transformers import pipeline
from prometheus_fastapi_instrumentator import Instrumentator

app = FastAPI(title="AI Sentiment Service")

Instrumentator().instrument(app).expose(app, endpoint="/metrics")

clf = pipeline(
    "sentiment-analysis",
    model="distilbert-base-uncased-finetuned-sst-2-english"
)

@app.get("/health")
def health():
    return {"status": "UP"}

@app.get("/predict")
def predict(text: str = Query(..., min_length=1)):
    r = clf(text)[0]
    label = r["label"].lower()
    return {"sentiment": label, "score": float(r["score"])}

