package br.com.fiap.financas.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import br.com.fiap.financas.vo.Movimentacao;
import br.com.fiap.financas.vo.Origem;
import br.com.fiap.financas.vo.Usuario;

public class MovimentacaoDAO extends DataSource {

	private SQLiteStatement insertStmt;
	private static final String INSERT = "insert into "
			+ TABLE_MOVIMENTACAO
			+ "(titulo, tipo_movimentacao, data, valor_total, valor_parcial, lat_long, flag_efetivada, id_origem, id_usuario) "
			+ "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";

	private SQLiteStatement updateStmt;
	private static final String UPDATE = "update from "
			+ TABLE_MOVIMENTACAO
			+ " set titulo = ?, tipo_movimentacao = ?, data = ?, valor_total = ?, valor_parcial = ?, lat_long = ?, flag_efetivada = ? where id = ?";

	private SQLiteStatement deleteStmt;
	private static final String DELETE = "delete from " + TABLE_MOVIMENTACAO
			+ " where id = ?";

	// private SQLiteStatement selectLoginStmt;
	private static final String SELECT_ALL = "select id, titulo, tipo_movimentacao, data, valor_total, valor_parcial, lat_long, flag_efetivada, id_origem, id_usuario from "
			+ TABLE_MOVIMENTACAO;

	// Select SUM com filtro no tipo_movimentacao 
	private SQLiteStatement selectSumGastosOuGanhosStmt;
	private static final String SELECT_ALL_SUM = "select SUM(valor_total) from " 
			+ TABLE_MOVIMENTACAO 
			+ " where id_usuario = ? and tipo_movimentacao = ? and flag_efetivada = ?";
	
	public MovimentacaoDAO(Context context) {
		super(context);
		this.db = getWritableDatabase();
		this.insertStmt = this.db.compileStatement(INSERT);
		// this.selectLoginStmt = this.db.compileStatement(SELECT_LOGIN);
		this.selectSumGastosOuGanhosStmt = this.db.compileStatement(SELECT_ALL_SUM);
	}

	public long insert(Movimentacao movimentacao) {
		this.insertStmt.bindString(1, movimentacao.getTitulo());
		this.insertStmt.bindLong(2, movimentacao.getTipoMovimentacao());
		// this.insertStmt.bindLong(3, movimentacao.getData);
		this.insertStmt.bindDouble(4, movimentacao.getValorTotal()
				.doubleValue());
		this.insertStmt.bindDouble(5, movimentacao.getValorParcial()
				.doubleValue());
		this.insertStmt.bindString(6, movimentacao.getLatLong());
		this.insertStmt.bindString(7, movimentacao.isFlag_efetivada() ? "S"
				: "N");
		this.insertStmt.bindLong(8, movimentacao.getOrigem().getIdOrigem());
		this.insertStmt.bindLong(9, movimentacao.getUsuario().getIdUsuario());
		return this.insertStmt.executeInsert();
	}

	public List<Movimentacao> selectAll(Usuario usuario) {
		List<Movimentacao> resultado = new ArrayList<Movimentacao>();
		Cursor cursor = this.db.query(TABLE_CATEGORIA, new String[] {
				"id_movimentacao", "titulo", "tipo_movimentacao", "data",
				"valor_total", "valor_parcial", "lat_long", "flag_efetivada",
				"id_origem", "id_usuario" }, null, null, null, null,
				"id_movimentacao desc");
		if (cursor.moveToFirst()) {
			do {
				resultado.add(new Movimentacao(
						cursor.getInt(cursor.getColumnIndex("id_movimentcao")),
						cursor.getString(cursor.getColumnIndex("titulo")),
						cursor.getInt(cursor
								.getColumnIndex("tipo_movimentacao")),
						new Date(),// cursor.getInt(cursor.getColumnIndex("data")),
						new BigDecimal(cursor.getDouble(cursor
								.getColumnIndex("valor_parcial"))),
						new BigDecimal(cursor.getDouble(cursor
								.getColumnIndex("valor_total"))), cursor
								.getString(cursor.getColumnIndex("lat_long")),
						"S".equalsIgnoreCase(cursor.getString(cursor
								.getColumnIndex("flag_efetivada"))) ? true
								: false, new Origem(cursor.getInt(cursor
								.getColumnIndex("id_origem")), null),
						new Usuario(cursor.getInt(cursor
								.getColumnIndex("id_usuario")), null, null,
								null, null, null, true)));
			} while (cursor.moveToNext());
		}
		if (cursor != null && !cursor.isClosed()) {
			cursor.close();
		}
		return resultado;
	}
	
	public Double selectAllSum(Usuario user, String tipo_movimentacao)
	{
		Double _resultSum = 0.0d;
		
		Cursor c = db.rawQuery(SELECT_ALL_SUM,
				new String[ ] { user.getIdUsuario().toString(), tipo_movimentacao, "S" } ) ;
		if( c.moveToFirst())
		{
			_resultSum = c.getDouble( 0 );
		} 
		else 
		{
			_resultSum = -1.0d;
		}
		
		if (c != null && !c.isClosed())
		{
			c.close();
		}
		return _resultSum;
	}
}
