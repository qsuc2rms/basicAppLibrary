package com.baplib.util.file;

import com.ipaulpro.afilechooser.utils.FileUtils;
import com.ops.basicapplib.R;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class FileChooser {

	public static final int FILECHOOSER_CODE = 6384;

	public static Intent getChooserIntent(String title) {
		Intent target = FileUtils.createGetContentIntent();
		return Intent.createChooser(target, title);
	}

	public static String getFilePath(int requestCode, int resultCode, Intent data, Context context) {
		switch (requestCode) {
		case FILECHOOSER_CODE:
			// If the file selection was successful
			if (resultCode == android.app.Activity.RESULT_OK) {
				if (data != null) {
					// Get the URI of the selected file
					final Uri uri = data.getData();
					Log.i("baplib", "Uri = " + uri.toString());
					try {
						// Get the file path from the URI
						final String path = FileUtils.getPath(context, uri);
						return path;
					} catch (Exception e) {
						Log.e("FileSelectorTestActivity", "File select error", e);
					}
				}
			}
			break;
		}
		return "";
	}

}
