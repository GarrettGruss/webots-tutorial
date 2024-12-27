// File:          supervisor.java
// Date:
// Description:
// Author:
// Modifications:
import com.cyberbotics.webots.controller.Supervisor;
import com.cyberbotics.webots.controller.Node;
import com.cyberbotics.webots.controller.Field;


// Here is the main class of your controller.
// This class defines how to initialize and how to run your controller.
public class supervisor {

  public static void main(String[] args) {

    Supervisor robot = new Supervisor();

    // get the time step of the current world.
    int timeStep = (int) Math.round(robot.getBasicTimeStep());  
    
    Node bb8Node = robot.getFromDef("BB-8");
    if (bb8Node == null) {
      System.out.println("Node 'BB-8' not found. Ensure 'DEF BB-8' is correctly defined in the Webots world.");
      return; // Exit the program to prevent further errors
    }
    Field translationField = bb8Node.getField("translation");
    if (translationField == null) {
        System.out.println("Field 'translation' not found in node 'BB-8'. Check the Webots world file.");
        return; // Exit the program to prevent further errors
    }
    
    // Import ball for maniupulation
    Node rootNode = robot.getRoot();
    Field childrenField = rootNode.getField("children");
    childrenField.importMFNodeFromString(-1, "DEF BALL Ball { Translation 0 1 1 }");
    Node ballNode = robot.getFromDef("BALL");
    Field colorField = ballNode.getField("color");
    
    int i = 0;
    while (robot.step(timeStep) != -1) {
      double position[] = ballNode.getPosition();
        
      if (position[2] < 0.2) {
        double redColor[] = {1, 0, 0};
        colorField.setSFColor(redColor);
              System.out.println("Ball position: " + position[0] +
        " " + position[1] + 
        " " + position[2]);
      }
      i++;
    };

    // Enter here exit cleanup code.
  }
}
