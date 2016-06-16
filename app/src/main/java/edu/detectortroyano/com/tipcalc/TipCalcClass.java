package edu.detectortroyano.com.tipcalc;

/**
 * Created by detectortroyano on 09/06/2016.
 */
import android.app.Application;

public class TipCalcClass extends Application {
    private final static String ABOUT_URL = "https://about.me/adriancatalan";

    public String getAboutUrl(){
        return this.ABOUT_URL;
    }
}
