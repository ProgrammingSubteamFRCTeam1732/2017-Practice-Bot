package org.usfirst.frc.team1732.robot.commands;

import java.util.Iterator;

import org.usfirst.frc.team1732.robot.Robot;

import com.ctre.phoenix.motion.TrajectoryPoint;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Use the drivetrain to follow a path.
 * 
 * Will erase other profiles that are currently being executed/loaded into the
 * talon.
 */
public class FollowPath extends Command {

	private final Iterator<TrajectoryPoint[]> iterator;

	public FollowPath(Iterator<TrajectoryPoint[]> iterator) {
		requires(Robot.drivetrain);
		this.iterator = iterator;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		// make sure updated gains are applied
		Robot.drivetrain.leftMPGains.applyToTalon(Robot.drivetrain.leftTalon1, 0, 0);
		Robot.drivetrain.rightMPGains.applyToTalon(Robot.drivetrain.rightTalon1, 0, 0);
		Robot.drivetrain.profileManager.startProfile(iterator);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.drivetrain.profileManager.run();
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return Robot.drivetrain.profileManager.isWaiting();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		// robot either holds last point or sits there in neutral output, depending on
		// if the trajectory's last points has the 'isLast' flag set
		// System.out.println("Path test is finished!");
	}
}
