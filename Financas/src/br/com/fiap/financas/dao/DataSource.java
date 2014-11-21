package br.com.fiap.financas.dao;

import br.com.fiap.financas.vo.MensagemBean;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataSource extends SQLiteOpenHelper {

	protected static final String DATABASE_FINANCAS = "financas.db";
	protected static final String TABLE_USUARIO = "tb_usuario";
	protected static final String TABLE_MOVIMENTACAO = "tb_movimentacao";
	protected static final String TABLE_CATEGORIA = "tb_categoria";
	protected static final String TABLE_ORIGEM = "tb_origem";
	private static final int DATABASE_VERSION = 2;

	Context context;
	SQLiteDatabase db;

	public DataSource(Context context) {

		super(context, DATABASE_FINANCAS, null, DATABASE_VERSION);
		this.context  = context ;
		this.db = getWritableDatabase();
		//onUpgrade(db, 2, DATABASE_VERSION);
		onUpgrade(db, 2, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL("CREATE TABLE "
				+ TABLE_USUARIO
				+ " (id_usuario INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, usuario TEXT, senha TEXT, email TEXT, telefone TEXT, flag_ativo VARCHAR)");

		db.execSQL("CREATE TABLE "
				+ TABLE_MOVIMENTACAO
				+ " (id_movimentacao INTEGER PRIMARY KEY AUTOINCREMENT, titulo TEXT, tipo_movimentacao INTEGER, data TEXT, valor_total REAL, valor_parcial REAL, flag_efetivada VARCHAR, lat_long TEXT, id_origem INTEGER, id_usuario INTEGER)");

		db.execSQL("CREATE TABLE "
				+ TABLE_CATEGORIA
				+ " (id_categoria INTEGER PRIMARY KEY AUTOINCREMENT, descricao TEXT)");

		db.execSQL("CREATE TABLE "
				+ TABLE_ORIGEM
				+ " (id_origem INTEGER PRIMARY KEY AUTOINCREMENT, descricao TEXT)");

	}

	public void insertSmsIntoDatabase(MensagemBean message)
	{
		
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w("Finanças",
				"Upgrading database, this will drop tables and recreate.");
		if(newVersion > oldVersion){
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIMENTACAO);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIO);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIA);
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORIGEM);
			onCreate(db);
		}
	}

	public void close(){
		this.db.close();
	}
}
