package com.akash;
package com.example.changelanguage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;

import com.example.changelanguage.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

     ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.changeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeLanguage();
            }
        });

    }

    private void changeLanguage() {
        final String language[] = {"English", "Hindi", "Bhojpuri", "Urdu"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        mBuilder.setTitle("Select Language");
        mBuilder.setSingleChoiceItems(language, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which ==0){
                    setLocale("");
                    recreate();
                }
                else if (which ==1){
                    setLocale("hi");
                    recreate();
                }
               else if (which ==2){
                    setLocale("bho");
                    recreate();
                }
                else if (which ==3){
                    setLocale("Ur");
                    recreate();
                }
            }
        });

        mBuilder.create();
        mBuilder.show();



    }

    private void setLocale(String language) {
        Locale locale = new Locale(language);
        Locale.setDefault(locale);

        Configuration configuration = new Configuration();
        configuration.setLocale(locale);
        getBaseContext().getResources().updateConfiguration(configuration, getBaseContext().getResources().getDisplayMetrics());

       SharedPreferences.Editor editor = getSharedPreferences("Setting", MODE_PRIVATE).edit();
       editor.putString("app_lang", language);
       editor.apply();
    }

    private void loadLocale(){
        SharedPreferences preference = getSharedPreferences("Setting", MODE_PRIVATE);
        String language = preference.getString("app_lang", "");
        setLocale(language);
    }

}
