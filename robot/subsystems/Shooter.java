// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  private static CANSparkMax motor1;
  private static CANSparkMax motor2;
  private static CANSparkMax motor3;
  private static CANSparkMax motor4;

  private Joystick joystick;

  /** Creates a new Shooter. */
  public Shooter() {
    joystick = new Joystick(0);

    motor1 = new CANSparkMax(5, MotorType.kBrushless);
    motor2 = new CANSparkMax(6, MotorType.kBrushless);
    motor3 = new CANSparkMax(7, MotorType.kBrushless);
    //motor4 = new CANSparkMax(8, MotorType.kBrushless);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }

  public static void shooterSpeed(double poop, double stick) {
    
    if (stick > .5) {
      poop = (double) 0.5;
    } else {
      poop = Math.min(stick, poop);
    }

    motor1.set(stick);
    motor2.set(stick);
    //motor3.set(poop);
    //motor4.set(poop);
  }
}
