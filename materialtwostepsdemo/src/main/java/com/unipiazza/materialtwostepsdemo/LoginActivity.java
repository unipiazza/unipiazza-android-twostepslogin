package com.unipiazza.materialtwostepsdemo;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lorenzobraghetto.material2stepslogin.TwoStepsLoginListener;
import com.lorenzobraghetto.material2stepslogin.view.MaterialTwoStepsLogin;

public class LoginActivity extends AppCompatActivity implements TwoStepsLoginListener {

    private MaterialTwoStepsLogin login_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_view = (MaterialTwoStepsLogin) findViewById(R.id.login_view);

        login_view.setListener(this);
        login_view.setActivity(this);
        login_view.setFirst_step_background_color(getResources().getColor(R.color.colorPrimary));
        login_view.setSecond_step_background_color(Color.WHITE);
        login_view.setLogo(R.mipmap.ic_launcher);
        login_view.setDescription(R.string.insert_email_login);
        login_view.setRegister_description(R.string.not_registered_login);
        login_view.setRegister_text(R.string.registrati);

        //Register button background
        //login_view.setRegister_background(R.drawable.rounded_white_stroke_button);

        //EditText backgounds
        //login_view.setEdittext_password_background(R.drawable.edittext_unipiazza_background);
        //login_view.setEdittext_email_background(R.drawable.edittext_unipiazza_background);

        //Button backgrounds
        //login_view.setButton_login_background(R.drawable.rounded_orange_light_btn);
        //login_view.setButton_next_background(R.drawable.rounded_orange_btn);
    }

    @Override
    public void onNextClicked(String email) {
        /*TODO Check if email is corrected
            ...
            if it is not call login_view.setNotVerified();
        */
        login_view.setInfosAfterNext(email
                , "Material Two Steps Demo"
                , BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
    }

    @Override
    public void onLoginClicked(String password) {
        /*TODO Check if password is corrected
            ...
            if it is not call login_view.setPasswordWrong();
            otherwise go on with your Activities
        */
    }

    @Override
    public void onRecoverPasswordClicked() {
        //Called when user click to recover password
    }

    @Override
    public void onBackToMail() {
        //Called when user click to back button
    }

    @Override
    public void onRegisterClicked() {
        //Called when user click to register
    }
}
