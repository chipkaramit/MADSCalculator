package com.amit.madscalculator;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String REGEXOPERATORS = "[/+,-,/*,//,-]";
    public static final String REGEXDIGITS = "(\\d+)";
    public static Character[] OPERATORS = {'*', '+', '/', '-'};
    public static ArrayList<Character> operators = new ArrayList<Character>();
    public static ArrayList<Integer> digits = new ArrayList<Integer>();
    TextView tvResults;
    EditText etDisplay;
    Button btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine, btnZero,
            btnAddition, btnSubtraction, btnMultiplication, btnDivision, btnResults, btnClear;

    public static void getDigits(String math) {

        Pattern r = Pattern.compile(REGEXDIGITS);
        Matcher m = r.matcher(math);
        while (m.find()) {
            int t = Integer.parseInt(math.substring(m.start(), m.end()));
            digits.add(t);
        }
    }

    public static void getOperators(String math) {
        Pattern r = Pattern.compile(REGEXOPERATORS);
        Matcher m = r.matcher(math);
        while (m.find()) {
            operators.add(math.charAt(m.start()));
        }

    }

    private static void getNextOperator(ArrayList<Character> operators) {
        for (Character op : OPERATORS) {
            //Multiplication
            for (int i = 0; i < operators.size(); i++) {

                if (operators.get(i) == '*') {
                    operators.remove(i);
                    digits.set(i, (digits.get(i) * digits.get(i + 1)));
                    digits.remove(i + 1);
                    i -= 1;
                }
            }

            //addition
            for (int i = 0; i < operators.size(); i++) {

                if (operators.get(i) == '+') {
                    operators.remove(i);
                    digits.set(i, (digits.get(i) + digits.get(i + 1)));
                    digits.remove(i + 1);
                    i -= 1;
                }
            }
            //Division
            for (int i = 0; i < operators.size(); i++) {

                if (operators.get(i) == '/') {
                    operators.remove(i);
                    digits.set(i, (digits.get(i) / digits.get(i + 1)));
                    digits.remove(i + 1);
                    i -= 1;
                }
            }

            //subtraction

            for (int i = 0; i < operators.size(); i++) {

                if (operators.get(i) == '-') {
                    operators.remove(i);
                    digits.set(i, (digits.get(i) - digits.get(i + 1)));
                    digits.remove(i + 1);
                    i -= 1;
                }
            }


        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        etDisplay = findViewById(R.id.et_display);
        tvResults = findViewById(R.id.tv_display_result);

        btnOne = findViewById(R.id.button_one);
        btnOne.setOnClickListener(this);
        btnTwo = findViewById(R.id.button_two);
        btnTwo.setOnClickListener(this);
        btnThree = findViewById(R.id.button_three);
        btnThree.setOnClickListener(this);
        btnFour = findViewById(R.id.button_four);
        btnFour.setOnClickListener(this);
        btnFive = findViewById(R.id.button_five);
        btnFive.setOnClickListener(this);
        btnSix = findViewById(R.id.button_six);
        btnSix.setOnClickListener(this);
        btnSeven = findViewById(R.id.button_seven);
        btnSeven.setOnClickListener(this);
        btnEight = findViewById(R.id.button_eight);
        btnEight.setOnClickListener(this);
        btnNine = findViewById(R.id.button_nine);
        btnNine.setOnClickListener(this);
        btnZero = findViewById(R.id.button_zero);
        btnZero.setOnClickListener(this);
        btnAddition = findViewById(R.id.button_addition);
        btnAddition.setOnClickListener(this);
        btnSubtraction = findViewById(R.id.button_subtraction);
        btnSubtraction.setOnClickListener(this);
        btnMultiplication = findViewById(R.id.button_multiplication);
        btnMultiplication.setOnClickListener(this);
        btnDivision = findViewById(R.id.button_division);
        btnDivision.setOnClickListener(this);
        btnResults = findViewById(R.id.button_results);
        btnResults.setOnClickListener(this);
        btnClear = findViewById(R.id.button_clear);
        btnClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.button_zero:
                etDisplay.setText(etDisplay.getText() + "0");
                break;
            case R.id.button_one:
                etDisplay.setText(etDisplay.getText() + "1");
                break;
            case R.id.button_two:
                etDisplay.setText(etDisplay.getText() + "2");
                break;
            case R.id.button_three:
                etDisplay.setText(etDisplay.getText() + "3");
                break;
            case R.id.button_four:
                etDisplay.setText(etDisplay.getText() + "4");
                break;
            case R.id.button_five:
                etDisplay.setText(etDisplay.getText() + "5");
                break;
            case R.id.button_six:
                etDisplay.setText(etDisplay.getText() + "6");
                break;
            case R.id.button_seven:
                etDisplay.setText(etDisplay.getText() + "7");
                break;
            case R.id.button_eight:
                etDisplay.setText(etDisplay.getText() + "8");
                break;
            case R.id.button_nine:
                etDisplay.setText(etDisplay.getText() + "9");
                break;
            case R.id.button_addition:
                if (!TextUtils.isEmpty(etDisplay.getText())) {

                    if (checkOperator()) {
                        etDisplay.setText(etDisplay.getText() + "+");
                    }
                }

                break;
            case R.id.button_subtraction:
                if (!TextUtils.isEmpty(etDisplay.getText())) {
                    if (checkOperator()) {
                        etDisplay.setText(etDisplay.getText() + "-");
                    }
                }

                break;
            case R.id.button_multiplication:
                if (!TextUtils.isEmpty(etDisplay.getText())) {
                    if (checkOperator()) {
                        etDisplay.setText(etDisplay.getText() + "*");
                    }
                }

                break;
            case R.id.button_division:
                if (!TextUtils.isEmpty(etDisplay.getText())) {
                    if (checkOperator()) {
                        etDisplay.setText(etDisplay.getText() + "/");
                    }
                }

                break;
            case R.id.button_clear:
                etDisplay.setText("");
                break;
            case R.id.button_results:
                madsCalculations();
                break;
            default:
                break;
        }
    }

    private boolean checkOperator() {
        String last = etDisplay.getText().toString();
        last = last.substring(last.length() - 1);
        return !last.equalsIgnoreCase("+") && !last.equalsIgnoreCase("-") && !last.equalsIgnoreCase("*") && !last.equalsIgnoreCase("/");
    }
    private boolean isLastNumber() {
        String last = etDisplay.getText().toString();
        last = last.substring(last.length() - 1);
        return !last.equalsIgnoreCase("0") && !last.equalsIgnoreCase("1") && !last.equalsIgnoreCase("2") && !last.equalsIgnoreCase("3")
                && !last.equalsIgnoreCase("4") && !last.equalsIgnoreCase("5") && !last.equalsIgnoreCase("6") && !last.equalsIgnoreCase("7")
                && !last.equalsIgnoreCase("8") && !last.equalsIgnoreCase("9");
    }

    private void madsCalculations() {
        String strFinal = etDisplay.getText().toString();
        getDigits(strFinal);
        getOperators(strFinal);
        getNextOperator(operators);

        for (Integer digit : digits) {
            System.out.print(String.valueOf(digit) + ' ');
        }

        System.out.println();

        for (Character operator : operators) {
            System.out.print(operator);
        }

    }

}