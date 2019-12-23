#StorageLibrary
What is the storage library?
The storage library helps the user to manage storing of files by choosing from different storage technologies (like S3, filesystem, Azure Blob Storage, Database like MongoDB, SQLite, etc.) and switching between them with minimum changes. 
The library is extensible i.e. a user can introduce new storage engine (like a different database or completely different cloud service) by using a simple syntax.
The library supports common storage-related operations like - saving the document, open document, get document attributes (like creation time, last update time, size), rename the document, delete the document;

Currently Supported Storage Engines 
Local FileSystem Storage Engine 
SQLITE Storage Engine

Note:The library will over write the file if another file with same name is to be saved.
How to Use
1.Configuring the property file
```properties
storage.type=LocalStorage
storage.localDirectory.basePath=dataFolder
```
| Property key  |Possible Values|	Requried |
| ------------- | ------------- |----------|
| storage.type  |LocalStorage,SQLiteStorage,{customClass simple name}  |YES|
| storage.localDirectory.basePath  |{ valid directory path}  | 	if storage.type =LocalStorage|
|storage.sqllite.dbpath|{ valid path to the sqlite db}|if storage.type = SQLiteStorage|
|{custom.property}|	According to requirements|	If storage.type = {custom}|
		
	
		
	

 
2. Setting custom configuration file 
In order for the lib to access the configuration file you must initialize it with the path of config file using the following java syntax

```java
import com.lib.storage.StorageManager;

public class Test {
    public static void main(String[] args) throws Exception {
       StorageManager.setConfigurationFilePath(“propertyFilePath/file.properties”);
    }
}
```

The default value of property file is “storage.properties” in the root of project folder structure

3.Performing Storage Operations - open,save,delete,rename

```java
import com.lib.storage.Storage;
import com.lib.storage.StorageManager;

  public class Test {
      public static void main(String[] args) throws Exception {        
          Storage storage = StorageManager.getStorageInstance();
          storage.openDocument(file)
          storage.saveDocument(data, fileName);
          storage.deleteDocument(fileName);
          storage.renameDocument(name, replacementName);
      }
  }
```
4.Extending the library
In order to extend the library through a custom implementation you must register your class in the following syntax and it must extend “Storage” Interface
```java
import com.lib.storage.Storage;
import com.lib.storage.StorageManager;
import com.custom.CustomStorage;

public class test {
    public static void main(String[] args) throws Exception {
        
       StorageManager.register(CustomStorage.class);
       StorageManager.setConfigurationFilePath(“propertyFilePath/file.properties”);
       Storage storage = StorageManager.getStorageInstance();
        
    }

}
```
The “storage.type” property will have the value as the simple class name being registered e.g. CustomStorage in this case
