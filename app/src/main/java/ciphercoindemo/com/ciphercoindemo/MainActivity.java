package ciphercoindemo.com.ciphercoindemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

public class MainActivity extends AppCompatActivity {
    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;
    private String tag_string_req = "string_req";
    private EditText etWSURL, etAPIKey, etToken;
    private Button btnSales;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext=MainActivity.this;

        etWSURL= (EditText) findViewById(R.id.editText);
        etAPIKey= (EditText) findViewById(R.id.editText2);
        etToken= (EditText) findViewById(R.id.editText3);
        btnSales= (Button) findViewById(R.id.button);


        etWSURL.setText("https://test.cmsget.org/edd-api/stats/");
        etAPIKey.setText("f03e7b2b3c975656a4321869d925a25c");
        etToken.setText("a30c1e85127469c364fb50443d9a459e");

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);


        btnSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (new ConnectionDetector(mContext).isConnectingToInternet()){
                    makeStringReq();
                }else {
                    Toast.makeText(mContext, "Please connect with internet.", Toast.LENGTH_LONG).show();
                }
            }
        });


    }


    private void makeStringReq() {
        showProgressDialog();
        String url_simple1 = "https://test.cmsget.org/edd-api/stats/?key=f03e7b2b3c975656a4321869d925a25c&token=a30c1e85127469c364fb50443d9a459e";

        StringRequest strReq = new StringRequest(Request.Method.GET,
                url_simple1, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    Intent intent= new Intent(MainActivity.this, Main2Activity.class);
                    intent.putExtra("response", response);
                    startActivity(intent);

                }catch (Exception e){
                    Log.d(TAG, e.toString());
                }
                hideProgressDialog();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hideProgressDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);

    }

    private void showProgressDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideProgressDialog() {
        if (pDialog.isShowing())
            pDialog.hide();
    }

}
