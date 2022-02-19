package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;

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
  public final static int threshold = 1000;
  private DigitalInput distanceSensor;
  private boolean rawD;
  private static boolean refinedD;



  private static String color;

 
  //                                          R,   G,    B
  private static final int[] blueValues = {3000, 7000, 10600};
  private static final int[] redValues = {10000, 6000, 2000};
 
  public Sensors() { 
    distanceSensor = new DigitalInput(0);
    colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
    color = "";
  }

  @Override
  public void periodic() {
    rawD = distanceSensor.get();
    // converts raw distance to CM 
    refinedD = rawD;

    blue = colorSensor.getBlue();
    red = colorSensor.getRed();
    green = colorSensor.getGreen();
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

    SmartDashboard.putNumber("red", red);
    SmartDashboard.putNumber("green", green);
    SmartDashboard.putNumber("blue", blue);

    SmartDashboard.putString("color detected", color);
    
    SmartDashboard.putBoolean("distance", refinedD);
    return color;
    
  }
 
  public static boolean getDistance() {
    return refinedD;
  }

  private static boolean threshold(double value1, double value2, double _threshold) {
    return Math.abs(value1 - value2) <= _threshold;
  }
}