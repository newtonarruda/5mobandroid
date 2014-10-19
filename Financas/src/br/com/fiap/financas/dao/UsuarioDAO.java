package br.com.fiap.financas.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import br.com.fiap.financas.vo.Usuario;

public class UsuarioDAO extends DataSource {

	private SQLiteStatement insertStmt;
	private static final String INSERT = "insert into " + TABLE_USUARIO
			+ "(nome, usuario, senha, flag_ativo) values (?, ?, ?, ?)";

	private SQLiteStatement updateStmt;
	private static final String UPDATE = "update from "
			+ TABLE_USUARIO
			+ " set nome = ?, usuario = ?, senha = ?, flag_ativo = ? where id = ?";

	private SQLiteStatement deleteStmt;
	private static final String DELETE = "delete from " + TABLE_USUARIO
			+ " where id = ?";

	// private SQLiteStatement selectLoginStmt;
	private static final String SELECT_LOGIN = "select id, nome, usuario, senha, flag_ativo from "
			+ TABLE_USUARIO + " where usuario = ? and senha = ?";

	public UsuarioDAO(Context context) {
		super(context);
		this.db = getWritableDatabase();
		this.insertStmt = this.db.compileStatement(INSERT);
		// this.selectLoginStmt = this.db.compileStatement( SELECT_LOGIN ) ;
	}

	public long insert(Usuario usuario) {
		this.insertStmt.bindString(1, usuario.getNome());
		this.insertStmt.bindString(2, usuario.getUsuario());
		this.insertStmt.bindString(3, usuario.getSenha());
		// TODO: Implementar Enum para SIM/NÃO
		this.insertStmt.bindString(4, usuario.isFlagAtivo() ? "S" : "N");
		return this.insertStmt.executeInsert();
	}

	public Usuario selectLogin(Usuario usuario) {
		Usuario resultado = null;

		Cursor c = db.rawQuery(SELECT_LOGIN,
				new String[] { usuario.getUsuario(), usuario.getSenha() });
		if (c.moveToFirst()) {
			resultado = new Usuario(c.getInt(c.getColumnIndex("id")), 
									c.getString(c.getColumnIndex("nome")), 
									c.getString(c.getColumnIndex("usuario")), 
									c.getString(c.getColumnIndex("senha")), 
									c.getString(c.getColumnIndex("flag_ativo")).equalsIgnoreCase("S") ? true : false);
		}
		c.close();

		return resultado;
	}
}
