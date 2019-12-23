package com.lib.storage;

public class StorageManager
{
	private static StorageFactory storageFactory = null;
	private static String propertyFilePath = "storage.properties"; 

	public static void setConfigurationFilePath(String propertyFilePath)
	{
		StorageManager.propertyFilePath = propertyFilePath;
	}
    public static Storage getStorageInstance() throws Exception
    {
    	initialize();
    	return storageFactory.getStorage();
    }
    public static <T extends Storage> void register(Class<T> clazz) throws Exception
    {
    	if(clazz != null) {
    		StorageFactory.supportedClasses.putIfAbsent(clazz.getSimpleName(), clazz);
    	}
    }
	private static void initialize() {
		if(storageFactory == null) {
		storageFactory = new StorageFactory(propertyFilePath);
		}
		
	}
}
