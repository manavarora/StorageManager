package com.lib.storage.type;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import com.lib.storage.Storage;
import com.lib.storage.util.PropertiesUtil;

public class LocalStorage extends Storage {

	private String basePath = "";

	public LocalStorage() {
		
	}
	@Override
	public void loadProperties(PropertiesUtil properties) {

		String basePath = properties.getProperty("storage.localDirectory.basePath");
			

	}

	@Override
	protected void instantiate() {
		if (basePath != null && basePath != "" && basePath.length() > 0) {
			
			new File(basePath).mkdirs();
			
			if (!basePath.endsWith("/")) {

				this.basePath += "/";
			}
		}
		
	}
	public void saveDocument(String data, String fileName) {

		try (OutputStream os = new FileOutputStream(new File(basePath + fileName))) {
			os.write(data.getBytes(), 0, data.length());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String openDocument(String fileName) {
		String st = "";

		File file = new File(basePath + fileName);
		if (file != null && file.exists()) {
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {

				while ((st = br.readLine()) != null)
					System.out.println(st);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return st;

		}
		return null;

	}

	public void renameDocument(String fileName, String replacementName) {

		File file = new File(basePath + fileName);
		if (file != null && file.exists()) {
			{
				file.renameTo(new File(basePath + replacementName));
			}
		}
		System.out.println("renamed doc");

	}

	public void deleteDocument(String fileName) {
		File file = new File(basePath + fileName);
		if (file != null && file.exists()) {
			{
				file.delete();
			}
		}
		System.out.println("deleted doc");

	}

}
