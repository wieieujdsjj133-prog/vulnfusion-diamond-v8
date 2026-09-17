# Haya Security AI

منصة دفاعية متعددة الوكلاء لبناء التطبيقات وفحصها أمنيًا.

## بنية النظام

### مسار بناء التطبيق
المستخدم → Agent 1 → Agent 2 → Agent 3 → Agent 4 → Agent 5 → Agent 6

1. Code Generator
2. Code Reviewer & Fixer
3. Secure Code Auditor
4. Readiness Verifier
5. Builder
6. QA / Browser Tester

### مسار الفحص الأمني
مدير الفحص → وكلاء الأدوات بالتتابع → Security Correlation → Remediation → Verification

كل وكيل أداة مستقل، يستقبل نتيجة المرحلة السابقة ويخرج نتيجة منظمة للمرحلة التالية.

## أدوات مدعومة كـ adapters

Nmap, httpx, Nuclei, OWASP ZAP, Nikto, SSLyze, testssl.sh,
Amass, Subfinder, Semgrep, SonarQube, Gitleaks, Trivy, MobSF,
OpenVAS/Greenbone, Prowler, Kubeaudit, Playwright.

النسخة المرفقة هي architecture/starter آمنة: adapters لا تشغّل أوامر هجومية تلقائيًا.
أضف تنفيذ كل أداة فقط في بيئة تملكها أو لديك تصريح صريح لفحصها، مع Scope وAllowlist.

## التشغيل

### Backend

```bash
cd backend
python3 -m venv .venv
source .venv/bin/activate
pip install -r requirements.txt
uvicorn app.main:app --host 0.0.0.0 --port 8000
```

### Frontend

```bash
cd frontend
npm install
npm run dev
```

افتح منفذ 3000 في Codespaces.

## ملاحظة أمنية

المشروع مخصص للفحص الدفاعي المصرح به. لا تستخدمه لفحص أو اختبار أنظمة لا تملكها أو ليس لديك تصريح بفحصها.
