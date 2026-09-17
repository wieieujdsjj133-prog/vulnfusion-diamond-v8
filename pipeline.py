from typing import List, Tuple
from app.models.schemas import Finding, PipelineEvent
from app.scanners.agents import (
    PassiveHeaderAgent, NmapAgent, NucleiAgent, ZapAgent, NiktoAgent,
    SSLyzeAgent, TestSSLAgent, AmassAgent, SubfinderAgent, SemgrepAgent,
    SonarQubeAgent, GitleaksAgent, TrivyAgent, MobSFAgent, OpenVASAgent,
    ProwlerAgent, KubeauditAgent, BrowserQAAgent
)

AGENTS = [
    PassiveHeaderAgent(), NmapAgent(), NucleiAgent(), ZapAgent(),
    NiktoAgent(), SSLyzeAgent(), TestSSLAgent(), AmassAgent(),
    SubfinderAgent(), SemgrepAgent(), SonarQubeAgent(), GitleaksAgent(),
    TrivyAgent(), MobSFAgent(), OpenVASAgent(), ProwlerAgent(),
    KubeauditAgent(), BrowserQAAgent()
]

async def run_security_pipeline(target: str, enabled_tools=None):
    findings: List[Finding] = []
    events: List[PipelineEvent] = []

    selected = set(enabled_tools or [a.tool for a in AGENTS])

    for i, agent in enumerate(AGENTS, start=1):
        if agent.tool not in selected:
            continue
        events.append(PipelineEvent(step=i, agent=agent.name, tool=agent.tool,
                                    status="running",
                                    message=f"بدأ {agent.name}"))
        new_findings = await agent.scan(target, findings)
        findings.extend(new_findings)
        events[-1].status = "completed"
        events[-1].message = f"انتهى {agent.name} وتم تمرير النتيجة للمرحلة التالية"

    # Deduplicate by tool/title.
    unique = {}
    for f in findings:
        unique[(f.tool, f.title)] = f
    findings = list(unique.values())

    summary = {
        "critical": sum(f.severity == "critical" for f in findings),
        "high": sum(f.severity == "high" for f in findings),
        "medium": sum(f.severity == "medium" for f in findings),
        "suspected": sum(f.severity == "suspected" for f in findings),
        "clean": sum(f.severity == "clean" for f in findings),
    }
    return events, findings, summary
