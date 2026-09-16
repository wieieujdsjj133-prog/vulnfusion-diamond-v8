package com.vulnfusion.diamond;

public class CheckerRobot extends Robot {
    public CheckerRobot() { this.name = "المدقق 🔍"; }

    @Override
    public void execute(String task) {
        System.out.println("🔍 المدقق: افحص جودة الكود...");
        this.taskCompleted = true;
    }

    public boolean checkQuality(String code) {
        return !code.contains("TODO") && !code.contains("id=5");
    }
}
