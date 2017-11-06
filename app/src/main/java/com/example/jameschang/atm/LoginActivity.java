package com.example.jameschang.atm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();
    private EditText etUserName;
    private EditText etPsd;
    private CheckBox cbUserid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
        //


        cbUserid.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                getSharedPreferences(getString(R.string.pref_name), MODE_PRIVATE)
                        .edit()
                        .putBoolean("REMEMBER", isChecked)
                        .apply();
            }
        });
        boolean remember = getSharedPreferences(getString(R.string.pref_name), MODE_PRIVATE)
                .getBoolean("REMEMBER",false);

        cbUserid.setChecked(remember);
        if( remember ){
            String userid  = getSharedPreferences(getString(R.string.pref_name), MODE_PRIVATE)
                    .getString("USERID", null);

            Log.d(TAG, "onCreate: " + userid);
            etUserName.setText(userid);

        }



    }

    private void findViews() {
        etUserName = findViewById(R.id.et_username);
        etPsd = findViewById(R.id.et_password);
        cbUserid = findViewById(R.id.cb_remember_userid);

    }

    public void loginListener(View view){

        String userid = etUserName.getText().toString();
        String password = etPsd.getText().toString();
        // 字串常數.equals放前面才不會null exception
        if( "James".equals(userid) &&
                "1234".equals(password)){
            getIntent().putExtra("USERID", userid);
            getIntent().putExtra("PASSWORD", password);
            setResult(RESULT_OK, getIntent());
            finish();
        }

    }

    public void quitListener(View view){

    }
}
