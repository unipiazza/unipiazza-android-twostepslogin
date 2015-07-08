package com.lorenzobraghetto.myapplicationtest;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.lorenzobraghetto.material2stepslogin.MaterialTwoStepsLogin;
import com.lorenzobraghetto.material2stepslogin.TwoStepsLoginListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.unipiazza.restlibrary.utentiapp.RestClient;
import com.unipiazza.restlibrary.utentiapp.model.Token;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {

    private MaterialTwoStepsLogin login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = (MaterialTwoStepsLogin) findViewById(R.id.login_view);
        login.setActivity(this);
        login.setListener(new TwoStepsLoginListener() {
            @Override
            public void onNextClicked(String email) {
                //TODO
                Toast.makeText(MainActivity.this, "Next Clicked, controllare mail", Toast.LENGTH_LONG).show();
                RestClient.getInstance().postAuthenticate(MainActivity.this, email, "androidftw", new Callback<Token>() {
                    @Override
                    public void success(final Token token, Response response) {
                        Picasso.with(MainActivity.this).load(token.getCurrentUser().getImage_url()).placeholder(R.drawable.ic_add_image).into(new Target() {
                            @Override
                            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                                login.setInfosAfterNext(token.getCurrentUser().getEmail(), token.getCurrentUser().getFirst_name() + token.getCurrentUser().getLast_name(), bitmap);
                            }

                            @Override
                            public void onBitmapFailed(Drawable errorDrawable) {

                            }

                            @Override
                            public void onPrepareLoad(Drawable placeHolderDrawable) {

                            }
                        });
                    }

                    @Override
                    public void failure(RetrofitError error) {

                    }
                });

            }

            @Override
            public void onLoginClicked(String password) {
                Toast.makeText(MainActivity.this, "onLoginClicked, controllare psw", Toast.LENGTH_LONG).show();
                //TODO
            }
        });
    }
}
