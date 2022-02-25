package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Sensors extends SubsystemBase {
 
  private static double blue;
  private static double red;
  private static double green;
  private ColorSensorV3 colorSensor;
  public final static int threshold = 100;
  private static DigitalInput distanceSensor;
  private static DigitalInput distanceSensorFar;
  private static boolean shortDistance;
  private static boolean longDistance;



  private static String color;

 
  //                                          R,   G,    B
  private static final int[] blueValues = {3000, 7000, 10600};
  private static final int[] redValues = {2100, 400, 500};
 
  public Sensors() { 
    distanceSensor = new DigitalInput(1);
    distanceSensorFar = new DigitalInput(0);

    colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
    color = "";
  }

  @Override
  public void periodic() {

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
    

    return color;
    
  }
 
  public static boolean getDistanceNear() {
    shortDistance = distanceSensor.get();
    SmartDashboard.putBoolean("distance short", shortDistance);
    // .get() returns true if there is no ball, so these if statements make it so its true if there is a ball (easier to read/code )
    if (shortDistance) {
      shortDistance = false;
    }
    else if (shortDistance == false) {
      shortDistance = true;
    }
    return shortDistance;
  }
  public static boolean getDistanceLong() {
    longDistance = distanceSensorFar.get();
    SmartDashboard.putBoolean("distance long", longDistance);
    // .get() returns true if there is no ball normally, so this flips it to make it easier
    if (longDistance) {
      longDistance = false;
    }
    else if (longDistance == false) {
      longDistance = true;
    }
    return longDistance;
  }

  private static boolean threshold(double value1, double value2, double _threshold) {
    return Math.abs(value1 - value2) <= _threshold;
  }
}