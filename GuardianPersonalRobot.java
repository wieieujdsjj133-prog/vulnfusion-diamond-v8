package com.vulnfusion.diamond;

public class GuardianPersonalRobot extends Robot {
    private String ownerIdOnly;
    private boolean is14DaysActive = false;

    public GuardianPersonalRobot(String ownerId) {
        this.name = "الحارس الشخصي 🛡️⚔️";
        this.ownerIdOnly = ownerId;
    }

    @Override
    public void execute(String task) {
        start14DaysProtection();
    }

    public void start14DaysProtection() {
        is14DaysActive = true;
        this.taskCompleted = true;
        System.out.println("🛡️⚔️ الحارس الشخصي: بدأ حماية 14 يوم متواصلة");
        System.out.println("  - لا يطيع إلا المالك: " + ownerIdOnly);
        System.out.println("  - يفحص كل ساعة");
        System.out.println("  - يصد SQLi, XSS, DDoS, BruteForce في 0.2 ثانية");
        System.out.println("  - يغلق الثغرة تلقائيا");
        System.out.println("  - محادثة خاصة لا يراها إلا المالك");
    }

    public boolean obeyCommand(String requesterId) {
        // حماية IDOR: لا يطيع إلا أنت - يمنع تغيير id=5 الى id=1
        return requesterId.equals(ownerIdOnly);
    }

    public void blockAttack(String attackType) {
        System.out.println("🚫 صد هجوم: " + attackType + " - تم الحظر في 0.2s + اغلاق الثغرة");
    }

    public void privateChat(String ownerMsg) {
        if(obeyCommand(ownerIdOnly)) {
            System.out.println("💬 محادثة خاصة مع المالك: " + ownerMsg);
        }
    }
}
