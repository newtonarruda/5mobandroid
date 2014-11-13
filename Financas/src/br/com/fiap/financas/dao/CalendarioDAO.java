package br.com.fiap.financas.dao;

import android.content.Context;
import android.database.sqlite.SQLiteStatement;

public class CalendarioDAO extends DataSource
{

	//TODO Verificar qual é a tabela correta para inserir um novo Ganho ou novo Gasto
	private SQLiteStatement insertStmt;
	private static final String INSERT = "insert into " 
			+ TABLE_MOVIMENTACAO
			+ "(titulo, tipo_movimentacao, data, valor_total, valor_parcial, lat_long, flag_efetivada, id_origem, id_usuario) "
			+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	
	
	
	public CalendarioDAO(Context context) {
		super(context);
		this.db = getWritableDatabase();
		this.insertStmt = this.db.compileStatement(INSERT);
	}

}
