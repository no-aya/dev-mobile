package ma.enset.calculatriceupdate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

        TextView user,pass;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_result);
            user=findViewById(R.id.userResult);
            pass=findViewById(R.id.passresult);
            Intent intent=getIntent();
            String resultText=intent.getStringExtra("username");
            String resultText2=intent.getStringExtra("password");
            user.setText(resultText);
            pass.setText(resultText2);
        }

    public void returnMain(View view) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
