package br.com.fiap.financas.adapter;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.fiap.financas.R;

public class CalendarioAdapter extends BaseAdapter 
{

	static final int PRIMEIRO_DIA_DA_SEMANA = 0;

	private Context mContext;
	
	private Calendar mes;
	private Calendar selectedDate;
	private ArrayList< String > items;
	
	// Refencia para os items 
    public String[] arrDias;
	
    public CalendarioAdapter( Context c, Calendar mesCalendario )
	{
		mes = mesCalendario;
		selectedDate = ( Calendar ) mesCalendario.clone();
		mContext = c;
		mes.set( Calendar.DAY_OF_MONTH, 1 );
		this.items = new ArrayList< String >();
		atualizaDias();
	}
    
    public void setItems( ArrayList< String > items )
	{
		for( int i = 0; i != items.size(); i++ )
		{
			if ( items.get( i ).length() == 1 )
				items.set( i, "0" + items.get( i ) );
		}
		this.items = items;
	}
    
    @Override
	public int getCount() {
		return arrDias.length;
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
		View v = convertView;
		TextView dayView;
		
		// se não for recycled, inicializa alguns atributos
		 if ( convertView == null ) 
		 {  
			 LayoutInflater vi = ( LayoutInflater ) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
			 v = vi.inflate(R.layout.activity_calendario_item, null);
		 }
		 dayView = ( TextView ) v.findViewById( R.id.date );
		 
		 // desabilita os "arrDias" para o início
		 if( arrDias[ position ].equals( "" ) ) 
		 {
			 dayView.setClickable( false );
			 dayView.setFocusable( false );
		 }
		 else 
		 {
			 // marca o dia corrente com foco
			 if( mes.get( Calendar.YEAR ) == selectedDate.get( Calendar.YEAR ) && 
					 mes.get( Calendar.MONTH ) == selectedDate.get( Calendar.MONTH ) && 
					 arrDias[ position ].equals( "" + selectedDate.get( Calendar.DAY_OF_MONTH ) ) )
				 v.setBackgroundResource(R.drawable.item_background_black);
			 else 
				 v.setBackgroundResource(R.drawable.list_item_background);
		 }
		 dayView.setText( arrDias[ position ] );
		 
		 // cria a string date para comparação
		 String date = arrDias[ position ];

		 if( date.length() == 1 ) 
			 date = "0" + date;
		 
		 String mesStr = "" + ( mes.get( Calendar.MONTH ) + 1 );
		 
		 if( mesStr.length() == 1 ) 
			 mesStr = "0" + mesStr;

		 // show icon if date is not empty and it exists in the items array
		 ImageView iw = (ImageView)v.findViewById(R.id.date_icon);
		 if(date.length()>0 && items!=null && items.contains(date)) {        	
			 iw.setVisibility(View.VISIBLE);
		 }
		 else {
			 iw.setVisibility(View.INVISIBLE);
		 }
		 
		return v;
	}

	public void atualizaDias()
	{
		// limpa os itens
		items.clear();
		
		int ultimoDia = mes.getActualMaximum( Calendar.DAY_OF_MONTH );
		int primeiroDia = ( int ) mes.get( Calendar.DAY_OF_WEEK );
		
		// define o tamanho do array
		if ( primeiroDia == 1 )
			arrDias = new String[ ultimoDia + ( PRIMEIRO_DIA_DA_SEMANA * 6 ) ];
		else
			arrDias = new String[ ultimoDia + primeiroDia - ( PRIMEIRO_DIA_DA_SEMANA + 1 ) ];
		
		int j = PRIMEIRO_DIA_DA_SEMANA;
		
		// popula o array vazio "arrDias (array de dias)" antes do primeiro dia real
		if ( primeiroDia > 1 )
		{
			for ( j = 0; j < primeiroDia - PRIMEIRO_DIA_DA_SEMANA; j++ )
				arrDias[ j ] = "";
		}
		else
		{
			for ( j = 0; j < PRIMEIRO_DIA_DA_SEMANA * 6; j++ )
				arrDias[ j ] = "";
			
			j = PRIMEIRO_DIA_DA_SEMANA * 6 + 1;
		}
		
		// "popula arrDias"
		int diaNumero = 1;
		for( int i = j - 1; i < arrDias.length; i++ )
		{
			arrDias[ i ] = "" + diaNumero;
			diaNumero++;
		}
	}
}
