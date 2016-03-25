package com.example.commonmodule.update;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.baplib.update.BapUpdateConfig;
import com.baplib.update.UpdateManager;
import com.example.basicapplibdemo.R;

public class UpdateActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update);

		Button btn = (Button) findViewById(R.id.btn_mockUpdate);

		btn.setOnClickListener(new View.OnClickListener() {// 原始用法

					@Override
					public void onClick(View v) {
						//在实际使用中，可以通过查询服务器或接收服务器推送等方式，获取最新版本信息
						//此处模拟经过HTTP请求，已获知最新版本信息
						//默认版本为1，不更改的话只会提示”已经是最新版本“
						BapUpdateConfig.getInstance().setLatestVersion("100");//注意，此处对应manifest文件中的versionCode，不是versionName
						BapUpdateConfig.getInstance().setApkName("com.lenovo.ops.mconfig");//用嗨动App做测试
						BapUpdateConfig.getInstance().setURL(" http://43.255.224.137:8181/MBGSTServer/ServletDownload?filename=MConfig-v1.1.apk");//("http://www.lenovomm.com/appdown/17253606-2");//下载地址
						BapUpdateConfig.getInstance().setSavePath("BapDemo");//手机本地保存路径
						UpdateManager manager = new UpdateManager(
								UpdateActivity.this);
						// 检查软件更新
						manager.checkUpdate();
					}
				});

	}
}
