package com.vulnfusion.diamond;
public class CoderRobot extends Robot {
    public CoderRobot() { this.name = "المبرمج 💻"; }
    public void execute(String task) {
        System.out.println("المبرمج: اكتب الكود 100x لـ " + task);
        taskCompleted = true;
    }
}
