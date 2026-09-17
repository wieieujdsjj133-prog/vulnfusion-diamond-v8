from pydantic import BaseModel, HttpUrl, Field
from typing import Literal, Optional, List, Dict, Any

Severity = Literal["critical", "high", "medium", "suspected", "clean"]

class Finding(BaseModel):
    tool: str
    title: str
    severity: Severity
    confidence: float = Field(ge=0, le=1)
    impact: str
    evidence: str
    safe_verification: str
    remediation: str
    status: str = "open"

class ScanRequest(BaseModel):
    target: HttpUrl
    authorized: bool = False
    enabled_tools: Optional[List[str]] = None

class PipelineEvent(BaseModel):
    step: int
    agent: str
    tool: str
    status: str
    message: str

class ScanResponse(BaseModel):
    target: str
    events: List[PipelineEvent]
    findings: List[Finding]
    summary: Dict[str, int]

class BuildRequest(BaseModel):
    description: str
