package com.vulnfusion.diamond;

import java.util.Arrays;
import java.util.List;

public class AIFactory {
    // قائمة الروبوتات السبعة
    List<Robot> robots = Arrays.asList(
        new CoderRobot(),      // 1. المبرمج
        new ReviewerRobot(),   // 2. المدقق
        new SecurityRobot(),   // 3. حارس الأمن - 18 أداة
        new BuilderRobot(),    // 4. البناء
        new SupervisorRobot(), // 5. المشرف العام
        new GuardianPersonalRobot("owner"), // 6. الحارس الشخصي
        new InspectorRobot()   // 7. المفتش المصلح
    );

    public void startFactory(String prompt) {
        for(Robot robot : robots) {
            robot.execute(prompt);
            // المشرف العام يفحص ويرجع العمل مع شرح إذا في غلط
            if(robot instanceof SupervisorRobot) {
                ((SupervisorRobot) robot).validatePreviousWork(robots);
            }
        }
    }
    
    public List<Robot> getRobots() { return robots; }
}
