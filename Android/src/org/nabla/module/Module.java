package org.nabla.module;

import org.nabla.system.Hub;

public class Module {
	//every logic element has a unique id
	int id;
	
	Hub hub;
	
	public void setID(int ID) {
		id = ID;
	}
	
	public int getID(){
		return id;
	}
	
	public void setHub(Hub h){
		hub = h;
	}
	
	public Hub getHub(){
		return hub;
	}
}
