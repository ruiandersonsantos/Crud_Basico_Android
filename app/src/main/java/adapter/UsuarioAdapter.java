package adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import crud.com.br.crud.R;
import model.Usuario;

/**
 * Created by Rui on 20/01/2015.
 */
public class UsuarioAdapter extends BaseAdapter {

    private Context context;
    private List<Usuario> usuarios;

    public UsuarioAdapter(Context ctx, List<Usuario> listausuario){

        this.context = ctx;
        this.usuarios = listausuario;
    }

    @Override
    public int getCount() {
        return usuarios.size();
    }

    @Override
    public Object getItem(int posicao) {
        return usuarios.get(posicao);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Usuario usuario = usuarios.get(i);

        if (view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.usuarios,null);
        }

        TextView txtnome = (TextView) view.findViewById(R.id.usuario_lista_nome);
        txtnome.setText(usuario.getNome());

        return view;
    }
}
