package ma.enset.calculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mariuszgromada.math.mxparser.Expression;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv, solutionTv;
    MaterialButton buttonC, buttonBrackOpen, buttonBrackClose;
    MaterialButton buttonDiv, buttonMult, buttonPlus, buttonMin, buttonEq;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonAC, buttonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        {
            resultTv = findViewById(R.id.result_small);
            solutionTv = findViewById(R.id.result_tv);

            assignId(buttonC, R.id.button_c);
            assignId(buttonBrackOpen, R.id.button_open_bracket);
            assignId(buttonBrackClose, R.id.button_closed_bracket);
            assignId(buttonDiv, R.id.button_div);
            assignId(buttonMult, R.id.button_mult);
            assignId(buttonPlus, R.id.button_plus);
            assignId(buttonMin, R.id.button_min);
            assignId(buttonEq, R.id.button_eq);

            assignId(buttonAC, R.id.button_ac);
            assignId(buttonDot, R.id.button_dot);

            assignId(button0, R.id.button_0);
            assignId(button1, R.id.button_1);
            assignId(button2, R.id.button_2);
            assignId(button3, R.id.button_3);
            assignId(button4, R.id.button_4);
            assignId(button5, R.id.button_5);
            assignId(button6, R.id.button_6);
            assignId(button7, R.id.button_7);
            assignId(button8, R.id.button_8);
            assignId(button9, R.id.button_9);
        }

    }

    void assignId(MaterialButton btn, int id) {
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if (dataToCalculate.startsWith("0")) dataToCalculate = dataToCalculate.substring(1);

        if (buttonText.equals("AC")) {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if (buttonText.equals("=")) {
            solutionTv.setText(resultTv.getText());
            return;
        }
        if (buttonText.equals("C")) {
            if (dataToCalculate.length() > 0)
                dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            else dataToCalculate = "0";
        } else {
            dataToCalculate = dataToCalculate + buttonText;
        }
        solutionTv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);
        if (!finalResult.equals("NaN")) {
            resultTv.setText(finalResult);
        } else {
            return;
        }

    }

    String getResult(String data) {
        //boolean test = Pattern.matches("^[-+]?[0-9]*\\.?[0-9]+([-+*/]?([0-9]*\\.?[0-9]+))*$", data);
        try {
            //TODO: mXparser
/*
                Context context = Context.enter();
                context.setOptimizationLevel(-1);
                Scriptable scriptable = context.initStandardObjects();
                String finalResult = context.evaluateString(scriptable, data, "Javascript", 1, null).toString();
*/
            Expression expression = new Expression(data);
            Double finalResult = expression.calculate();
                /*if (finalResult.endsWith(".0")) {
                    finalResult = finalResult.replace(".0", "");
                }*/

            return finalResult.toString();

        } catch (Exception e) {
            return "NaN";
        }
    }
}