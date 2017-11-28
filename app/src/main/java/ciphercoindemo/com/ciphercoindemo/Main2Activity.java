package ciphercoindemo.com.ciphercoindemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONObject;


public class Main2Activity extends AppCompatActivity {
    private TextView tvSales, tvSalesDetails, tvEarnings, tvEarningDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvSales = (TextView) findViewById(R.id.tvSales);
        tvSalesDetails = (TextView) findViewById(R.id.tvSaleDetails);
        tvEarnings = (TextView) findViewById(R.id.tvEarnings);
        tvEarningDetails = (TextView) findViewById(R.id.tvEarningsDetails);


        Intent intent = getIntent();
        String response = intent.getStringExtra("response");


        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject stats = JSONUtils.getJSONObjectFromJSON(jsonObject, "stats");
            JSONObject sales = JSONUtils.getJSONObjectFromJSON(stats, "sales");
            JSONObject earning = JSONUtils.getJSONObjectFromJSON(stats, "earnings");

            String saleDetails = "Today " + JSONUtils.getStringFromJSON(sales, "today")
                    + "\nCurrent Month " + JSONUtils.getStringFromJSON(sales, "current_month")
                    + "\nLast Month " + JSONUtils.getStringFromJSON(sales, "last_month")
                    + "\ntotals " + JSONUtils.getStringFromJSON(sales, "totals");

            String earningDetails = "Today " + JSONUtils.getStringFromJSON(earning, "today")
                    + "\nCurrent Month " + JSONUtils.getStringFromJSON(earning, "current_month")
                    + "\nLast Month " + JSONUtils.getStringFromJSON(earning, "last_month")
                    + "\ntotals " + JSONUtils.getStringFromJSON(earning, "totals");


            tvSalesDetails.setText(saleDetails);
            tvEarningDetails.setText(earningDetails);


        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "", Toast.LENGTH_LONG).show();
        }


    }
}
