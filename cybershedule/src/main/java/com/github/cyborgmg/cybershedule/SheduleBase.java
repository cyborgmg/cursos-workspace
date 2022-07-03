package com.github.cyborgmg.cybershedule;

import java.util.Calendar;

public abstract class SheduleBase implements Shedulable{
	
	private Integer minuteCorrente_;
	
	private Integer minuteAnterior_=0;
	
	private Integer count=0;
	
	private boolean canCount = true;
	
	private Thread thread;
	
	private boolean running = true;

	private boolean canTimeCheck_() {
		
		try {
			Calendar now = Calendar.getInstance();
			Integer minute = now.get(Calendar.MINUTE);
			Integer second = now.get(Calendar.SECOND);
		
			minuteCorrente_ = minute;
			
			return (minute % 1 == 0)&(second==0);
			
		} catch (Exception e) {
			return false;
		}
	}
	
	protected void start_(String threadname) {
		
		thread = new Thread(threadname) {
			public void run() {
				while(running) {
					if(canTimeCheck_()&&(minuteCorrente_!=minuteAnterior_)) {
						
						if(canCount) {
							count=minuteCorrente_;
							canCount=false;
						}
						
						execute();
						
						count++;
						
						minuteAnterior_=minuteCorrente_;
					}
				}				
			}  
		};
		
		thread.start();
	}

	protected void zeraCount() {
		count=0;
	}
	
	protected Integer getCount() {
		return count;
	}
	
	public void stop() {
		running = false;
		if(thread!=null) {
			thread.stop();
		}
	}
}
