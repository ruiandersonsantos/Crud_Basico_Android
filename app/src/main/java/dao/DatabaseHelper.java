package dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Rui on 18/01/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String BANCO_DADOS = "tarefas";
    private static final int VERSAO = 1;


    public DatabaseHelper(Context context) {
        super(context, BANCO_DADOS, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Criando tabela de usuarios.
        String sqlUsuario = "create table usuarios(_id integer primary key autoincrement, " +
                "nome text not null, login text not null, senha text not null)";
        db.execSQL(sqlUsuario);

        // Criando tabela de tarefas.
        String sqlTarefa = "create table tarefas(_id integer primary key autoincrement," +
                "tarefa text not null, dt_criacao date not null, dt_completado)";
        db.execSQL(sqlTarefa);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static class Usuarios{
        public static final String TABELA = "usuarios";
    }

    public static class Tarefas{
        public static final String TABELA = "tarefas";
    }
}
