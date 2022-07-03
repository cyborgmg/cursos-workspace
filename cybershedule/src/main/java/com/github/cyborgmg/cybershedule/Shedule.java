package com.github.cyborgmg.cybershedule;

import java.util.Date;

public class Shedule extends SheduleBase {
	
	
	
	private Shedulable shedulable;
	private Integer timeInTime;
	
	public Shedule(Shedulable shedulable, Integer timeInTime) {
		super();
		this.shedulable = shedulable;
		this.timeInTime = timeInTime;
	}

	public void start(String threadname) {
		
		super.start_(threadname);
			
	}

	@Override
	public void execute() {
		
		if(getCount()%timeInTime==0) {
			
			zeraCount();
			
			new Thread() {
				public void run() {
					shedulable.execute();
				}
			}.start();	
			
		}
		
	}

	
}