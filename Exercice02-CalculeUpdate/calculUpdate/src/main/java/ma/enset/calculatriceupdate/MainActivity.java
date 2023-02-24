package ma.enset.calculatriceupdate;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //TextView result;
    EditText premierchamp, deuxiemechamp;
    MaterialButton btnconn,btnRen;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this,getString(R.string.tutorial),Toast.LENGTH_LONG).show();
        //Send result to ResultActivity

        premierchamp=findViewById(R.id.premierchamp);
        deuxiemechamp=findViewById(R.id.deuxiemechamp);


        assignId(btnconn,R.id.connexion);
        assignId(btnRen,R.id.cancel_button);

    }

    void assignId (MaterialButton btn, int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button=(MaterialButton) view;
        String buttonText=button.getText().toString();

        if(buttonText.equals(getString(R.string.reset))){
            premierchamp.setText("");
            deuxiemechamp.setText("");
            return;
        }
        if(buttonText.equals(getString(R.string.conn))){
            //send result to ResultActivity
            Intent intent = new Intent(this, ResultActivity.class);
            intent.putExtra("username",premierchamp.getText().toString());
            intent.putExtra("password",deuxiemechamp.getText().toString());
            startActivity(intent);
        }

    }

    @Override
    protected void onPause() {
        Toast.makeText(this,getString(R.string.toast_missu),Toast.LENGTH_LONG).show();
        super.onPause();
    }

    @Override
    protected void onRestart() {
        Toast.makeText(this,getString(R.string.toast_res),Toast.LENGTH_LONG).show();
        super.onRestart();
    }

    @Override
    protected void onResume() {
        Toast.makeText(this,getString(R.string.toast_back),Toast.LENGTH_LONG).show();
        super.onResume();
    }
}