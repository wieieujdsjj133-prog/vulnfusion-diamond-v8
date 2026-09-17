"use client";
import {useState} from "react";

const tools=["httpx","Nmap","Nuclei","OWASP ZAP","Nikto","SSLyze","testssl.sh","Amass","Subfinder","Semgrep","SonarQube","Gitleaks","Trivy","MobSF","OpenVAS","Prowler","Kubeaudit","Playwright"];

type Finding={tool:string,title:string,severity:string,confidence:number,impact:string,evidence:string,safe_verification:string,remediation:string,status:string};

export default function Home(){
 const [tab,setTab]=useState("build"),[description,setDescription]=useState(""),[target,setTarget]=useState(""),[authorized,setAuthorized]=useState(false),[loading,setLoading]=useState(false),[findings,setFindings]=useState<Finding[]>([]),[events,setEvents]=useState<any[]>([]),[msg,setMsg]=useState("");
 async function build(){
   if(!description)return;
   setMsg("تم إرسال المشروع إلى خط البناء 1 → 6");
   await fetch("http://localhost:8000/build",{method:"POST",headers:{"Content-Type":"application/json"},body:JSON.stringify({description})});
 }
 async function scan(){
   setLoading(true);setMsg("");setFindings([]);setEvents([]);
   try{
    const r=await fetch("http://localhost:8000/scan",{method:"POST",headers:{"Content-Type":"application/json"},body:JSON.stringify({target,authorized})});
    const d=await r.json(); if(!r.ok)throw new Error(d.detail||"فشل الفحص");
    setFindings(d.findings);setEvents(d.events);
   }catch(e:any){setMsg(e.message)}finally{setLoading(false)}
 }
 const bucket=(s:string)=>findings.filter(x=>x.severity===s);
 return <main className="shell">
  <header><div><small>HAYA BUILDING A</small><h1>Haya Security AI</h1><p>وكيل موحّد لبناء المشاريع وفحصها دفاعيًا.</p></div><div className="auth"><button>Google</button><button>Facebook</button></div></header>
  <nav><button className={tab==="build"?"on":""} onClick={()=>setTab("build")}>🚀 بناء التطبيق</button><button className={tab==="scan"?"on":""} onClick={()=>setTab("scan")}>🛡️ مركز الفحص</button></nav>
  {tab==="build"?<section className="card">
    <h2>صف ما تريد بناءه</h2><textarea value={description} onChange={e=>setDescription(e.target.value)} placeholder="مثال: أنشئ متجرًا إلكترونيًا عربيًا للهواتف مع تسجيل دخول وسلة شراء ولوحة تحكم..."/>
    <button className="primary" onClick={build}>ابدأ البناء 1 → 6</button>{msg&&<p className="msg">{msg}</p>}
    <div className="steps">{["01 إنشاء","02 مراجعة وإصلاح","03 أمان","04 جاهزية","05 بناء","06 اختبار"].map(x=><div>{x}</div>)}</div>
  </section>:<section className="card">
    <h2>مركز الفحص متعدد الوكلاء</h2><p>كل أداة لها وكيل AI متخصص، وتنتقل النتائج بالتتابع إلى الوكيل التالي.</p>
    <input value={target} onChange={e=>setTarget(e.target.value)} placeholder="https://example.com"/>
    <label><input type="checkbox" checked={authorized} onChange={e=>setAuthorized(e.target.checked)}/> أؤكد أن لدي تصريحًا بفحص الهدف.</label>
    <button className="primary" disabled={!authorized||!target||loading} onClick={scan}>{loading?"جارٍ التشغيل...":"ابدأ الفحص المتسلسل"}</button>
    {msg&&<p className="error">{msg}</p>}
    <div className="tools">{tools.map((t,i)=><span>{String(i+1).padStart(2,"0")} · {t} AI</span>)}</div>
    <h2>سير العمل</h2><div className="events">{events.map(e=><div><b>{e.step}. {e.agent}</b><small>{e.status} — {e.message}</small></div>)}</div>
    <div className="results">{[["critical","🔴 قوي"],["high","🔴 عالي"],["medium","🟠 متوسط"],["suspected","🟡 اشتباه"],["clean","🟢 سليم"]].map(([s,t])=><div className="bucket"><h3>{t}<i>{bucket(s).length}</i></h3>{bucket(s).map(f=><article><b>{f.title}</b><small>{f.tool} · ثقة {(f.confidence*100).toFixed(0)}%</small><p><strong>الضرر:</strong> {f.impact}</p><p><strong>الدليل:</strong> {f.evidence}</p><p><strong>التحقق الآمن:</strong> {f.safe_verification}</p><p><strong>الإصلاح:</strong> {f.remediation}</p></article>)}</div>)}</div>
  </section>}
 </main>
}
