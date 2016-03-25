package com.baplib.ui.fragment;

import de.greenrobot.event.EventBus;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class BapFragment extends Fragment {

	@Override
	public void onStart() {
		EventBus.getDefault().register(this);//注册事件总线
	}

	@Override
	public void onStop() {
		EventBus.getDefault().unregister(this);//注销事件总线
	}
}
