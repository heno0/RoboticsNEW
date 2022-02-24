// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Climber extends SubsystemBase {
  private DoubleSolenoid sol1;
  private DoubleSolenoid sol2;
  /** Creates a new Climber. */
  public Climber() {

    // set solenoid values (placeholder values)
    sol1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 2);

  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
    if (RobotContainer.joystick2.getRawButtonPressed(Constants.BBUTTON) == true) {
      // if you press the b button, then go up
      sol1.toggle();
    
    }
  }
  public void setClimber(Value value) {
    sol1.set(value);
    
  }
}