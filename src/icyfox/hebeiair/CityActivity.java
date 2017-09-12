package icyfox.hebeiair;

import java.util.List;

import com.example.hebeiair.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class CityActivity extends Activity {

	private TextView tv_time;
	private GridView gv;
	private CityData cityData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_city);
		
		tv_time = (TextView)findViewById(R.id.tv_time);
		gv = (GridView)findViewById(R.id.gv);
		
		Intent it = getIntent();
		cityData = (CityData)it.getSerializableExtra("node");
		
		tv_time.setText(cityData.name+"   "+cityData.aqi+"\n���ʱ��:"+cityData.dataTime+"\n"+cityData.level+"\n��Ҫ��Ⱦ��:"+cityData.maxPoll);
		
		gv.setAdapter(new gvAdapter(cityData.pointerList));
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
				Pointer pointer = cityData.pointerList.get(position);
				String info = "����:"+pointer.city+" "+pointer.region+"\n��λ����:"+pointer.name
						+"\n����ʱ��:"+pointer.dataTime+"\nAQI:"+pointer.aqi+"\n��Ⱦ�ȼ�:"+pointer.level
						+"\n��Ҫ��Ⱦ��:"+pointer.maxPoll+"\n��������״��:"+pointer.intro+"\n���鼰��ʩ:"
						+pointer.tips;
				new AlertDialog.Builder(CityActivity.this)
					.setTitle("��ϸ��Ϣ")
					.setMessage(info)
					.show();
			}
		});
	}
	
	class gvAdapter extends BaseAdapter{

		List<Pointer> pList;
		
		public gvAdapter(List<Pointer> cdList) {
			super();
			this.pList = cdList;
		}

		@Override
		public int getCount() {
			return pList.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = CityActivity.this.getLayoutInflater().inflate(R.layout.gv2, null);
			TextView tv_city = (TextView)convertView.findViewById(R.id.tv_city);
			TextView tv_aqi = (TextView)convertView.findViewById(R.id.tv_aqi);
			View bg = convertView.findViewById(R.id.bg);
			tv_city.setText(pList.get(position).name);
			tv_aqi.setText(pList.get(position).aqi);
			bg.setBackgroundColor(Color.parseColor(pList.get(position).color));
			return convertView;
		}
		
	}

}
