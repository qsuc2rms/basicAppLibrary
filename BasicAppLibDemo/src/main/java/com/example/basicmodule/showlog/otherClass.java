package com.example.basicmodule.showlog;
import org.apache.log4j.Logger;

import com.baplib.log.BapLogger;
import com.example.basicapplibdemo.MainActivity;

import android.app.Activity;
import android.os.Bundle;

public class otherClass   { 
private BapLogger logger=BapLogger.getBapLogger(otherClass.class.getName());
private Logger jlogger=Logger.getLogger(MainActivity.class.getName());

	public void testLog(){  
		logger.error("baperror demo in otherClass Class");
		//jlogger.error("jlogger otherclass");
	}
    
}