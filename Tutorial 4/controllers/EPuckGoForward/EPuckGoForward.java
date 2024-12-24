// File:          EPuckGoForward.java
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
// This class defines how to initialize and how to run your controller

public class EPuckGoForward {

  // This is the main function of your controller.
  // It creates an instance of your Robot instance and
  // it uses its function(s).
  // Note that only one instance of Robot should be created in
  // a controller program.
  // The arguments of the main function can be specified by the
  // "controllerArgs" field of the Robot node
  public static void main(String[] args) {
    
    double MAX_SPEED = 6.28;
    
    // create the Robot instance.
    Robot robot = new Robot();
    int timeStep = (int) Math.round(robot.getBasicTimeStep());
    
    DistanceSensor[] ps = new DistanceSensor[8];
    String[] psNames = {
      "ps0", "ps1", "ps2", "ps3", "ps4", "ps5",  "ps6", "ps7"};
    
    for (int i = 0; i < 8; i++) {
      ps[i] = robot.getDistanceSensor(psNames[i]);
      ps[i].enable(timeStep);
    }
    
    Motor leftMotor = robot.getMotor("left wheel motor");
    Motor rightMotor = robot.getMotor("right wheel motor");
    
    leftMotor.setPosition(Double.POSITIVE_INFINITY);
    rightMotor.setPosition(Double.POSITIVE_INFINITY);
    
    leftMotor.setVelocity(0.0);
    rightMotor.setVelocity(0.0);

    while (robot.step(timeStep) != -1) {
      // Read the sensors:
      double[] psValues = {0, 0, 0, 0, 0, 0, 0, 0};
      for (int i = 0; i < 8; i++) {
        psValues[i] = ps[i].getValue();
      }
      
      // Detect objects
      boolean right_obstacle = 
        psValues[0] > 80.0 ||
        psValues[1] > 80.0 ||
        psValues[2] > 80.0;
      boolean left_obstacle =
        psValues[5] > 80.0 ||
        psValues[6] > 80.0 ||
        psValues[7] > 80.0;

      // Process sensor data here.
      
      double leftSpeed = 0.5 * MAX_SPEED;
      double rightSpeed = 0.5 * MAX_SPEED;
      
      if (right_obstacle) {
        // turn left
        leftSpeed = -0.5 * MAX_SPEED;
        rightSpeed = 0.5 * MAX_SPEED;
      }
      
      if (left_obstacle) {
        // turn right
        leftSpeed = 0.5 * MAX_SPEED;
        rightSpeed = -0.5 * MAX_SPEED;
      }
      
      rightMotor.setVelocity(rightSpeed);
      leftMotor.setVelocity(leftSpeed);
      // Enter here functions to send actuator commands, like:
      //  motor.setPosition(10.0);
    };

    // Enter here exit cleanup code.
  }
}
