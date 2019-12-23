package com.lib.storage;

import java.util.HashMap;
import java.util.Map;

import com.lib.storage.type.LocalStorage;
import com.lib.storage.type.SQLiteStorage;
import com.lib.storage.util.PropertiesUtil;

 class StorageFactory {

	public final String Local = "local";
	public final String SQLite = "SQLlite";
	public static Map<String, Class> supportedClasses = new HashMap<String, Class>();
	private PropertiesUtil properties;
	private String storageType;

	static {
		supportedClasses.put(LocalStorage.class.getSimpleName(), LocalStorage.class);
		supportedClasses.put(SQLiteStorage.class.getSimpleName(), SQLiteStorage.class);

	}

	public StorageFactory(String propertFile) {
		properties = new PropertiesUtil(propertFile);

		this.storageType = properties.getProperty("storage.type");

	}

	public <T extends Storage> Storage getStorage() throws Exception {
		Storage store = null;
		if (storageType == null && storageType.length() > 0 && storageType != "") {
			return null;
		}

		Class<T> clazz = supportedClasses.get(storageType);

		if (clazz != null && clazz.getName() != null && clazz.getSimpleName() != ""
				&& clazz.getSimpleName().equals(storageType)) {
			try {
				store = clazz.newInstance();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			throw new Exception("Invalid property value storage.type:" + storageType);
		}
		store.loadAndInstantiate(properties);
		return store;
	}

}
