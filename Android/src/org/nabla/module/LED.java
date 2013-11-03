package org.nabla.module;

import org.nabla.system.Hub;

public class LED extends Module {
	private boolean status;

	public final static boolean ON = true;
	public final static boolean OFF = false;

	public LED() {

	}

	public LED(int ledid) {
		id = ledid;
	}

	public LED(int ledid, Hub ledhub) {
		id = ledid;
		hub = ledhub;
	}

	void on() {
		status = ON;
		hub.output("LED" + " " + Integer.toString(id) + " " + "O"
				+ Boolean.toString(status));
	}

	void off() {
		status = OFF;
		hub.output("LED" + " " + Integer.toString(id) + " " + "O"
				+ Boolean.toString(status));
	}

	void setStatus(boolean onoff) {
		status = onoff;
		hub.output("LED" + " " + Integer.toString(id) + " " + "O"
				+ Boolean.toString(status));
	}

	boolean getStatus() {
		return status;
	}

}
