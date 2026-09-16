package com.vulnfusion.diamond;

public class BuilderRobot extends Robot {
    public BuilderRobot() { this.name = "البناء 🏗️"; }

    @Override
    public void execute(String task) {
        System.out.println("🏗️ البناء: ابني موقع / APK لـ: " + task);
        this.taskCompleted = true;
    }
}
