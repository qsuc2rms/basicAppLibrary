package com.baplib.qrcodescan;

import android.os.Handler;

public interface IQRScanner {
	public void handleDecode(String result);
	public Handler getHandler();
	public int getX();
	public int getY();
	public int getCropWidth();
	public int getCropHeight();
	public boolean isNeedCapture();
}
