// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  private static CANSparkMax motor1;
  private static CANSparkMax motor2;
  private static Spark logMotor;

  private static Joystick joystick;


  private double rStick;
  private double pow3;
  /** Creates a new Shooter. */
  public Shooter() {
    // top 2 motors
    motor1 = new CANSparkMax(5, MotorType.kBrushless);
    motor2 = new CANSparkMax(6, MotorType.kBrushless);

    // bottom motor and rollercoaster
    logMotor = new Spark(1);

    joystick = new Joystick(0);

    rStick = joystick.getRawAxis(Constants.RIGHTSTICK);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    // math calculations
    // link:https://www.desmos.com/calculator/wm5vyubbee
    rStick = (Math.pow(rStick, 2)) + .1;
    pow3 = rStick/2.5;

    // maxmin
    rStick = Constants.maxmin(rStick, 1);
    pow3 = Constants.maxmin(pow3, 1);

    if (Math.abs(rStick) < .15) {
      rStick = 0;
    }
    if (Math.abs(pow3) < .1) {
      pow3 = 0;
    }

    // setting motors
    motor1.set(rStick);
    motor2.set(-rStick);
    logMotor.set(pow3);
  }
}
