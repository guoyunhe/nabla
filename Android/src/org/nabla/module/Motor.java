package org.faguzo.nabla.module;

import org.faguzo.nabla.system.Hub;

public class Motor extends Element {
	private int speed;
	public static final int STOP = 0;
	public static final int MAX = 255;

	public Motor() {

	}

	public Motor(int motorid) {
		id = motorid;
	}

	public Motor(int motorid, Hub motorhub) {
		id = motorid;
		hub = motorhub;
	}

	public void stop() {
		setSpeed(STOP);
	}

	public void setSpeed(int motorspeed) {
		if (motorspeed < -MAX) {
			speed = -MAX;
		} else if (motorspeed > MAX) {
			speed = MAX;
		} else {
			speed = motorspeed;
		}
		hub.output("id" + "," + Integer.toString(id) + ";" + "speed" + ","
				+ Integer.toString(speed) + ";");
	}

}
