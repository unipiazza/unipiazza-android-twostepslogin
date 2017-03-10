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
package com.unipiazza.material2stepslogin.fragments;

import android.animation.Animator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.unipiazza.material2stepslogin.R;
import com.unipiazza.material2stepslogin.TwoStepsLoginListener;
import com.unipiazza.material2stepslogin.view.MaterialTwoStepsLogin;

import de.hdodenhof.circleimageview.CircleImageView;
import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;
import io.codetail.widget.RevealLinearLayout;

/**
 * Created by monossido on 26/06/15.
 */
public class SecondStepFragment extends Fragment implements View.OnKeyListener {

    private TwoStepsLoginListener mListener;
    private TextView email;
    private TextView name;
    private EditText editTextPassword;
    private MaterialTwoStepsLogin mtsl;
    private CircleImageView profile_image;
    private Button pass_forget;
    private ProgressBar progressBarSecond;
    private RevealLinearLayout layoutSecond;
    private Button buttonLogin;
    private TextView pass_forget_description;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        final View view = inflater.inflate(R.layout.second_login_view, null);

        email = (TextView) view.findViewById(R.id.email);
        name = (TextView) view.findViewById(R.id.name);
        editTextPassword = (EditText) view.findViewById(R.id.editTextPassword);
        profile_image = (CircleImageView) view.findViewById(R.id.profile_image);
        pass_forget = (Button) view.findViewById(R.id.pass_forget);
        pass_forget_description = (TextView) view.findViewById(R.id.pass_forget_description);
        progressBarSecond = (ProgressBar) view.findViewById(R.id.progressBarSecond);
        layoutSecond = (RevealLinearLayout) view.findViewById(R.id.layoutSecond);
        buttonLogin = (Button) view.findViewById(R.id.buttonLogin);

        progressBarSecond.setVisibility(View.GONE);

        if (mtsl != null) {
            email.setText(mtsl.getEmail());
            name.setText(mtsl.getName());
            profile_image.setImageBitmap(mtsl.getBitmap());

            if (mtsl.getButton_login_text_color() != 0)
                buttonLogin.setTextColor(mtsl.getButton_login_text_color());
            if (mtsl.getButton_login_background() != 0)
                buttonLogin.setBackgroundResource(mtsl.getButton_login_background());
            if (mtsl.getButton_login_text() != 0)
                buttonLogin.setText(mtsl.getButton_login_text());

            if (mtsl.getEdittext_password_background() != 0)
                editTextPassword.setBackgroundResource(mtsl.getEdittext_password_background());
            if (mtsl.getEdittext_password_text_color() != 0)
                editTextPassword.setTextColor(mtsl.getEdittext_password_text_color());

            if (mtsl.getName_text_color() != 0)
                name.setTextColor(mtsl.getName_text_color());

            if (mtsl.getButton_passforget_text_color() != 0)
                pass_forget.setTextColor(mtsl.getButton_passforget_text_color());
            if (mtsl.getButton_passforget_text() != 0)
                pass_forget.setText(mtsl.getButton_passforget_text());
            if (mtsl.getPassforget_description_text_color() != 0)
                pass_forget_description.setTextColor(mtsl.getPassforget_description_text_color());
            if (mtsl.getPassforget_description_text() != 0)
                pass_forget_description.setTextColor(mtsl.getPassforget_description_text());

            if (mtsl.getEmail_text_color() != 0)
                email.setTextColor(mtsl.getEmail_text_color());

            view.setBackgroundColor(mtsl.getSecond_step_background_color());
        }

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarSecond.setVisibility(View.VISIBLE);
                layoutSecond.setVisibility(View.GONE);
                mListener.onLoginClicked(editTextPassword.getText().toString());
            }
        });

        pass_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onRecoverPasswordClicked();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        ViewTreeObserver vto = view.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            @Override
            public void onGlobalLayout() {
                createReveal(profile_image);
                view.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(this);
    }

    private void createReveal(final View myView) {

        // get the center for the clipping circle
        int cx = (myView.getWidth()) / 2;
        int cy = (myView.getHeight()) / 2;

        // get the final radius for the clipping circle
        int finalRadius = Math.max(myView.getWidth(), myView.getHeight());

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Animator animator = android.view.ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);
            animator.setDuration(800);
            animator.start();
        } else {
            SupportAnimator animator =
                    ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);
            animator.setInterpolator(new AccelerateDecelerateInterpolator());
            animator.setDuration(800);
            animator.start();
        }
    }

    public void setListener(MaterialTwoStepsLogin mtsl, TwoStepsLoginListener listener) {
        mListener = listener;
        this.mtsl = mtsl;
    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                mListener.onBackToMail();
                return false;
        }
        return false;
    }

    public void wrongPassword() {
        progressBarSecond.setVisibility(View.GONE);
        layoutSecond.setVisibility(View.VISIBLE);
    }
}
