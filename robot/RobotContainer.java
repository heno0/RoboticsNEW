// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.AutoMove;
import frc.robot.commands.AutoPlan;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.MecanumCommand;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.MecanumSubsystem;
import frc.robot.subsystems.Sensors;
import frc.robot.subsystems.Shooter;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public MecanumSubsystem mecanumSubsystem;
  private MecanumCommand mecanumCommand;
  private AutoMove autoMove;
  private Limelight limelight;
  private Intake intake;
  private IntakeCommand intakeCommand;
  private Shooter shooter;
  private Sensors sensors;

  //AT
  private Climber climber;

  public static Joystick joystick;
  public static Joystick joystick2;
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // mecanum wheel command and subsystem
    mecanumSubsystem = new MecanumSubsystem();
    mecanumCommand = new MecanumCommand(mecanumSubsystem);

    // shooter
    shooter = new Shooter();

     //limelight
    limelight = new Limelight();

    // sensors
    sensors = new Sensors();

    //intake
    intake = new Intake();
    intakeCommand = new IntakeCommand(intake);

    //Climber  AT
    climber = new Climber();

    joystick = new Joystick(Constants.JOYSTICKID);
    joystick2 = new Joystick(Constants.SECONDARYJOYSTICK);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // the command set here will run in autonomous
    return new AutoPlan();
  }
}