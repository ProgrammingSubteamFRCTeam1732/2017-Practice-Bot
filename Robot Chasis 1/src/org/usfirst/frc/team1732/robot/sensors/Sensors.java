package org.usfirst.frc.team1732.robot.sensors;

import org.usfirst.frc.team1732.robot.Robot;
import org.usfirst.frc.team1732.robot.sensors.encoders.TalonEncoder;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;

public class Sensors {

	public final TalonEncoder leftEncoder = Robot.drivetrain.leftEncoder;
	public final TalonEncoder rightEncoder = Robot.drivetrain.rightEncoder;

	public final AHRS navX = new AHRS(SPI.Port.kMXP);

}