package br.com.fiap.financas.view;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;
import br.com.fiap.financas.R;
import br.com.fiap.financas.adapter.CalendarioAdapter;

public class CalendarioActivity extends Activity {
	public Calendar mes;
	public CalendarioAdapter adapter;
	public Handler handler;

	// Container para armazenar alguns valores randomicos de items do calendario
	// Deve=se trabalhar de outra forma!!!
	public ArrayList<String> items;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendario);
		
		mes = Calendar.getInstance();

		items = new ArrayList<String>();
		adapter = new CalendarioAdapter(this, mes);

		GridView gridview = (GridView) findViewById(R.id.gridview);
		gridview.setAdapter(adapter);

		TextView title = (TextView) findViewById(R.id.title);
		title.setText(android.text.format.DateFormat.format("MMMM yyyy", mes));

		TextView previous = (TextView) findViewById(R.id.previous);
		previous.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mes.get(Calendar.MONTH) == mes
						.getActualMinimum(Calendar.MONTH))
					mes.set((mes.get(Calendar.YEAR) - 1),
							mes.getActualMaximum(Calendar.MONTH), 1);
				else
					mes.set(Calendar.MONTH, mes.get(Calendar.MONTH) - 1);

				atualizaCalendario();
			}
		});

		TextView next = (TextView) findViewById(R.id.next);
		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (mes.get(Calendar.MONTH) == mes
						.getActualMaximum(Calendar.MONTH))
					mes.set((mes.get(Calendar.YEAR) + 1),
							mes.getActualMinimum(Calendar.MONTH), 1);
				else
					mes.set(Calendar.MONTH, mes.get(Calendar.MONTH) + 1);

				atualizaCalendario();
			}
		});

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				// TODO criar um dialog onde deve criar um novo gasto ou ganho
			}

		});
	}

	public void atualizaCalendario() {
		TextView title = (TextView) findViewById(R.id.title);

		adapter.atualizaDias();
		adapter.notifyDataSetChanged();
		// gera alguns items randomicos do calendar
		// handler.post(calendarUpdater);

		title.setText(android.text.format.DateFormat.format("MMMM yyyy", mes));
	}

	public void onNewIntent(Intent intent) {
		String data = intent.getStringExtra("date");
		String[] dateArr = data.split("-"); // date format is yyyy-mm-dd
		mes.set(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]),
				Integer.parseInt(dateArr[2]));
	}

	public Runnable calendarUpdater = new Runnable() {

		@Override
		public void run() {
			items.clear();
			// format random values. You can implement a dedicated class to
			// provide real values
			for (int i = 0; i < 31; i++) {
				Random r = new Random();

				if (r.nextInt(10) > 6)
					items.add(Integer.toString(i));
			}

			adapter.setItems(items);
			adapter.notifyDataSetChanged();
		}
	};
}
