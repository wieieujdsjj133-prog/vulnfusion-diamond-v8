
# كيف ترفع التطبيق على GitHub - شرح مفصل بالصور

## الخطوة 1: أنشئ حساب GitHub
1. ادخل github.com > Sign up
2. أكد إيميلك

## الخطوة 2: أنشئ مستودع جديد
1. اضغط + في الأعلى > New repository
2. اسم المستودع: vulnfusion-diamond-v8
3. اختر Private إذا تريد المشروع خاص بك فقط
4. لا تضع علامة على Add README
5. اضغط Create repository

## الخطوة 3: ارفع المشروع (3 طرق)

### أسهل طريقة (بدون أوامر):
1. في صفحة المستودع الجديد، اضغط uploading an existing file
2. اسحب كل ملفات المشروع (أو ملف ZIP)
3. اضغط Commit changes

### طريقة Android Studio:
1. افتح المشروع في Android Studio
2. من القائمة العلوية: VCS > Enable Version Control > Git
3. VCS > Share Project on GitHub
4. أدخل اسم المستودع واضغط Share

### طريقة سطر الأوامر:
```bash
git init
git add .
git commit -m "Initial commit Diamond v8"
git remote add origin https://github.com/USERNAME/vulnfusion-diamond-v8.git
git push -u origin main
```

## الخطوة 4: أضف المفاتيح السرية
1. في GitHub: Settings > Secrets and variables > Actions > New repository secret
2. أضف:
   - Name: OPENAI_API_KEY Value: sk-proj-xxxx
   - Name: FIREBASE_CONFIG Value: محتوى ملف google-services.json

## الخطوة 5: فعل GitHub Actions (يبني APK تلقائيا)
أنشئ ملف .github/workflows/build.yml والمحتوى موجود في المشروع

بعدها كل مرة تدفع كود جديد، GitHub يبني لك APK تلقائيا!
