package org.usfirst.frc.team1732.robot.controlutils;

public class Feedforward {

	public static final double TALON_SRX_FF_GAIN = 1023 / 12;

	public final double fkV;
	public final double fkA;
	public final double fVintercept;
	public final double bkV;
	public final double bkA;
	public final double bVintercept;

	public Feedforward(double fkV, double fkA, double fVintercept, double bkV, double bkA, double bVintercept) {
		super();
		this.fkV = fkV;
		this.fkA = fkA;
		this.fVintercept = fVintercept;
		this.bkV = bkV;
		this.bkA = bkA;
		this.bVintercept = bVintercept;
	}

	public double getAppliedVoltage(double velocity, double acceleration) {
		if (velocity >= 0) {
			return fkV * velocity + fkA * acceleration + fVintercept;
		} else {
			return bkV * velocity + bkA * acceleration + bVintercept;
		}
	}

	public double getInitialAcceleration(double x, double t, double v0) {
		double numerator = x - t * v0;
		double denominator;
		if (v0 >= 0) {
			double av = fkA / fkV;
			denominator = t * av + av * av * (Math.exp(-fkV / fkA * t)));
		} else {
			double av = bkA / bkV;
			denominator = t * av + av * av * (Math.exp(-bkV / bkA * t)));
		}
		return numerator/denominator;
	}

	public double getVelocityAtTime(double v0, double a0, double t) {
		if (v0 >= 0) {
			return v0 + fkA / fkV * a0 - fkA * a0 / (fkV * Math.exp(fkV / fkA * t));
		} else {
			return v0 + bkA / bkV * a0 - bkA * a0 / (bkV * Math.exp(bkV / bkA * t));
		}
	}
}