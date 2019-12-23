package com.lib.storage;

import com.lib.storage.util.PropertiesUtil;

abstract class CustomInitializer {

	protected void loadAndInstantiate(PropertiesUtil properties) {
		loadProperties(properties);
		instantiate();
	}
	
	protected abstract void instantiate();
	protected abstract void loadProperties(PropertiesUtil properties);

}
