package com.darshilpatel.tipme;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static final NumberFormat currentFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();
    double billAmount = 0.0;
    double percent = 0.15;
    TextView amountTextView;
    TextView percentTextView;
    TextView tipTextView;
    TextView totalTextView;

    double finalAmount;
    double tip;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amountTextView = (TextView)findViewById(R.id.amountTextView);

        percentTextView = (TextView)findViewById(R.id.percentTextView);

            tipTextView = (TextView) findViewById(R.id.tipTextView);
        totalTextView = (TextView) findViewById(R.id.totalTextView);

        tipTextView.setText(currentFormat.format(0));

        totalTextView.setText(currentFormat.format(0));

            EditText amountEditText = (EditText)findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(amountEditTextWatcher);

        SeekBar percentSeekBar = (SeekBar)findViewById(R.id.percentSeekBar);
        percentSeekBar.setOnSeekBarChangeListener(seekBarListener);
    }

    private void calculate (){
        percentTextView.setText(percentFormat.format(percent));

        tip = billAmount * percent;
        finalAmount = billAmount + tip;

        tipTextView.setText(currentFormat.format(tip));
        totalTextView.setText(currentFormat.format(finalAmount));
    }
        //seekbar listener

    // everytime the user moves the slider, it will re calculate. SO the calculations are real time.
    private final SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            percent = progress /100.0; //converts into percent form
            calculate(); // calls the calculation function

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private final TextWatcher amountEditTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
          // fixed app crash on empty userInput

            try {
              billAmount = Double.parseDouble(s.toString()) / 100;
              amountTextView.setText(currentFormat.format(billAmount));
          } catch (NumberFormatException e){
                amountTextView.setText("");
              billAmount = 0.0;
          }
            calculate();// update textviews and calculations

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

}
