package com.example.jorgeandre.addevent;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;


public class SignUpActivity extends ActionBarActivity {

    private Button mSignUp;
    private EditText mEmail, mPassword, mBirthYear, mZipcode;
    //RadioButton mMale, mFemale;

    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        requestQueue=MySingelton.getInstance(getApplicationContext()).getRequestQueue();


        mEmail = (EditText)findViewById(R.id.email);
        mPassword = (EditText)findViewById(R.id.password);
        mBirthYear = (EditText)findViewById(R.id.birthYear);
        mZipcode = (EditText)findViewById(R.id.zipcode);
        mSignUp = (Button)findViewById(R.id.bSignUp);

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Person user=new Person();
                user.setEmail(mEmail.getText().toString());
                user.setPassword(mPassword.getText().toString());
                user.setBirthYear(Integer.parseInt(mBirthYear.getText().toString()));
                user.setZipCode(Integer.parseInt(mZipcode.getText().toString()));

                StringRequest stringRequest=new StringRequest(Request.Method.GET
                        ,"http://foodapplication.mybluemix.net/api/db/folder/?email="+user.getEmail()+"&password="+user.getPassword()+"&birthyear="+user.getBirthYear()+"&zipcode="+user.getZipCode()
                        ,new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    }
                },new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();
                    }
                });

                requestQueue.add(stringRequest);

                startActivity(new Intent(SignUpActivity.this, EnterEventActivity.class));
            }
        });



    }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
