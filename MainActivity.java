package com.vulnfusion.diamond;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    
    // مصنع 7 روبوتات
    private AIFactory factory;
    private InspectorRobot inspector; // المفتش المصلح الجديد
    private GuardianPersonalRobot personalGuardian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // تشغيل المصنع
        factory = new AIFactory();
        factory.startFactory("ابن لي متجر الكتروني آمن");

        // 7. المفتش المصلح - يفحص هل كل روبوت قام بعمله
        inspector = new InspectorRobot();
        inspector.inspectAllRobots(factory.getRobots());

        // 6. الحارس الشخصي - لا يطيع إلا أنت
        personalGuardian = new GuardianPersonalRobot(getOwnerId());
        personalGuardian.start14DaysProtection();
    }
    
    private String getOwnerId() {
        // يمنع ثغرة IDOR: لا يسمح بتغيير id=5 الى id=1
        return FirebaseAuth.getInstance().getCurrentUser().getUid();
    }
}
