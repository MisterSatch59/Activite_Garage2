package com.garage.observer;

public interface Observer {

	public void update();
	
	public void updateWithErrorMessage(String message);
}
