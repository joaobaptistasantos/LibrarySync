package a21260338.isec.pt.librarysync;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuPrincipal extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
    }

    public void exit(View v) {
        finish();
    }

    public void definicoes(View v){
        Intent intent = new Intent(this, MenuDefinicoes.class);
        startActivity(intent);
    }
}
