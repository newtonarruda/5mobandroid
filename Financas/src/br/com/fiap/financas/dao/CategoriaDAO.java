package br.com.fiap.financas.dao;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import br.com.fiap.financas.vo.Categoria;

public class CategoriaDAO extends DataSource {

	private SQLiteStatement insertStmt;
	private static final String INSERT = "insert into " + TABLE_CATEGORIA
			+ "(descricao) values (?)";

	private SQLiteStatement updateStmt;
	private static final String UPDATE = "update from " + TABLE_CATEGORIA
			+ " set descricao = ? where id_categoria = ?";

	private SQLiteStatement deleteStmt;
	private static final String DELETE = "delete from " + TABLE_CATEGORIA
			+ " where id_categoria = ?";

	// private SQLiteStatement selectLoginStmt;
	private static final String SELECT_ALL = "select id_categoria, descricao from "
			+ TABLE_CATEGORIA;

	public CategoriaDAO(Context context) {
		super(context);
		this.db = getWritableDatabase();
		this.insertStmt = this.db.compileStatement(INSERT);
	}

	public long insert(Categoria categoria) {
		this.insertStmt.bindString(1, categoria.getDescricao());
		return this.insertStmt.executeInsert();
	}

	// Diferente do Select do Login
	public List<Categoria> selectAll(Categoria categoria) {
		List<Categoria> resultado = new ArrayList<Categoria>();
		Cursor cursor = this.db.query(TABLE_CATEGORIA,
				new String[] { "descricao" }, null, null, null, null,
				"descicao desc");
		if (cursor.moveToFirst()) {
			do {
				resultado.add(new Categoria(cursor.getInt(cursor
						.getColumnIndex("id_categoria")), cursor
						.getString(cursor.getColumnIndex("descricao"))));
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return resultado;
	}
}
