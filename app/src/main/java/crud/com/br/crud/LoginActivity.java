package crud.com.br.crud;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import dao.UsuarioDAO;
import util.Mensagem;


public class LoginActivity extends Activity {

    private EditText edtusuario, edtsenha;
    private UsuarioDAO helper;
    private CheckBox checkBoxConectado;

    private static final String MANTER_CONECTADO = "manter_conectado";
    private static final String PREFERENCE_NAME = "LoginActivityPreference";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtusuario = (EditText) findViewById(R.id.login_edtusuario);
        edtsenha = (EditText) findViewById(R.id.login_edtusenha);
        checkBoxConectado = (CheckBox)findViewById(R.id.login_check_manterConectado);
        helper = new UsuarioDAO(this);

        SharedPreferences preferences = getSharedPreferences(PREFERENCE_NAME,MODE_PRIVATE);
        boolean conectado = preferences.getBoolean(MANTER_CONECTADO,false);

        if(conectado){
            chamarMainActivity();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void logar(View view){

        String usuario = edtusuario.getText().toString();
        String senha = edtsenha.getText().toString();

        boolean validacao = true;

        if (usuario == null || usuario.equals("")){

            validacao = false;
            edtusuario.setError(getString(R.string.valida_usuario));

        }

        if (senha == null || senha.equals("")){

            validacao = false;
            edtsenha.setError(getString(R.string.valida_senha));

        }

        if(validacao){
            if (helper.logar(usuario,senha)){

                if(checkBoxConectado.isChecked()){

                    SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCE_NAME,MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(MANTER_CONECTADO,true);
                    editor.commit();

                }

                chamarMainActivity();


            }else{

                Mensagem.msg(this,getString(R.string.msg_error_Login));
            }
        }

    }

    public void chamarMainActivity(){
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
