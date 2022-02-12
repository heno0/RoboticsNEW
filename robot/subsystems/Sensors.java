package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class Sensors extends SubsystemBase {
 
  private double blue;
  private double red;
 
  public final int threshold = 500;
 
  //                                          R,   G,    B
  private static final int[] blueValues = {600, 1800, 1800};
  private static final int[] redValues = {1400, 1000, 400};
 
  public Sensors() { }

  @Override
  public void periodic() {
      blue = Robot.container.colourSensor.getColourSensorBlue();
      red = Robot.container.colourSensor.getColourSensorRed();
 
      // Debug stuff
      System.out.println("Colour sensor detected colour: " + determineColour());      

      /* If you want to use  instead*/
      SmartDashboard.putNumber("Colour sensor blue", blue);
      SmartDashboard.putNumber("Colour sensor red", red);
     
  }
 
  public String determineColour() {
      // Check blue
      if (threshold(blue, blueValues[2], threshold) && threshold(red, blueValues[0], threshold)) {
          return "Blue";
      }
      // Check red
      else if (threshold(blue, redValues[2], threshold) && threshold(red, redValues[0], threshold)) {
          return "Red";
      } else {
          return "No/other colour detected";
      } 
  }
 
  private boolean threshold(double value1, double value2, double _threshold) {
      return Math.abs(value1 - value2) <= _threshold;
  }
}