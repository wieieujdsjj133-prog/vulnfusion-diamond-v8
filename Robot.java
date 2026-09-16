package com.vulnfusion.diamond;

public abstract class Robot {
    protected boolean taskCompleted = false;
    protected String name;

    public abstract void execute(String task);

    public boolean isTaskCompleted() {
        return taskCompleted;
    }

    public String getName() {
        return name != null ? name : this.getClass().getSimpleName();
    }

    public void fixAutomatically() {
        System.out.println("🔧 اصلاح تلقائي لـ " + getName() + ": Restart + Reset Memory + Retraining");
        taskCompleted = true;
    }
}
