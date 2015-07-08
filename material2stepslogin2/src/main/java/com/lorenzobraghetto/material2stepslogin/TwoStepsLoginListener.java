package com.lorenzobraghetto.material2stepslogin;

/**
 * Created by monossido on 26/06/15.
 */
public interface TwoStepsLoginListener {
    void onNextClicked(String email);

    void onLoginClicked(String password);

    void onRecoverPassword();

    void onBackToMail();
}
