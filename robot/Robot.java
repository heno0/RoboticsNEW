// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;
  
  public static Joystick joystick2;

  public UsbCamera frontCamera;
  public UsbCamera climbingCamera;

  public NetworkTableEntry table;

  public String cameraType = "";

  public DriverStation.Alliance allianceColor;


  SendableChooser<String> m_startingMode = new SendableChooser<>();
  SendableChooser<String> m_autoPosition = new SendableChooser<>();
  SendableChooser<String> m_autoRoutine = new SendableChooser<>();

  

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    
    frontCamera = CameraServer.startAutomaticCapture(0);
    climbingCamera = CameraServer.startAutomaticCapture(1);

    table = NetworkTableInstance.getDefault().getTable("").getEntry("CameraSelection");

    frontCamera.setResolution(640, 480);
    climbingCamera.setResolution(640, 480);

    joystick2 = new Joystick(Constants.SECONDARYJOYSTICK);

    allianceColor = DriverStation.getAlliance();


    m_startingMode.setDefaultOption("Starting in Teleop", "teleop");
    m_startingMode.addOption("Starting in Autonomous", "auto");

    SmartDashboard.putData("Auto Starting Mode", m_startingMode);

    m_autoRoutine.setDefaultOption("Single Cargo Ship ", "singleCargo");
    m_autoRoutine.addOption("Side Cargo Ship", "sideCargo");
    m_autoRoutine.addOption("Single Rocket", "singleRocket");
    m_autoRoutine.addOption("Dual Rocket", "dualRocket");
    m_autoRoutine.addOption("Dual Cargo Ship", "dualCargo");
 

    SmartDashboard.putData("Auto Routine", m_autoRoutine);


    m_autoPosition.setDefaultOption("Left Position", "L");
    m_autoPosition.addOption("Right Position", "R");

    SmartDashboard.putData("Auto Starting Position", m_autoPosition);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {

    String mode = m_startingMode.getSelected();
    String routine = m_autoRoutine.getSelected();
    String position = m_autoPosition.getSelected();

    m_autonomousCommand = m_robotContainer.getAutonomousCommand();



    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }

  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    if (joystick2.getRawButton(8)) {
      cameraType = "Climbing Camera";
      table.setString(climbingCamera.getName());
    }
    else {
      cameraType = "Front Camera";
      table.setString(frontCamera.getName());
    }
    SmartDashboard.putString("Camera Selected", cameraType);
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}
