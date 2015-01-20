package util;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by Rui on 20/01/2015.
 */
public class Mensagem {

    public static void msg(Activity activity, String mensagem){

        Toast.makeText(activity,mensagem, Toast.LENGTH_LONG).show();
    }
}
