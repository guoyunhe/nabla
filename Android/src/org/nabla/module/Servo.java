package org.faguzo.nabla.module;

import org.nabla.system.Hub;


public class Servo extends Element {
	private int position;
	public static final int ORIGINAL = 0;
	public static final int MAX = 179;
	
	public Servo(){
		
	}
	
	public Servo(int servoid){
		id = servoid;
	}
	
	public Servo(int servoid, Hub servohub){
		id = servoid;
		hub = servohub;
	}
	
	public void setPosition(int servoposition){
		position = servoposition;
		hub.output("id" + "," + Integer.toString(id) + ";" +
				"position" + "," + Integer.toString(position) + ";");
	}
}
