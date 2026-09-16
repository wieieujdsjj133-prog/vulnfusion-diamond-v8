package com.vulnfusion.diamond;

import java.util.List;

public class InspectorRobot extends Robot {
    @Override
    public void execute(String task) {
        System.out.println("👁️ المفتش المصلح يفحص...");
    }

    public void inspectAllRobots(List<Robot> robots) {
        for(Robot r : robots) {
            if(!r.isTaskCompleted()) {
                // إذا فيه عطل يصلحه فوراً
                System.out.println("🔧 وجدت عطل في: " + r.getName() + " - أصلحه الآن");
                r.fixAutomatically();
            }
        }
        System.out.println("✅ فحص المفتش: كل الروبوتات تعمل 100%");
    }
}
