package com.lorenzobraghetto.material2stepslogin.fragments;

import android.animation.Animator;
import android.annotation.SuppressLint;
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

import com.lorenzobraghetto.material2stepslogin.R;
import com.lorenzobraghetto.material2stepslogin.TwoStepsLoginListener;
import com.lorenzobraghetto.material2stepslogin.view.MaterialTwoStepsLogin;

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
        progressBarSecond = (ProgressBar) view.findViewById(R.id.progressBarSecond);
        layoutSecond = (RevealLinearLayout) view.findViewById(R.id.layoutSecond);

        progressBarSecond.setVisibility(View.GONE);

        if (mtsl != null) {
            email.setText(mtsl.getEmail());
            name.setText(mtsl.getName());
            if (mtsl.getBitmap() != null)
                profile_image.setImageBitmap(mtsl.getBitmap());
            else
                profile_image.setImageResource(R.drawable.ic_add_image);
        }

        Button buttonLogin = (Button) view.findViewById(R.id.buttonLogin);
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
        view.setBackgroundColor(mtsl.getSecond_step_background_color());

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

    @SuppressLint("NewApi")
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
