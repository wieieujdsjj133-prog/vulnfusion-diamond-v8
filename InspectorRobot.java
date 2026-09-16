package com.vulnfusion.diamond;

import java.util.List;

public class InspectorRobot extends Robot {
    public InspectorRobot() { this.name = "المفتش المصلح 👁️🔧"; }

    @Override
    public void execute(String task) {
        System.out.println("👁️🔧 المفتش المصلح: افحص هل كل روبوت قام بعمله...");
        this.taskCompleted = true;
    }

    public void inspectAllRobots(List<Robot> robots) {
        System.out.println("👁️ بدء فحص شامل لـ " + robots.size() + " روبوتات...");
        int brokenCount = 0;
        for(Robot r : robots) {
            if(!r.isTaskCompleted()) {
                brokenCount++;
                System.out.println("🔴 وجدت عطل في: " + r.getName());
                fixRobot(r);
            } else {
                System.out.println("🟢 " + r.getName() + " يعمل 100%");
            }
        }
        if(brokenCount == 0) {
            System.out.println("✅ فحص المفتش: كل الروبوتات تعمل 100% - لا يوجد اعطال");
        } else {
            System.out.println("🔧 تم اصلاح " + brokenCount + " روبوت");
        }
        this.taskCompleted = true;
    }

    public void fixRobot(Robot robot) {
        System.out.println("🔧 اصلح الروبوت: " + robot.getName());
        System.out.println("  1. اعادة تشغيل Restart");
        System.out.println("  2. مسح ذاكرة Reset Memory");
        System.out.println("  3. اعادة تدريب Retraining");
        robot.fixAutomatically();
    }
}
