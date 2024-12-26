// File:          four_wheeled_collision_avoidance.java
// Date:
// Description:
// Author:
// Modifications:

// You may need to add other webots classes such as
//  import com.cyberbotics.webots.controller.DistanceSensor;
//  import com.cyberbotics.webots.controller.Motor;
import com.cyberbotics.webots.controller.Robot;
import com.cyberbotics.webots.controller.Motor;
import com.cyberbotics.webots.controller.DistanceSensor;

// Here is the main class of your controller.
// This class defines how to initialize and how to run your controller.
public class four_wheeled_collision_avoidance {

  // This is the main function of your controller.
  // It creates an instance of your Robot instance and
  // it uses its function(s).
  // Note that only one instance of Robot should be created in
  // a controller program.
  // The arguments of the main function can be specified by the
  // "controllerArgs" field of the Robot node
  public static void main(String[] args) {

    // create the Robot instance.
    Robot robot = new Robot();

    // get the time step of the current world.
    int TIME_STEP = (int) Math.round(robot.getBasicTimeStep());
    
    // initialize sensors
    DistanceSensor[] ds = new DistanceSensor[2];
    String[] dsNames = {"ds_right", "ds_left"};
    for (int i = 0; i < dsNames.length; i++) {
      ds[i] = robot.getDistanceSensor(dsNames[i]);
      ds[i].enable(TIME_STEP);
    }

    // Initalize motors
    Motor[] wheels = new Motor[4];
    String[] wheelsNames = {"Motor1", "Motor2", "Motor3", "Motor4"};
    for (int i = 0; i < wheelsNames.length; i++) {
      wheels[i] = robot.getMotor(wheelsNames[i]);
      wheels[i].setPosition(Double.POSITIVE_INFINITY);
      wheels[i].setVelocity(0.0);
    }
    int avoidObstacleCounter = 0;
    
    
    double speed = -1.5; // [rad/s]
    
    while (robot.step(TIME_STEP) != -1) {
      double leftSpeed = 1.0;
      double rightSpeed = 1.0;
      if (avoidObstacleCounter > 0) {
        avoidObstacleCounter--;
        leftSpeed = 1.0;
        rightSpeed = -1.0;
      } else {
      for (int i = 0; i < dsNames.length; i++) {
        if (ds[i].getValue() < 950.0) {
          avoidObstacleCounter = 100;
        }
      }
      }
      
      wheels[0].setVelocity(leftSpeed);
      wheels[1].setVelocity(leftSpeed);
      wheels[2].setVelocity(rightSpeed);
      wheels[3].setVelocity(rightSpeed);
    };

    // Enter here exit cleanup code.
  }
}
