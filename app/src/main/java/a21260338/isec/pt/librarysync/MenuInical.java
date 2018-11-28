package a21260338.isec.pt.librarysync;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuInical extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_inical);

    }

    public void onRegistar(View v){
        Intent intent = new Intent(this, MenuRegistar.class);
        startActivity(intent);
    }

    public void esqueceuPassword(View v){
        Intent intent = new Intent(this, MenuEsqueceuPassword.class);
        startActivity(intent);
    }

    public void login(View v){
        Intent intent = new Intent(this, MenuPrincipal.class);
        startActivity(intent);
    }
}
