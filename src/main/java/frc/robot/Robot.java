// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.*;
// import com.revrobotics.Rev2mDistanceSensor.Port;

import com.revrobotics.ColorSensorV3.RawColor;
import edu.wpi.first.wpilibj.I2C.Port;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.Orchestra;
import com.ctre.phoenix6.StatusCode;
import com.ctre.phoenix6.controls.MusicTone;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
  private final TalonFX musicMotor = new TalonFX(19);
  private final Orchestra orchestra = new Orchestra();
  private final DigitalInput input = new DigitalInput(0);

  // private Rev2mDistanceSensor distOnboard;
  // private Rev2mDistanceSensor distMXP;

  private static ColorSensorV3 colorSensorLow = new ColorSensorV3(Port.kMXP);

  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);

    SmartDashboard.putData("Auto choices", m_chooser);

    StatusCode error = orchestra.loadMusic("zelda.chrp");
    orchestra.addInstrument(musicMotor);

    // distOnboard = new Rev2mDistanceSensor(Port.kOnboard);
    // distMXP = new Rev2mDistanceSensor(Port.kMXP);
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items
   * like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different
   * autonomous modes using the dashboard. The sendable chooser code works with
   * the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the
   * chooser code and
   * uncomment the getString line to get the auto name from the text box below the
   * Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional comparisons to the
   * switch structure
   * below with additional strings. If using the SendableChooser make sure to add
   * them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {
    // distOnboard.setAutomaticMode(true);
    StatusCode status = orchestra.play();
    System.out.println(status);

  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    // System.out.println(orchestra.isPlaying());

    SmartDashboard.putBoolean("Break?", input.get());

    // boolean isNote = false;
    // double proximity = colorSensorLow.getProximity();

    // if (colorSensorLow.getRed() > 350 && colorSensorLow.getRed() < 750 &&
    // colorSensorLow.getBlue() < 275
    // && colorSensorLow.getBlue() > 50 && colorSensorLow.getGreen() < 525 &&
    // colorSensorLow.getGreen() > 250) {
    // isNote = true;
    // } else {
    // isNote = false;
    // }
    // SmartDashboard.putNumber("Proximity", colorSensorLow.getProximity());

    // SmartDashboard.putNumber("Red", colorSensorLow.getRed());
    // SmartDashboard.putNumber("Green", colorSensorLow.getGreen());
    // SmartDashboard.putNumber("Blue", colorSensorLow.getBlue());

    // SmartDashboard.putBoolean("Note", isNote);

    // if(distOnboard.isRangeValid()) {
    // SmartDashboard.putNumber("Range Onboard", distOnboard.getRange());
    // SmartDashboard.putNumber("Timestamp Onboard", distOnboard.getTimestamp());
    // }

    // if(distMXP.isRangeValid()) {
    // SmartDashboard.putNumber("Range MXP", distMXP.getRange());
    // SmartDashboard.putNumber("Timestamp MXP", distMXP.getTimestamp());

  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {
    orchestra.stop();
  }

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {
  }

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {
  }

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {
  }
}