package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;

import java.security.DigestInputStream;

import com.fasterxml.jackson.databind.deser.std.NumberDeserializers.ShortDeserializer;
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
  private static DigitalInput distanceSensor;
  private static DigitalInput distanceSensorFar;
  private static boolean shortDistance;
  private static boolean longDistance;

  private static boolean distanceBOO = false;

  private static String color;

 
  //                                          R,   G,    B
  private static final int[] blueValues = {3000, 7000, 10600};
  private static final int[] redValues = {10000, 6000, 2000};
 
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
    getDistanceLong(); getDistanceNear();
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

  public static DriverStation.Alliance determineFMSColor() {
    if (threshold(blue, blueValues[2], threshold) && threshold(red, blueValues[0], threshold)) {
      color = DriverStation.Alliance.Blue;
    }
    // Check red
    else if (threshold(blue, redValues[2], threshold) && threshold(red, redValues[0], threshold)) {
      color = DriverStation.Alliance.Red;
    } else {
      color = DriverStation.Alliance.Invalid;
    }
  }
 
  public static boolean getDistanceNear() {
    shortDistance = distanceSensor.get();
    SmartDashboard.putBoolean("distance short", shortDistance);
    return shortDistance;
  }
  public static boolean getDistanceLong() {
    longDistance = distanceSensorFar.get();
    SmartDashboard.putBoolean("distance long", longDistance);
    return longDistance;
  }

  private static boolean threshold(double value1, double value2, double _threshold) {
    return Math.abs(value1 - value2) <= _threshold;
  }
}