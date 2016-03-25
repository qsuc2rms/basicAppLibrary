package com.example.uimodule.expandhori;

import com.example.basicapplibdemo.R;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.baplib.util.file.FileChooser;

public class HoriExpandActivity extends FragmentActivity {


	@Override
	public void onCreate(Bundle saveInstanceState) {
		super.onCreate(saveInstanceState);
		setContentView(R.layout.activity_ninth);
		InitLeanLayout() ;
	}

	private void InitLeanLayout() {
		FragmentManager fm = getSupportFragmentManager();
		try {

			Fragment_lean f1= new Fragment_lean(0);
			fm.beginTransaction().replace(R.id.fragment1, f1, Fragment_lean.class.getName())
					.commitAllowingStateLoss();
			Fragment_lean f2= new Fragment_lean(1);
			fm.beginTransaction().replace(R.id.fragment2, f2, Fragment_lean.class.getName())
					.commitAllowingStateLoss();
			Fragment_lean f3= new Fragment_lean(2);
			fm.beginTransaction().replace(R.id.fragment3, f3, Fragment_lean.class.getName())
					.commitAllowingStateLoss();
			Fragment_lean f4= new Fragment_lean(3);
			fm.beginTransaction().replace(R.id.fragment4, f4, Fragment_lean.class.getName())
					.commitAllowingStateLoss();

		} catch (Exception e) {
			Log.e("baplibdemo", "InitLeanLayout err:" + e.getMessage());

		}
	}
	
}
