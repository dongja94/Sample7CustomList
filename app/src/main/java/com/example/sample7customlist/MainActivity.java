package com.example.sample7customlist;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sample7customlist.MyAdapter.OnAdapterListener;

public class MainActivity extends ActionBarActivity {

	ListView listView;
	MyAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		listView = (ListView) findViewById(R.id.listView1);
		listView.setEmptyView(findViewById(R.id.text_empty));
		TextView headerView = new TextView(this);
		headerView.setText("this is header view");
		listView.addHeaderView(headerView, "headerview", true);
		mAdapter = new MyAdapter(new ItemView.OnButtonClickListener() {

			@Override
			public void onButtonClick(View view, ItemData data) {
				Toast.makeText(MainActivity.this, data.title + " clicked",
						Toast.LENGTH_SHORT).show();
			}
		});
		mAdapter.setOnAdapterListener(new OnAdapterListener() {

			@Override
			public void onAdapterAction(Adapter adapter, View view,
					ItemData data) {
				Toast.makeText(MainActivity.this,
						"adapter listener : " + data.title, Toast.LENGTH_SHORT)
						.show();
			}
		});
		listView.setAdapter(mAdapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Object item = listView.getItemAtPosition(position);
				if (item instanceof String) {
					String header = (String) item;
					Toast.makeText(MainActivity.this, "header : " + header,
							Toast.LENGTH_SHORT).show();
				} else if (item instanceof ItemData) {
					ItemData data = (ItemData) listView
							.getItemAtPosition(position);
					Toast.makeText(MainActivity.this,
							"item clicked : " + data.title, Toast.LENGTH_SHORT)
							.show();
				}
			}
		});
		
		Button btn = (Button)findViewById(R.id.btn_clear);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mAdapter.clear();
			}
		});
		
		btn = (Button)findViewById(R.id.btn_add);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				initData();
			}
		});

		initData();
	}

	private void initData() {
		for (int i = 0; i < 20; i++) {
			ItemData d = new ItemData();
			d.iconId = R.drawable.ic_launcher;
			d.title = "title " + i;
			d.desc = "desc : " + i;
			d.like = i;
			mAdapter.add(d);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
