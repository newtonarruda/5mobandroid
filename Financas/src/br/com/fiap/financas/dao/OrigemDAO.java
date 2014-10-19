package br.com.fiap.financas.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import br.com.fiap.financas.vo.Origem;

public class OrigemDAO extends DataSource {

	private SQLiteStatement insertStmt;
	private static final String INSERT = "insert into " + TABLE_ORIGEM
			+ "(descricao) values (?)";

	private SQLiteStatement updateStmt;
	private static final String UPDATE = "update from " + TABLE_ORIGEM
			+ " set descricao = ? where id_origem = ?";

	private SQLiteStatement deleteStmt;
	private static final String DELETE = "delete from " + TABLE_ORIGEM
			+ " where id_origem = ?";

	// private SQLiteStatement selectLoginStmt;
	private static final String SELECT_ALL = "select id_origem, descricao from "
			+ TABLE_ORIGEM;

	public OrigemDAO(Context context) {
		super(context);
		this.db = getWritableDatabase();
		this.insertStmt = this.db.compileStatement(INSERT);
	}

	public long insert(Origem origem) {
		this.insertStmt.bindString(1, origem.getDescricao());
		return this.insertStmt.executeInsert();
	}

	// Diferente do Select do Login
	public List<Origem> selectAll(Origem origem) {
		List<Origem> resultado = new ArrayList<Origem>();
		Cursor cursor = this.db.query(TABLE_CATEGORIA,
				new String[] { "descricao" }, null, null, null, null,
				"descicao desc");
		if (cursor.moveToFirst()) {
			do {
				resultado.add(new Origem(cursor.getInt(cursor
						.getColumnIndex("id_origem")), cursor
						.getString(cursor.getColumnIndex("descricao"))));
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return resultado;
	}
}
