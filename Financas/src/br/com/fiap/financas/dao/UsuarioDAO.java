package br.com.fiap.financas.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import br.com.fiap.financas.vo.Usuario;

public class UsuarioDAO extends DataSource {

	private SQLiteStatement insertStmt;
	private static final String INSERT = "insert into " + TABLE_USUARIO
			+ "(nome, usuario, senha, email, telefone, flag_ativo) values (?, ?, ?, ?, ?, ?)";

	private SQLiteStatement updateStmt;
	private static final String UPDATE = "update from "
			+ TABLE_USUARIO
			+ " set nome = ?, usuario = ?, senha = ?, email = ?, telefone = ?, flag_ativo = ? where id_usuario = ?";

	private SQLiteStatement deleteStmt;
	private static final String DELETE = "delete from " + TABLE_USUARIO
			+ " where id_usuario = ?";

	private SQLiteStatement selectLoginStmt;
	private static final String SELECT_LOGIN = "select id_usuario, nome, usuario, senha, email, telefone, flag_ativo from "
			+ TABLE_USUARIO + " where usuario = ? and senha = ?";
	
	private SQLiteStatement selectLoginByUsuarioStmt;
	private static final String SELECT_LOGIN_BY_USUARIO = "select id_usuario, nome, usuario, senha, email, telefone, flag_ativo from "
			+ TABLE_USUARIO + " where usuario = ?";

	public UsuarioDAO(Context context) {
		super(context);
		this.insertStmt = this.db.compileStatement(INSERT);
		//this.updateStmt = this.db.compileStatement(UPDATE);
		this.deleteStmt = this.db.compileStatement(DELETE);
		this.selectLoginStmt = this.db.compileStatement(SELECT_LOGIN);
		this.selectLoginByUsuarioStmt = this.db.compileStatement(SELECT_LOGIN_BY_USUARIO);
	}

	public long insert(Usuario usuario) {
		this.insertStmt.bindString(1, usuario.getNome());
		this.insertStmt.bindString(2, usuario.getUsuario());
		this.insertStmt.bindString(3, usuario.getSenha());
		this.insertStmt.bindString(4, usuario.getEmail());
		this.insertStmt.bindString(5, usuario.getTelefone());
		// TODO: Implementar Enum para SIM/NÃO
		this.insertStmt.bindString(6, usuario.isFlagAtivo() ? "S" : "N");
		return this.insertStmt.executeInsert();
	}

	public Usuario selectLogin(Usuario usuario) {
		Usuario resultado = null;

		Cursor c = db.rawQuery(SELECT_LOGIN,
				new String[] { usuario.getUsuario(), usuario.getSenha() });
		if (c.moveToFirst()) {
			resultado = new Usuario(c.getInt(c.getColumnIndex("id_usuario")), 
									c.getString(c.getColumnIndex("nome")), 
									c.getString(c.getColumnIndex("usuario")), 
									c.getString(c.getColumnIndex("senha")), 
									c.getString(c.getColumnIndex("email")), 
									c.getString(c.getColumnIndex("telefone")),
									c.getString(c.getColumnIndex("flag_ativo")).equalsIgnoreCase("S") ? true : false);
		}
		c.close();

		return resultado;
	}

	public Usuario selectLoginByUsuario(Usuario usuario) {
		Usuario resultado = null;

		Cursor c = db.rawQuery(SELECT_LOGIN_BY_USUARIO,
				new String[] {usuario.getUsuario()});
		if (c.moveToFirst()){
			resultado = new Usuario(c.getInt(c.getColumnIndex("id_usuario")), 
					c.getString(c.getColumnIndex("nome")), 
					c.getString(c.getColumnIndex("usuario")), 
					c.getString(c.getColumnIndex("senha")), 
					c.getString(c.getColumnIndex("email")), 
					c.getString(c.getColumnIndex("telefone")), 
					c.getString(c.getColumnIndex("flag_ativo")).equalsIgnoreCase("S") ? true : false);
		}
		c.close();

		return resultado;
	}
	
	public boolean isOpenDb(){
		return db.isOpen();
	}
}
