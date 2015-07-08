package com.lorenzobraghetto.material2stepslogin;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * Created by monossido on 26/06/15.
 */
public class FirstStepFragment extends Fragment {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$", Pattern.CASE_INSENSITIVE);

    private TwoStepsLoginListener mListener;
    private AutoCompleteTextView email;
    private MaterialTwoStepsLogin mtsl;
    private Button next;
    private ProgressBar progressBarFirst;
    private LinearLayout layoutFirst;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.first_login_view, null);

        email = (AutoCompleteTextView) view.findViewById(R.id.email);
        next = (Button) view.findViewById(R.id.buttonNext);
        progressBarFirst = (ProgressBar) view.findViewById(R.id.progressBarFirst);
        layoutFirst = (LinearLayout) view.findViewById(R.id.layoutFirst);

        email.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    next.performClick();
                }
                return false;
            }
        });

        Account[] accounts = AccountManager.get(getActivity()).getAccounts();
        Set<String> emailSet = new HashSet<String>();
        for (Account account : accounts) {
            if (EMAIL_PATTERN.matcher(account.name).matches()) {
                emailSet.add(account.name);
            }
        }
        ArrayList<String> emails = new ArrayList<>(emailSet);
        email.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, emails));
        if (emails.size() > 0)
            email.setText(emails.get(emails.size() - 1));

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarFirst.setVisibility(View.VISIBLE);
                layoutFirst.setVisibility(View.GONE);
                mListener.onNextClicked(email.getText().toString());
            }
        });

        return view;
    }

    public void setListener(MaterialTwoStepsLogin mtsl, TwoStepsLoginListener listener) {
        this.mtsl = mtsl;
        mListener = listener;
    }

    protected void emailVerified() {
        FragmentManager fm = getActivity().getSupportFragmentManager();
        SecondStepFragment fragment = new SecondStepFragment();
        fragment.setListener(mtsl, mListener);
        fm.beginTransaction().setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left, android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .replace(R.id.fragmentView, fragment).addToBackStack("secondStepLogin")
                .commit();
    }

    protected void notVerified() {
        progressBarFirst.setVisibility(View.GONE);
        layoutFirst.setVisibility(View.VISIBLE);
    }
}
