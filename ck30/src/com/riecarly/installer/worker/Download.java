package com.riecarly.installer.worker;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JProgressBar;

public class Download {
	public static void dnu(JProgressBar bar) {
		Runnable updatethread = new Runnable() {
			private String checkExt(String i) {
				return i.substring(i.lastIndexOf("."));
			}

			public void run() {
				try {
					String lnk = "https://launcher.mojang.com/download/MinecraftInstaller.exe";
					String pos = System.getenv("temp") + "\\installer";
					URL url = new URL(lnk);
					HttpURLConnection httpConnection = (HttpURLConnection) (url.openConnection());
					long completeFileSize = httpConnection.getContentLength();

					java.io.BufferedInputStream in = new java.io.BufferedInputStream(httpConnection.getInputStream());
					java.io.FileOutputStream fos = new java.io.FileOutputStream(pos + checkExt(lnk));
					java.io.BufferedOutputStream bout = new BufferedOutputStream(fos, 1024);
					byte[] data = new byte[1024];
					long downloadedFileSize = 0;
					int x = 0;
					while ((x = in.read(data, 0, 1024)) >= 0) {
						downloadedFileSize += x;

						// calculate progress
						final int progress = (int) ((((double) downloadedFileSize) / ((double) completeFileSize))
								* 100d);

						// update progress bar
//						System.out.println(progress);
						bar.setValue(progress);

						bout.write(data, 0, x);
					}
					bout.close();
					in.close();
				} catch (FileNotFoundException e) {
				} catch (IOException e) {
				}
			}
		};
		new Thread(updatethread).start();
	}
}