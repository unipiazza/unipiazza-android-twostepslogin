package com.lorenzobraghetto.material2stepslogin.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.lorenzobraghetto.material2stepslogin.R;
import com.lorenzobraghetto.material2stepslogin.TwoStepsLoginListener;
import com.lorenzobraghetto.material2stepslogin.fragments.FirstStepFragment;
import com.lorenzobraghetto.material2stepslogin.fragments.SecondStepFragment;

/**
 * Created by monossido on 26/06/15.
 */
public class MaterialTwoStepsLogin extends LinearLayout {

    private Context mContext;
    private View mView;
    private FragmentManager fm;
    private TwoStepsLoginListener mListener;
    private FirstStepFragment firstStepFragment;

    private String email;
    private String name;
    private Bitmap bitmap;
    private SecondStepFragment secondStepFragment;
    private int logo_image;
    private int description_text;
    private int register_background;
    private int register_description_text;
    private int register_text;
    private int first_step_background_color;
    private int second_step_background_color;
    private int edittext_password_background;
    private int button_login_background;
    private int edittext_email_background;
    private int button_next_background;

    public MaterialTwoStepsLogin(Context context) {
        super(context);
        this.mContext = context;

        init();
    }

    public MaterialTwoStepsLogin(Context context, AttributeSet attr) {
        super(context, attr);
        this.mContext = context;

        init();
    }

    private void init() {
        LayoutInflater inflater;
        inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mView = inflater.inflate(R.layout.login_view, this);

        firstStepFragment = new FirstStepFragment();
        secondStepFragment = new SecondStepFragment();
    }

    public void setActivity(AppCompatActivity activity) {
        fm = activity.getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.fragmentView, firstStepFragment)
                .commit();
    }

    public void setListener(TwoStepsLoginListener listener) {
        this.mListener = listener;
        firstStepFragment.setListener(this, mListener);
    }

    public void setInfosAfterNext(String email, String name, Bitmap avatar) {
        this.email = email;
        this.name = name;
        this.bitmap = avatar;
        firstStepFragment.emailVerified();
    }

    public void setNotVerified() {
        firstStepFragment.notVerified();
    }

    public void setPasswordWrong() {
        secondStepFragment.wrongPassword();
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public SecondStepFragment getSecondStepFragment() {
        return secondStepFragment;
    }

    public int getLogo() {
        return logo_image;
    }

    public void setLogo(int logo) {
        this.logo_image = logo;
    }

    public int getDescription() {
        return description_text;
    }

    public void setDescription(int description_text) {
        this.description_text = description_text;
    }

    public int getRegister_description() {
        return register_description_text;
    }

    public void setRegister_description(int register_description) {
        this.register_description_text = register_description;
    }

    public int getRegister_text() {
        return register_text;
    }

    public void setRegister_text(int register_text) {
        this.register_text = register_text;
    }

    public int getRegister_background() {
        return register_background;
    }

    public void setRegister_background(int register_background) {
        this.register_background = register_background;
    }

    public int getFirst_step_background_color() {
        return first_step_background_color;
    }

    public void setFirst_step_background_color(int first_step_background_color) {
        this.first_step_background_color = first_step_background_color;
    }

    public int getSecond_step_background_color() {
        return second_step_background_color;
    }

    public void setSecond_step_background_color(int second_step_background_color) {
        this.second_step_background_color = second_step_background_color;
    }

    public int getEdittext_password_background() {
        return edittext_password_background;
    }

    public void setEdittext_password_background(int edittext_password_background) {
        this.edittext_password_background = edittext_password_background;
    }

    public int getButton_login_background() {
        return button_login_background;
    }

    public void setButton_login_background(int button_login_background) {
        this.button_login_background = button_login_background;
    }

    public int getEdittext_email_background() {
        return edittext_email_background;
    }

    public void setEdittext_email_background(int edittext_email_background) {
        this.edittext_email_background = edittext_email_background;
    }

    public int getButton_next_background() {
        return button_next_background;
    }

    public void setButton_next_background(int button_next_background) {
        this.button_next_background = button_next_background;
    }
}
