package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class Sensors extends SubsystemBase {
 
  private static double blue;
  private static double red;
  private static double green;
  private ColorSensorV3 colorSensor;
  public final static int threshold = 500;
  private AnalogInput distanceSensor;
  private int rawD;
  private static double refinedD;


  private static String color;

 
  //                                          R,   G,    B
  private static final int[] blueValues = {600, 1800, 1800};
  private static final int[] redValues = {1400, 1000, 400};
 
  public Sensors() { 
    distanceSensor = new AnalogInput(0);
    colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
    color = "";
  }

  @Override
  public void periodic() {
    rawD = distanceSensor.getValue();
    // converts raw distance to CM 
    refinedD = rawD * 0.125;

    blue = colorSensor.getBlue();
    red = colorSensor.getRed();
    green = colorSensor.getGreen();
    SmartDashboard.putNumber("distance shooter", refinedD);
  }
 
  public static String determineColour() {
    // Check blue
    if (threshold(blue, blueValues[2], threshold) && threshold(red, blueValues[0], threshold)) {
      color = "Blue";
    }
    // Check red
    else if (threshold(blue, redValues[2], threshold) && threshold(red, redValues[0], threshold)) {
      color = "Red";
    } else {
      color = "other";
    }

    SmartDashboard.putNumber("red distanceSensor", red);
    SmartDashboard.putNumber("green distanceSensor", green);
    SmartDashboard.putNumber("blue distanceSensor", blue);

    SmartDashboard.putString("color detected", color);
    return color;
    
  }
 
  public static double getDistance() {
    return refinedD;
  }

  private static boolean threshold(double value1, double value2, double _threshold) {
    return Math.abs(value1 - value2) <= _threshold;
  }
}