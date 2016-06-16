package edu.detectortroyano.com.tipcalc.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.detectortroyano.com.tipcalc.R;
import edu.detectortroyano.com.tipcalc.TipCalcClass;
import edu.detectortroyano.com.tipcalc.fragment.TipHistoryListFragment;
import edu.detectortroyano.com.tipcalc.fragment.TipHistoryListFragmentListener;
import edu.detectortroyano.com.tipcalc.models.TipRecord;


public class MainActivity extends AppCompatActivity{

    @Bind(R.id.inputBill)
    EditText inputBill;
    @Bind(R.id.btnSubmmit)
    Button btnSubmmit;
    @Bind(R.id.inputPercentaje)
    EditText inputPercentaje;
    @Bind(R.id.btnIncrease)
    Button btnIncrease;
    @Bind(R.id.btnDecrease)
    Button btnDecrease;
    @Bind(R.id.btnClear)
    Button btnClear;
    @Bind(R.id.txtTip)
    TextView txtTip;

    private TipHistoryListFragmentListener fragmentListener;

    private final static int TIP_STEP_CHANGE = 1;
    private final static int DEFAULT_TIP_PERCENTAJE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        TipHistoryListFragment fragment =  (TipHistoryListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.fragmentList);
        fragment.setRetainInstance(true);
        fragmentListener = (TipHistoryListFragmentListener) fragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            about();
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnSubmmit)
    public void handleClickSubmit(){
        //Log.e(getLocalClassName(), "Click en submit");
        hideKeyboard();
        String strInputTotal = inputBill.getText().toString().trim();
        if (!strInputTotal.isEmpty()){
            double total = Double.parseDouble(strInputTotal);
            int tipPercentaje = getTipPercentaje();

            TipRecord tipRecord = new TipRecord();
            tipRecord.setBill(total);
            tipRecord.setTipPercentaje(tipPercentaje);
            tipRecord.setTimestap(new Date());

            double tip = total * (tipPercentaje/100d);

            String strTip = String.format(getString(R.string.global_message_tip),
                    tipRecord.getTip());
            //fragmentListener.action(strTip);
            fragmentListener.addToList(tipRecord);
            txtTip.setVisibility(View.VISIBLE);
            txtTip.setText(strTip);
        }
    }

    @OnClick(R.id.btnIncrease)
    public void handleClickIncrease() {
        //Log.e(getLocalClassName(), "Click en submit");
        hideKeyboard();
        handleTipChange(TIP_STEP_CHANGE);
    }

    @OnClick(R.id.btnDecrease)
    public void handleClickDecrease() {
        //Log.e(getLocalClassName(), "Click en submit");
        hideKeyboard();
        handleTipChange(-TIP_STEP_CHANGE);
    }

    @OnClick(R.id.btnClear)
    public void handleClickClear() {
        //Log.e(getLocalClassName(), "Click en submit");
        fragmentListener.clearList();
    }

    public void handleTipChange(int change) {
        int tipPercentaje = getTipPercentaje();
        tipPercentaje += change;
        if(tipPercentaje > 0){
            inputPercentaje.setText(String.valueOf(tipPercentaje));
        }
    }

    public int getTipPercentaje(){
        int tipPercentaje = DEFAULT_TIP_PERCENTAJE;
        String strInputTipPercentaje = inputPercentaje.getText().toString().trim();
        if(!strInputTipPercentaje.isEmpty()){
            tipPercentaje = Integer.parseInt(strInputTipPercentaje);
        }else{
            inputPercentaje.setText(String.valueOf(tipPercentaje));
        }
        return tipPercentaje;
    }

    private void hideKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager)
            getSystemService(Context.INPUT_METHOD_SERVICE);
        try{
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }catch(NullPointerException npe){
            Log.e(getLocalClassName(),Log.getStackTraceString(npe));
        }
    }

    private void about(){
        TipCalcClass app = (TipCalcClass)getApplication();
        String strUrl = app.getAboutUrl();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(strUrl));
        startActivity(intent);
    }
}
