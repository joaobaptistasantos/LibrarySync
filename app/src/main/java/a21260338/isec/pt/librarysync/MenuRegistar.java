package a21260338.isec.pt.librarysync;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MenuRegistar extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_registar);
    }

    public void onLogin(View v){
        finish();
    }
}
