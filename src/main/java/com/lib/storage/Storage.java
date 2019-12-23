package com.lib.storage;

public abstract class Storage extends CustomInitializer {
	
	public Storage()
	{
		
	}
	
	protected abstract void instantiate();
	/** 
	* Saves the file to the storage
	* @param data The file data as a string
    * @param fileName The name the file is to be saved as
    */
	public abstract void saveDocument(String data,String fileName);
	
	/** 
	* Reads the file in the storage and returns it as a String
	* @param data The file data as a string
    * @param fileName The name the file is to be saved as
    */
	public abstract String openDocument(String file);
//	public FilegetDocumentAttributes
	
	/** 
	* Renames the file in the storage
	* @param name The existing name of the file
    * @param replacementName The name to be replaced with
    */
	public abstract void renameDocument(String name,String replacementName);
	
	/** 
	* Deletes the file from the storage
    * @param fileName The name of the file to be deleted
    */
	public abstract void deleteDocument(String fileName);

	
	

}
