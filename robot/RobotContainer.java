// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CameraServerCvJNI;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AutoMove;
import frc.robot.commands.AutoPlan;
import frc.robot.commands.IncrementalShooter;
import frc.robot.commands.LimelightRotate;
import frc.robot.commands.LimelightShooter;
import frc.robot.commands.MecanumCommand;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Indexer;
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
  public MecanumCommand mecanumCommand;
  private AutoMove autoMove;
  private Limelight limelight;
  private LimelightRotate limelightRotate;
  private Intake intake;

  private IncrementalShooter shooterCommand;
  private Shooter shooter;
  private Sensors sensors;
  private Compressor compressor;


  //AT
  private Climber climber;

  private Indexer indexer;

  public static Joystick joystick;
  public static Joystick joystick2;

  public JoystickButton a;
  public JoystickButton a2;
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings

    // mecanum wheel command and subsystem
    mecanumSubsystem = new MecanumSubsystem();
    mecanumCommand = new MecanumCommand(mecanumSubsystem);

    // shooter
    shooter = new Shooter();
    shooterCommand = new IncrementalShooter(shooter);

     //limelight
    limelight = new Limelight();

    // sensors
    sensors = new Sensors();

    //intake
    intake = new Intake();

    //Climber  AT
    climber = new Climber();

    indexer = new Indexer();

    joystick = new Joystick(Constants.JOYSTICKID);
    joystick2 = new Joystick(Constants.SECONDARYJOYSTICK);

    a = new JoystickButton(joystick, Constants.ABUTTON);
    a2 = new JoystickButton(joystick2, Constants.ABUTTON);

    compressor = new Compressor(0, PneumaticsModuleType.CTREPCM);
    compressor.enableDigital();
    compressor.disable();


    
    configureButtonBindings();

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // while a button on driver controller is held, run auto limelight rotate
    a.whileActiveContinuous(new LimelightRotate(mecanumSubsystem));

    // while a button on shooter controller is held, run auto limelight shooter
    a2.whileActiveContinuous(new LimelightShooter(shooter));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // the command set here will run in autonomous
    return new AutoPlan(mecanumSubsystem);
  }
}