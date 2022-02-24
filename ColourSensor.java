// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.I2C;

import com.revrobotics.ColorSensorV3;

public class ColourSensor extends SubsystemBase {
  /** Creates a new Sensors. */
  private ColorSensorV3 colourSensor;

   
  public ColourSensor() {
    colourSensor = new ColorSensorV3 (I2C.Port.kOnboard);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

    public int getColourSensorRed(){
    return colourSensor.getRed();
  }

  public int getColourSensorGreen() {
    return colourSensor.getGreen();
  }

  public int getColourSensorBlue() {
    return colourSensor.getBlue();
  }

}
