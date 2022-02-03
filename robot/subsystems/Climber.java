// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
  private Compressor compressor;
  private DoubleSolenoid sol1;
  private DoubleSolenoid sol2;
  private Joystick joystick;
  /** Creates a new Climber. */
  public Climber() {
    // compressor
    compressor = new Compressor(PneumaticsModuleType.REVPH);

    // solenoids
    sol1 = new DoubleSolenoid(PneumaticsModuleType.REVPH, 4, 5);
    sol2 = new DoubleSolenoid(PneumaticsModuleType.REVPH, 6, 7);

    // joystick
    joystick = new Joystick(Constants.JOYSTICKID);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if (joystick.getRawButton(Constants.BBUTTON) == true) {
      sol1.set(Value.kForward);
      sol2.set(Value.kForward);
    } else {
      sol1.set(Value.kReverse);
      sol2.set(Value.kReverse);
    }
  }
}