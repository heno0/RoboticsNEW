// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.commands.IntakeCommand;

public class Intake extends SubsystemBase {
  private Spark motor;
  private Joystick joystick;
  private boolean activate;
  /** Creates a new Intake. */
  public Intake(boolean active) {
    motor = new Spark(0);
    active = activate;
    joystick = new Joystick(0);

    setDefaultCommand(new IntakeCommand(true));

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (joystick.getPOV() == 90 || activate == true) {
      motor.set(-.7);
    } else if (joystick.getPOV() == 180 || activate == false) {
      motor.set(0);
    }
  }
}
