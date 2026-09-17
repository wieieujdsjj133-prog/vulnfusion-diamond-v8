import socket, ipaddress, urllib.parse
import httpx
from fastapi import HTTPException
from app.models.schemas import Finding

def validate_public_target(target: str):
    host = urllib.parse.urlparse(target).hostname
    if not host:
        raise HTTPException(400, "هدف غير صالح")
    try:
        infos = socket.getaddrinfo(host, None)
    except socket.gaierror:
        raise HTTPException(400, "تعذر حل اسم النطاق")
    for info in infos:
        ip = ipaddress.ip_address(info[4][0])
        if ip.is_private or ip.is_loopback or ip.is_link_local or ip.is_reserved or ip.is_multicast:
            raise HTTPException(400, "الأهداف المحلية/الخاصة محظورة في هذه النسخة")

async def passive_headers(target: str):
    findings = []
    async with httpx.AsyncClient(timeout=12, follow_redirects=True,
                                 headers={"User-Agent": "Haya-Security-AI/0.1"}) as c:
        r = await c.get(target)
    h = {k.lower(): v for k,v in r.headers.items()}
    checks = [
        ("content-security-policy","Missing Content-Security-Policy"),
        ("x-content-type-options","Missing X-Content-Type-Options"),
        ("referrer-policy","Missing Referrer-Policy"),
        ("permissions-policy","Missing Permissions-Policy"),
    ]
    for header, title in checks:
        if header not in h:
            findings.append(Finding(
                tool="httpx", title=title, severity="medium", confidence=.96,
                impact="قد تقل الحماية المتوفرة للمتصفح أمام بعض فئات الهجمات.",
                evidence=f"الرأس {header} غير موجود في الاستجابة.",
                safe_verification=f"افحص ترويسات الاستجابة وتحقق من غياب {header}.",
                remediation=f"أضف {header} بقيمة مناسبة لتطبيقك."
            ))
    if not findings:
        findings.append(Finding(
            tool="httpx", title="Passive header checks clean", severity="clean",
            confidence=.90, impact="لم تجد الفحوص السلبية المحدودة مشكلة.",
            evidence="تم العثور على ترويسات الحماية الأساسية.",
            safe_verification="أعد الفحص بعد كل تغيير أمني.",
            remediation="استمر في تحديث الاعتماديات وإجراء اختبارات دورية."
        ))
    return findings
