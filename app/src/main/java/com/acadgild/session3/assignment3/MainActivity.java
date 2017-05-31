package com.acadgild.session3.assignment3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {
    EditText etcard, etrate, etpay;
    TextView etfbal, etmonth, etIn;
    Button btcomp, btclear;
    List<EditText> allEditTexts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etcard = (EditText) findViewById(R.id.etCard);
        etrate = (EditText) findViewById(R.id.etRate);
        etpay = (EditText) findViewById(R.id.etPay);

        etfbal = (TextView) findViewById(R.id.etFbal);
        etmonth = (TextView) findViewById(R.id.etMonth);
        etIn = (TextView) findViewById(R.id.etIn);
        btcomp = (Button) findViewById(R.id.btcomp);
        btclear = (Button) findViewById(R.id.btclear);
        btcomp.setOnClickListener(this);
        btclear.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String principal = etcard.getText().toString();
        String rate = etrate.getText().toString();
        boolean setLast = false;

        String pay = etpay.getText().toString();
        switch (v.getId()) {

            // on compute button click
            case R.id.btcomp : {

                float princ = Float.parseFloat(principal);
                float rates = Float.parseFloat(rate);
                int count = 0;

                int pays = Integer.parseInt(pay);

                int monthlyinterestpaid = Math.round((princ * (rates / (100 * 12))));

                etIn.setText("" + monthlyinterestpaid);
                float monthlyprincipal = pays - monthlyinterestpaid;
                float monthlyBalance = princ - monthlyprincipal;
                etfbal.setText("" + monthlyBalance);
                for (int i = 1; i < 1000; i++) {
                    count++;
                    if (setLast == true) {

                        monthlyinterestpaid = Math.round((princ * rates) / (100 * 12));
                        monthlyprincipal = monthlyBalance + monthlyinterestpaid;
                        monthlyBalance = 0;
                        break;


                    }

                    // calculation
                    if (!(princ <= 0) && setLast == false) {
                        monthlyinterestpaid = Math.round((princ * rates) / (100 * 12));
                        monthlyprincipal = pays - monthlyinterestpaid;

                        monthlyBalance = princ - monthlyprincipal;
                        if (monthlyBalance < pays) {
                            setLast = true;
                        }
                        princ = (int) monthlyBalance;


                    } else {
                        break;
                    }


                }
                etmonth.setText("" + count);
                break;
            }

            // to clear all input
            case R.id.btclear : {
                allEditTexts = new ArrayList<>();
                allEditTexts.add(etcard);
                allEditTexts.add(etrate);
                allEditTexts.add(etpay);

                for (EditText editText : allEditTexts)
                    editText.setText("");

                Toast.makeText(getApplicationContext(), "Cleared", Toast.LENGTH_SHORT).show();
                break;
            }

            default: {
                Toast.makeText(getApplicationContext(), "No donuts for you", Toast.LENGTH_SHORT).show();
            }


        }

    }
}
