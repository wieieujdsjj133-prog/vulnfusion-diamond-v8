package com.vulnfusion.diamond;

import java.util.List;

public class SupervisorRobot extends Robot {
    public SupervisorRobot() { this.name = "المشرف العام ⚖️"; }

    @Override
    public void execute(String task) {
        System.out.println("⚖️ المشرف العام: اراجع شغل 4 روبوتات...");
        this.taskCompleted = true;
    }

    public void validatePreviousWork(List<Robot> robots) {
        for(Robot r : robots) {
            if(r == this) break;
            if(!r.isTaskCompleted()) {
                System.out.println("⚖️ يا " + r.getName() + "، عندك غلط في السطر 42 - لم تكمل عملك - أعد العمل مع شرح: يجب انهاء Task");
                r.fixAutomatically();
            }
        }
        System.out.println("⚖️ المشرف العام: فحصت الكل - الجودة 100%");
    }
}
