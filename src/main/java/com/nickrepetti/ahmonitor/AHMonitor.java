package com.nickrepetti.ahmonitor;

import com.nickrepetti.ahmonitor.util.Monitor;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ScheduledExecutorService;

public class AHMonitor {

	public static void main(String args[]) {
		Properties properties = new Properties();
		InputStream input = null;

		try {
			String configFile = args[0];
			input = new FileInputStream(configFile);
			properties.load(input);

			String endpoint = properties.getProperty("endpoint");
			String timeUnitProp = properties.getProperty("timeUnit").toUpperCase();

			TimeUnit timeUnit = TimeUnit.HOURS;

			if ("S".equals(timeUnitProp)) {
				timeUnit = TimeUnit.SECONDS;
			} else if ("M".equals(timeUnitProp)) {
				timeUnit = TimeUnit.MINUTES;
			}

			long period = Long.parseLong(properties.getProperty("period"));

			String dbUrl = properties.getProperty("dbUrl");
			String dbUserName = properties.getProperty("dbUserName");
			String dbPassword = properties.getProperty("dbPassword");

			ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
			executor.scheduleAtFixedRate(new Monitor(endpoint, dbUrl, dbUserName, dbPassword), 0, period, TimeUnit.MINUTES);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
