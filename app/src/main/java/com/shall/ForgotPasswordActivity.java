package com.shall;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shall.network.App;
import com.shall.network.Controller;
import com.shall.pojo.ForgotPassword;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class ForgotPasswordActivity extends AppCompatActivity {
    @Inject
    Retrofit retrofit;

    @BindView(R.id.bForgotPassword)
    Button bForgotPassword;
    @BindView(R.id.tvPassword)
    TextView tvPassword;
    @BindView(R.id.etUsername)
    EditText etUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
        ((App) getApplication()).getNetComponent().inject(ForgotPasswordActivity.this);

        bForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestForgotPassword();
            }
        });
    }

    private void requestForgotPassword() {
        if (!etUsername.getText().toString().isEmpty()) {


            Call<ForgotPassword> response = retrofit.create(Controller.class).forgotPassword(etUsername.getText().toString());

            Log.e("Forgot Password URL:", response.request().url().toString());
            response.enqueue(new Callback<ForgotPassword>() {
                @Override
                public void onResponse(Call<ForgotPassword> call, retrofit2.Response<ForgotPassword> response) {
                    String password = response.body().getPassword();
                    tvPassword.setText(password);
                }

                @Override
                public void onFailure(Call<ForgotPassword> call, Throwable t) {

                }
            });
        } else {
            etUsername.setError("Required");
        }
    }
}
