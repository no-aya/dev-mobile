package ma.enset.calculatrice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

import org.mariuszgromada.math.mxparser.Expression;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView resultTv, solutionTv;
    MaterialButton buttonC, buttonBrackOpen, buttonBrackClose;
    MaterialButton buttonDiv, buttonMult, buttonPlus, buttonMin, buttonEq;
    MaterialButton button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    MaterialButton buttonFac, buttonSqrt, buttonPow, buttonLog, buttonLn, buttonSin, buttonCos, buttonTan,buttonPi, buttonE, buttonAns, buttonInv, buttonRad,buttonDeg;
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

            //Test if orientation changed
            if (savedInstanceState != null) {
                assignId(buttonSqrt, R.id.button_sqrt);
                assignId(buttonFac, R.id.button_fac);
                assignId(buttonPow, R.id.button_pow);
                assignId(buttonLog, R.id.button_log);
                assignId(buttonLn, R.id.button_ln);
                assignId(buttonSin, R.id.button_sin);
                assignId(buttonCos, R.id.button_cos);
                assignId(buttonTan, R.id.button_tan);
                assignId(buttonPi, R.id.button_pi);
                assignId(buttonE, R.id.button_e);
                assignId(buttonAns, R.id.button_ans);
                assignId(buttonInv, R.id.button_inv);
                assignId(buttonRad, R.id.button_rad);
                assignId(buttonDeg, R.id.button_deg);
            }
        }//Assigning Ids block
        if (savedInstanceState != null) {
            String solution = savedInstanceState.getString("solution");
            solutionTv.setText(solution);
            String result = savedInstanceState.getString("result");
            resultTv.setText(result);
            savedInstanceState.clear();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("solution", solutionTv.getText().toString());
        outState.putString("result", resultTv.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getString("solution");
        savedInstanceState.getString("result");
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
        if(buttonText.equals("x^y")){
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 3);
            dataToCalculate = dataToCalculate + "^";
        }
        if(buttonText.equals("x!")){
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 2);
            dataToCalculate = dataToCalculate + "!";
        }
        if(buttonText.equals("sin")){
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 3);
            dataToCalculate = dataToCalculate + "sin(";
        }
        if(buttonText.equals("cos")){
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 3);
            dataToCalculate = dataToCalculate + "cos(";
        }
        if(buttonText.equals("tan")){
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 3);
            dataToCalculate = dataToCalculate + "tan(";
        }
        if(buttonText.equals("log")){
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 3);
            dataToCalculate = dataToCalculate + "log(";
        }
        if(buttonText.equals("ln")){
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 2);
            dataToCalculate = dataToCalculate + "ln(";
        }
        if(buttonText.equals("√")){
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            dataToCalculate = dataToCalculate + "sqrt(";
        }
        if(buttonText.equals("π")){
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            dataToCalculate = dataToCalculate + "pi";
        }
        if(buttonText.equals("e")){
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 1);
            dataToCalculate = dataToCalculate + "e";
        }
        if(buttonText.equals("Ans")){
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 3);
            dataToCalculate = dataToCalculate + "ans";
        }
        if(buttonText.equals("deg")){
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 3);
            dataToCalculate = dataToCalculate + "deg(";
        }
        if(buttonText.equals("Rad")){
            dataToCalculate = dataToCalculate.substring(0, dataToCalculate.length() - 3);
            dataToCalculate = dataToCalculate + "rad(";
        }

        solutionTv.setText(dataToCalculate);
        System.out.println(dataToCalculate);

        String finalResult = getResult(dataToCalculate);
        if (!finalResult.equals("NaN")) {
            resultTv.setText(finalResult);
        }
    }

    String getResult(String data) {
        //boolean test = Pattern.matches("^[-+]?[0-9]*\\.?[0-9]+([-+*/]?([0-9]*\\.?[0-9]+))*$", data);
        try {
            if (data.endsWith("+") || data.endsWith("-") || data.endsWith("*") || data.endsWith("/")) {
                data = data.substring(0, data.length() - 1);
            }
            if (data.startsWith("*") || data.startsWith("/")) {
                data = data.substring(1);
            }
            if (data.contains("++") || data.contains("--") || data.contains("**") || data.contains("//")) {
                return "NaN";
            }
            if (data.contains("+-") || data.contains("-+") || data.contains("*-") || data.contains("/-")) {
                data = data.replace("+-", "-");
                data = data.replace("-+", "-");
                data = data.replace("*-", "*-1*");
                data = data.replace("/-", "/-1*");
            }

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