from fastapi import FastAPI, HTTPException
from fastapi.middleware.cors import CORSMiddleware
from app.models.schemas import ScanRequest, ScanResponse, BuildRequest
from app.services.passive import validate_public_target, passive_headers
from app.services.pipeline import run_security_pipeline

app = FastAPI(title="Haya Security AI", version="1.0")
app.add_middleware(
    CORSMiddleware,
    allow_origins=["http://localhost:3000"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

@app.get("/health")
async def health():
    return {"ok": True, "service": "Haya Security AI"}

@app.post("/scan", response_model=ScanResponse)
async def scan(req: ScanRequest):
    if not req.authorized:
        raise HTTPException(400, "يجب تأكيد التصريح قبل الفحص")
    target = str(req.target)
    validate_public_target(target)

    events, findings, summary = await run_security_pipeline(
        target, req.enabled_tools
    )

    # Put the safe passive HTTP result at the front.
    passive = await passive_headers(target)
    findings = passive + findings

    return ScanResponse(
        target=target, events=events, findings=findings, summary=summary
    )

@app.post("/build")
async def build(req: BuildRequest):
    return {
        "status": "queued",
        "description": req.description,
        "pipeline": [
            {"step": 1, "agent": "Code Generator", "status": "queued"},
            {"step": 2, "agent": "Code Reviewer & Fixer", "status": "queued"},
            {"step": 3, "agent": "Secure Code Auditor", "status": "queued"},
            {"step": 4, "agent": "Readiness Verifier", "status": "queued"},
            {"step": 5, "agent": "Builder", "status": "queued"},
            {"step": 6, "agent": "QA / Browser Tester", "status": "queued"}
        ],
        "note": "ربط مزود النموذج وتنفيذ البناء الفعلي يضاف في worker مع sandbox."
    }
