package com.example.commonmodule.gson;

import java.util.ArrayList;
import java.util.List;

import com.baplib.util.json.BapJsonParser;
import com.example.basicapplibdemo.R;
import com.example.communicatemodule.eventbus.FirstEvent;
import com.example.communicatemodule.eventbus.SecondEvent;
import com.example.communicatemodule.eventbus.ThirdEvent;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import de.greenrobot.event.EventBus;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ShowGSONActivity extends Activity {
	private TextView txt_simple, txt_complext, txt_city, txt_citylist;
	private EditText edit_object, edit_list;
	Gson gson = BapJsonParser.getGson();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_showgson);
		Button btn_simple = (Button) findViewById(R.id.btn_simplejson);
		Button btn_complex = (Button) findViewById(R.id.btn_complexjson);
		Button btn_gsonobject = (Button) findViewById(R.id.btn_gsonobject);
		Button btn_gsonlist = (Button) findViewById(R.id.btn_gsonlist);
		Button btn_parseobject = (Button) findViewById(R.id.btn_parsegsonobject);
		Button btn_parselist = (Button) findViewById(R.id.btn_parsegsonobjectlist);

		txt_simple = (TextView) findViewById(R.id.txt_simplejson);
		txt_complext = (TextView) findViewById(R.id.txt_complexjson);
		txt_city = (TextView) findViewById(R.id.txt_parsecity);
		txt_citylist = (TextView) findViewById(R.id.txt_parsecitylist);
		edit_object = (EditText) findViewById(R.id.edit_complexjson);
		edit_list = (EditText) findViewById(R.id.edit_complexjsonlist);

		btn_simple.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				TestGson obj = new TestGson("aaaa", "bbbbb");
				txt_simple.setText(gson.toJson(obj));
			}
		});

		btn_complex.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				txt_complext.setText(getComplexJsonString());
			}
		});

		btn_gsonobject.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				edit_object.setText(getComplexJsonString());
				JM003ActivityItem msg = gson.fromJson(getComplexJsonString(), JM003ActivityItem.class);
				String city = msg.getMainMsg().getMsgContent().getCITY();
				txt_city.setText(city);
			}
		});
		
		btn_parseobject.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				JM003ActivityItem msg = gson.fromJson(edit_object.getText().toString(), JM003ActivityItem.class);
				String city = msg.getMainMsg().getMsgContent().getCITY();
				txt_city.setText(city);
			}
		});

		btn_gsonlist.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				edit_list.setText(getComplexJsonStringFromJavaObjectList());
				String citys = "";
				TestMsgActivityItemList msg = gson.fromJson(getComplexJsonStringFromJavaObjectList(),
						TestMsgActivityItemList.class);
				for (ActivityItem item : msg.getMainMsg().getMsgContent()) {
					citys += item.getCITY() + ";";
				}
				txt_citylist.setText(citys);
			}
		});
		
		btn_parselist.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String citys = "";
				TestMsgActivityItemList msg = gson.fromJson(edit_list.getText().toString(),
						TestMsgActivityItemList.class);
				for (ActivityItem item : msg.getMainMsg().getMsgContent()) {
					citys += item.getCITY() + ";";
				}
				txt_citylist.setText(citys);
			}
		});
	}

	private String getComplexJsonString() {
		ActivityItem item = new ActivityItem();
		item.setACTIVITETIME(System.currentTimeMillis());
		item.setCITY("北京");
		item.setCONTENT("test content");
		item.setID("randomid");
		item.setTITLE("test title");

		JM003ActivityItem msg = new JM003ActivityItem();
		JM003ActivityItem.MainMsg main = msg.getMainMsg();
		main.setMsgContent(item);
		main.setMsgSendTime("2015-07-31 11:09:18");

		String res = gson.toJson(msg);
		Log.e("TAG", res);
		return res;
	}

	private String getComplexJsonStringFromJavaObjectList() {
		ActivityItem item = new ActivityItem();
		item.setACTIVITETIME(System.currentTimeMillis());
		item.setCITY("北京");
		item.setCONTENT("test content");
		item.setID("randomid");
		item.setTITLE("test title");

		ActivityItem item2 = new ActivityItem();
		item2.setACTIVITETIME(System.currentTimeMillis());
		item2.setCITY("上海");
		item2.setCONTENT("test content 2");
		item2.setID("randomid2");
		item2.setTITLE("test title 2");

		List<ActivityItem> lst = new ArrayList<ActivityItem>();

		lst.add(item);
		lst.add(item2);

		TestMsgActivityItemList msg = new TestMsgActivityItemList();
		TestMsgActivityItemList.MainMsg main = msg.getMainMsg();
		main.setMsgContent(lst);
		main.setMsgSendTime("2015-07-31 11:09:18");

		String res = gson.toJson(msg);

		return res;
	}
}
