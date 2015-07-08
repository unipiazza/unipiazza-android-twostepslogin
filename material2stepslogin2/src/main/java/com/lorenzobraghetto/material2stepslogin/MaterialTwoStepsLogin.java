package com.lorenzobraghetto.material2stepslogin;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

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
    private Fragment currentFragment;

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

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

}
