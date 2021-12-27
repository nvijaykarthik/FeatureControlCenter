package org.nvijaykarthik.fccclient.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadConfigProperties {

	public static Properties loadClientConfigs() {
		Properties props = new Properties();

		try (InputStream input = LoadConfigProperties.class.getClassLoader().getResourceAsStream("config.properties")) {
			if (input == null) {
				System.out.println("Unable to find config.properties");
				return null;
			}
			props.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return props;
	}
}
