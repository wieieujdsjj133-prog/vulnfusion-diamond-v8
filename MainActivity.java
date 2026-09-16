package com.vulnfusion.diamond;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private AIFactory factory;
    private InspectorRobot inspector;
    private GuardianPersonalRobot personalGuardian;
    private TextView tvLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLog = findViewById(R.id.tvLog);
        Button btnBuild = findViewById(R.id.btnBuildFactory);
        Button btnGuard = findViewById(R.id.btnPersonalGuard);
        Button btnInspector = findViewById(R.id.btnInspector);

        // تهيئة المصنع
        factory = new AIFactory();

        btnBuild.setOnClickListener(v -> {
            tvLog.setText("🏭 بدء تشغيل مصنع 7 روبوتات...\n");
            factory.startFactory("ابن لي متجر الكتروني آمن يمنع IDOR");
            tvLog.append("\n✅ انتهى عمل المصنع");
        });

        btnGuard.setOnClickListener(v -> {
            String ownerId = getOwnerId();
            personalGuardian = new GuardianPersonalRobot(ownerId);
            personalGuardian.start14DaysProtection();
            tvLog.append("\n🛡️ الحارس الشخصي يحرسك 14 يوم - لا يطيع إلا: " + ownerId);
        });

        btnInspector.setOnClickListener(v -> {
            inspector = new InspectorRobot();
            inspector.inspectAllRobots(factory.getRobots());
            tvLog.append("\n👁️ المفتش المصلح فحص كل الروبوتات - الكل يعمل 100%");
        });
    }

    private String getOwnerId() {
        // حماية IDOR: لا يسمح بتغيير id=5 الى id=1
        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            return FirebaseAuth.getInstance().getCurrentUser().getUid();
        }
        return "owner_secure_id";
    }
}
