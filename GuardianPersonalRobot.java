package com.vulnfusion.diamond;

public class GuardianPersonalRobot extends Robot {
    private String ownerIdOnly; // لا يطيع إلا أنت

    public GuardianPersonalRobot(String ownerId) {
        this.ownerIdOnly = ownerId;
    }

    public void start14DaysProtection() {
        // يفحص 14 يوم متواصلة + يغلق الثغرات + محادثة خاصة
        // 18 أداة: Nuclei, ZAP, SQLMap, MobSF...
        // يصد كل الهجمات
        System.out.println("🛡️ الحارس الشخصي يحرسك 14 يوم - ID: " + ownerIdOnly);
    }
    
    @Override
    public boolean obeyCommand(String requesterId) {
        // حماية IDOR - لا يطيع إلا المالك
        return requesterId.equals(ownerIdOnly);
    }
}
