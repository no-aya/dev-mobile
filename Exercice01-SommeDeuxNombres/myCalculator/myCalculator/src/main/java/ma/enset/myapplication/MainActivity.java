package ma.enset.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView result;
    EditText premiernum, deuxiemenum;
    MaterialButton btnSum,btnRen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        premiernum=findViewById(R.id.number1);
        deuxiemenum=findViewById(R.id.number2);


        assignId(btnSum,R.id.sum);
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
        Float num1 = Float.parseFloat(premiernum.getText().toString());
        Float num2 = Float.parseFloat(deuxiemenum.getText().toString());

        if(buttonText.equals("Rénitialiser")){
            result.setText("0");
            premiernum.setText("");
            deuxiemenum.setText("");
            return;
        }
        if(buttonText.equals("Somme")){
            Float somme = num1+num2;
            result.setText(somme.toString());
            return;
        }

    }
}