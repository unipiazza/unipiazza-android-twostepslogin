/*
 * Copyright 2016 Unipiazza
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.unipiazza.material2stepslogin.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.unipiazza.material2stepslogin.R;
import com.unipiazza.material2stepslogin.TwoStepsLoginListener;
import com.unipiazza.material2stepslogin.fragments.FirstStepFragment;
import com.unipiazza.material2stepslogin.fragments.SecondStepFragment;

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
    private int button_register_text_color;
    private int first_step_background_color;
    private int second_step_background_color;
    private int edittext_password_background;
    private int button_login_background;
    private int edittext_email_background;
    private int button_next_background;
    private int button_login_text_color;
    private int button_next_text_color;
    private int description_text_color;
    private int edittext_email_text_color;
    private int name_text_color;
    private int edittext_passw_text_color;
    private int button_pass_forget_text_color;
    private int pass_forget_description_text_color;
    private int pass_forget_description_text;
    private int register_description_text_color;
    private int button_pass_forget_text;
    private int email_text_color;
    private int button_login_text;

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

    public int getDescription_text_color() {
        return description_text_color;
    }

    public void setDescription_text_color(int description_text_color) {
        this.description_text_color = description_text_color;
    }

    public int getEditText_email_text_color() {
        return edittext_email_text_color;
    }

    public void setEdittext_email_text_color(int edittext_email_text_color) {
        this.edittext_email_text_color = edittext_email_text_color;
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

    public int getName_text_color() {
        return name_text_color;
    }

    public void setName_text_color(int name_text_color) {
        this.name_text_color = name_text_color;
    }

    public int getEmail_text_color() {
        return email_text_color;
    }

    public void setEmail_text_color(int email_text_color) {
        this.email_text_color = email_text_color;
    }

    public int getEdittext_password_text_color() {
        return edittext_passw_text_color;
    }

    public void setEdittext_password_text_color(int edittext_passw_text_color) {
        this.edittext_passw_text_color = edittext_passw_text_color;
    }

    public int getButton_login_text_color() {
        return button_login_text_color;
    }

    public void setButton_login_text_color(int button_login_text_color) {
        this.button_login_text_color = button_login_text_color;
    }

    public int getButton_next_text_color() {
        return button_next_text_color;
    }

    public void setButton_next_text_color(int button_next_text_color) {
        this.button_next_text_color = button_next_text_color;
    }

    public int getButton_register_text_color() {
        return button_register_text_color;
    }

    public void setButton_register_text_color(int button_register_text_color) {
        this.button_register_text_color = button_register_text_color;
    }

    public int getButton_passforget_text_color() {
        return button_pass_forget_text_color;
    }

    public void setButton_passforget_text_color(int button_pass_forget_text_color) {
        this.button_pass_forget_text_color = button_pass_forget_text_color;
    }

    public int getButton_passforget_text() {
        return button_pass_forget_text;
    }

    public void setButton_passforget_text(int button_pass_forget_text) {
        this.button_pass_forget_text = button_pass_forget_text;
    }

    public int getPassforget_description_text() {
        return pass_forget_description_text;
    }

    public void setPassforget_description_text(int pass_forget_description_text) {
        this.pass_forget_description_text = pass_forget_description_text;
    }

    public int getPassforget_description_text_color() {
        return pass_forget_description_text_color;
    }

    public void setPassforget_description_text_color(int pass_forget_description_text_color) {
        this.pass_forget_description_text_color = pass_forget_description_text_color;
    }

    public int getRegister_description_text_color() {
        return register_description_text_color;
    }

    public void setRegister_description_text_color(int register_description_text_color) {
        this.register_description_text_color = register_description_text_color;
    }

    public int getButton_login_text() {
        return button_login_text;
    }

    public void setButton_login_text(int button_login_text) {
        this.button_login_text = button_login_text;
    }
}
