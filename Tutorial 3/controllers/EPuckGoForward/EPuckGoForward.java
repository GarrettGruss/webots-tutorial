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

// Here is the main class of your controller.
// This class defines how to initialize and how to run your controller.
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
    
    Motor leftMotor = robot.getMotor("left wheel motor");
    Motor rightMotor = robot.getMotor("right wheel motor");
    
    leftMotor.setPosition(Double.POSITIVE_INFINITY);
    rightMotor.setPosition(Double.POSITIVE_INFINITY);
    
    leftMotor.setVelocity(0.1 * MAX_SPEED);
    rightMotor.setVelocity(0.1 * MAX_SPEED);

    // get the time step of the current world.
    

    // You should insert a getDevice-like function in order to get the
    // instance of a device of the robot. Something like:
    //  Motor motor = robot.getMotor("motorname");
    //  DistanceSensor ds = robot.getDistanceSensor("dsname");
    //  ds.enable(timeStep);
    int timeStep = (int) Math.round(robot.getBasicTimeStep());
    // Main loop:
    // - perform simulation steps until Webots is stopping the controller
    while (robot.step(timeStep) != -1) {
      // Read the sensors:
      // Enter here functions to read sensor data, like:
      //  double val = ds.getValue();

      // Process sensor data here.

      // Enter here functions to send actuator commands, like:
      //  motor.setPosition(10.0);
    };

    // Enter here exit cleanup code.
  }
}
