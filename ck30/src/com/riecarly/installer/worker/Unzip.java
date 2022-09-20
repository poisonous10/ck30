package com.riecarly.installer.worker;

import java.io.File;
import java.util.List;
import javax.swing.filechooser.FileNameExtensionFilter;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;

public class Unzip {
	@SuppressWarnings("resource")
	public static void unzip(String zipDir, String zipExtract, String password) {

		final FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("N/A", "zip");
		// Folder where zip file is present
		final File file = new File(zipDir);
		for (final File child : file.listFiles()) {
			try {
				net.lingala.zip4j.ZipFile zipFile;
				zipFile = new net.lingala.zip4j.ZipFile(child);
				if (extensionFilter.accept(child)) {
					if (zipFile.isEncrypted()) {
						// Your ZIP password
						zipFile.setPassword(password.toCharArray());
					}
					List<FileHeader> fileHeaderList = zipFile.getFileHeaders();

					for (int i = 0; i < fileHeaderList.size(); i++) {
						FileHeader fileHeader = (FileHeader) fileHeaderList.get(i);
						// Path where you want to Extract
						zipFile.extractFile(fileHeader, zipExtract);
						System.out.println("Extracted");
					}
				}
			} catch (ZipException e) {
				// empty block
			}
		}

	}

	public static void extract(String z, String e, String p) {
		unzip(z, e, p);
	}
}