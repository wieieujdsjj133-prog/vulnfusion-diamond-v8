package com.vulnfusion.diamond;

import java.util.Arrays;
import java.util.List;

public class AIFactory {
    private List<Robot> robots;

    public AIFactory() {
        robots = Arrays.asList(
            new CoderRobot(),
            new CheckerRobot(),
            new SecurityRobot(),
            new BuilderRobot(),
            new SupervisorRobot(),
            new GuardianPersonalRobot("owner"),
            new InspectorRobot()
        );
    }

    public void startFactory(String prompt) {
        System.out.println("🏭 مصنع 7 روبوتات يبدأ: " + prompt);
        for(Robot robot : robots) {
            robot.execute(prompt);
            // المشرف العام يفحص ويرجع العمل مع شرح
            if(robot instanceof SupervisorRobot) {
                ((SupervisorRobot) robot).validatePreviousWork(robots);
            }
        }
    }

    public List<Robot> getRobots() {
        return robots;
    }
}
