package com.example.uimodule.expandhori;

import com.baplib.common.UIUtil;
import com.example.basicapplibdemo.R;
import com.example.basicapplibdemo.R.color;
import com.example.communicatemodule.eventbus.FirstEvent;

import de.greenrobot.event.EventBus;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class Fragment_lean extends Fragment implements OnClickListener {

	private int num = -1;

	public Fragment_lean(int i) {
		num = i;
	}

	LinearLayout layout_lean_background;

	@Override
	public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle bundle) {
		View root = inflator.inflate(R.layout.fragment_horiexp_lean, container, false);
		layout_lean_background = UIUtil.getView(root, R.id.layout_lean_background);
		layout_lean_background.setOnClickListener(this);

		return root;
	}

	@Override
	public void onStart() {
		EventBus.getDefault().register(this);// 事件总线--注册观察者
		super.onStart();
	}

	@Override
	public void onStop() {
		EventBus.getDefault().unregister(this);// 事件总线--注册观察者
		super.onStop();
	}

	@Override
	public void onResume() {
		super.onResume();
		setColor(num);
	}

	/*
	 * 当点击界面时，展开被点击的，收缩未被点击的
	 */
	public void onEventMainThread(HoriExpandEvent event) {

		WindowManager wm = (WindowManager) this.getActivity().getSystemService(this.getActivity().WINDOW_SERVICE);
		int width = wm.getDefaultDisplay().getWidth();

		if (event.getMsg() != num) {
			LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layout_lean_background
					.getLayoutParams();
			layoutParams.width = 60;
			layout_lean_background.setLayoutParams(layoutParams);
		} else {
			LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) layout_lean_background
					.getLayoutParams();
			layoutParams.width = width-60*3;

			layout_lean_background.setLayoutParams(layoutParams);
		}
	}

	public void setColor(int i) {
		switch (i) {
		case 0:
			layout_lean_background.setBackgroundColor(Color.GRAY);
			break;
		case 1:
			layout_lean_background.setBackgroundColor(Color.BLACK);
			break;
		case 2:
			layout_lean_background.setBackgroundColor(Color.YELLOW);
			break;
		default:
			layout_lean_background.setBackgroundColor(Color.BLUE);
			break;

		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.layout_lean_background:

			EventBus.getDefault().post(new HoriExpandEvent(num));
			break;

		default:
			break;
		}

	}

	public class HoriExpandEvent {

		private int _num;

		public HoriExpandEvent(int num) {
			// TODO Auto-generated constructor stub
			_num = num;
		}

		public int getMsg() {
			return _num;
		}
	}

}
