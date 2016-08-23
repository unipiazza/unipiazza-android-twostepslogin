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

import android.Manifest;
import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.unipiazza.material2stepslogin.R;
import com.unipiazza.material2stepslogin.TwoStepsLoginListener;
import com.unipiazza.material2stepslogin.view.MaterialTwoStepsLogin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by monossido on 26/06/15.
 */
public class FirstStepFragment extends Fragment {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$", Pattern.CASE_INSENSITIVE);
    private static final int MY_PERMISSIONS_REQUEST_GET_ACCOUNTS = 123;

    private TwoStepsLoginListener mListener;
    private AutoCompleteTextView email;
    private MaterialTwoStepsLogin mtsl;
    private Button next;
    private ProgressBar progressBarFirst;
    private LinearLayout layoutFirst;
    private ImageView logo;
    private TextView insert_email_login;
    private TextView registerText;
    private Button buttonRegistra;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.first_login_view, null);

        email = (AutoCompleteTextView) view.findViewById(R.id.email);
        next = (Button) view.findViewById(R.id.buttonNext);
        progressBarFirst = (ProgressBar) view.findViewById(R.id.progressBarFirst);
        layoutFirst = (LinearLayout) view.findViewById(R.id.layoutFirst);
        logo = (ImageView) view.findViewById(R.id.logo);
        insert_email_login = (TextView) view.findViewById(R.id.insert_email_login);
        registerText = (TextView) view.findViewById(R.id.registerText);
        buttonRegistra = (Button) view.findViewById(R.id.buttonRegistra);

        progressBarFirst.setVisibility(View.GONE);

        if (mtsl != null) {
            view.setBackgroundColor(mtsl.getFirst_step_background_color());
            logo.setImageResource(mtsl.getLogo());
            insert_email_login.setText(mtsl.getDescription());
            registerText.setText(mtsl.getRegister_description());
            buttonRegistra.setText(mtsl.getRegister_text());
            buttonRegistra.setBackgroundResource(mtsl.getRegister_background());
            if (mtsl.getEdittext_email_background() != 0)
                email.setBackgroundResource(mtsl.getEdittext_email_background());
            next.setBackgroundResource(mtsl.getButton_next_background());
            if (mtsl.getButton_next_text_color() != 0)
                next.setTextColor(mtsl.getButton_next_text_color());
            if (mtsl.getButton_register_text_color() != 0)
                buttonRegistra.setTextColor(mtsl.getButton_register_text_color());
        }

        email.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    next.performClick();
                }
                return false;
            }
        });

        checkAccountPermissions();

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarFirst.setVisibility(View.VISIBLE);
                layoutFirst.setVisibility(View.GONE);
                mListener.onNextClicked(email.getText().toString());
                InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        });

        return view;
    }

    private void checkAccountPermissions() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.GET_ACCOUNTS)
                != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(new String[]{Manifest.permission.GET_ACCOUNTS},
                    MY_PERMISSIONS_REQUEST_GET_ACCOUNTS);
        } else {
            setAutoCompleteEmail();
        }
    }

    private void setAutoCompleteEmail() {
        Account[] accounts = AccountManager.get(getActivity()).getAccounts();
        Set<String> emailSet = new HashSet<String>();
        for (Account account : accounts) {
            if (EMAIL_PATTERN.matcher(account.name).matches()) {
                emailSet.add(account.name);
            }
        }
        ArrayList<String> emails = new ArrayList<>(emailSet);
        email.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, emails));
    }

    public void setListener(MaterialTwoStepsLogin mtsl, TwoStepsLoginListener listener) {
        this.mtsl = mtsl;
        mListener = listener;
    }

    public void emailVerified() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        mtsl.getSecondStepFragment().setListener(mtsl, mListener);
        fm.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.fragmentView, mtsl.getSecondStepFragment()).addToBackStack("secondStepLogin")
                .commit();
    }

    public void notVerified() {
        progressBarFirst.setVisibility(View.GONE);
        layoutFirst.setVisibility(View.VISIBLE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_GET_ACCOUNTS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    setAutoCompleteEmail();

                }
            }
        }
    }
}
