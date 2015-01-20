package crud.com.br.crud;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import dao.UsuarioDAO;
import model.Usuario;
import util.Mensagem;


public class CadUsuarioActivity extends Activity {

    private EditText edtnome, edtlogin, edtsenha;
    private UsuarioDAO usuarioDAO;
    private Usuario usuario;
    private int idusuario;

    private void cadastra(){

        boolean validacao = true;

        String nome = edtnome.getText().toString();
        String login = edtlogin.getText().toString();
        String senha = edtsenha.getText().toString();

        if(nome == null || nome.equals("")){
            validacao = false;
            edtnome.setError(getString(R.string.txt_campo_obrigatorio));
        }

        if(login == null || login.equals("")){
            validacao = false;
            edtlogin.setError(getString(R.string.txt_campo_obrigatorio));
        }

        if(senha == null || senha.equals("")){
            validacao = false;
            edtsenha.setError(getString(R.string.txt_campo_obrigatorio));
        }

        if(validacao){

            usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setLogin(login);
            usuario.setSenha(senha);

            // Verificando se é atualização
            if(idusuario > 0){
                usuario.set_id(idusuario);
            }

            long resultado = usuarioDAO.salvarUsuario(usuario);

            if(resultado != -1){
                if(idusuario > 0){

                    Mensagem.msg(this,getString(R.string.mensagem_atualizar));

                }else{

                    Mensagem.msg(this,getString(R.string.mensagem_cadastrar));
                }

                finish();
                startActivity(new Intent(this,MainActivity.class));
            }else{

                Mensagem.msg(this,getString(R.string.mensagem_error));
            }


        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_usuario);

        usuarioDAO = new UsuarioDAO(this);

        edtnome = (EditText) findViewById(R.id.cadUsuario_edtNome);
        edtlogin = (EditText) findViewById(R.id.cadUsuario_edtlogin);
        edtsenha = (EditText) findViewById(R.id.cadUsuario_edtsenha);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.cadastros, menu);

        if(idusuario > 0){
            menu.findItem(R.id.menu_excluir).setVisible(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

       switch (id){

           case R.id.menu_salvar:
               this.cadastra();
               break;

           case R.id.menu_sair:
               finish();
               startActivity(new Intent(this,MainActivity.class));
               break;
       }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        usuarioDAO.fechar();
        super.onDestroy();
    }
}
