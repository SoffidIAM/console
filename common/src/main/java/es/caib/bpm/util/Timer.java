package es.caib.bpm.util;
import java.util.Date;

import org.apache.log4j.Logger;


public class Timer {
	public Date init=null;
	public Timer timers[];
	
	public Timer(){
		init=new Date();
	};
	
	public Timer(Timer[] timers){
		init=new Date();
		this.timers=timers;
	};
	
	
	public void reset(){
		init=new Date();
	}

	public void resetAll(){
		if(timers!=null){
			for(int i=0;i<timers.length;i++)
				timers[i].reset();
		}

		init=new Date();
	}
	
	public long time(){
		
		return new Date().getTime()-init.getTime();
	}

	public void logTime(String title,int milisToLog){
		
		long dif= new Date().getTime()-init.getTime();
		long maxDif=dif;
		
		String timeString=title+": "+dif+""; //$NON-NLS-1$ //$NON-NLS-2$
		if(timers!=null){
			for(int i=0;i<timers.length;i++){
				long t=timers[i].time();
				if(t>maxDif) maxDif=t;
				timeString+=" "+t; //$NON-NLS-1$
			}
		}
		
		if(maxDif<milisToLog) return;
		
		Logger.getLogger(this.getClass()).warn(timeString);
	}
	
}
