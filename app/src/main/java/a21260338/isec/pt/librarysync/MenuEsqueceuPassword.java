package a21260338.isec.pt.librarysync;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuEsqueceuPassword extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_esqueceu_password);
    }

    public void onRegistar(View v){
        Intent intent = new Intent(this, MenuRegistar.class);
        startActivity(intent);
    }

    public void onLogin(View v){
        finish();
    }
}
