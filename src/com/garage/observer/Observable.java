package com.garage.observer;

public interface Observable {

	public void addObserver(Observer obs);

	public void updateObserverWithErrorMessage();
	
	public void updateObserver();

	public void delObserver();
}
