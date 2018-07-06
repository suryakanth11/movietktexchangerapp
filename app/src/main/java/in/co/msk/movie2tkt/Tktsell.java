package in.co.msk.movie2tkt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Tktsell extends AppCompatActivity {
    EditText movname, dat, tim, nos, cost, mobi,pna,thname;
    DatePicker dp;TimePicker tp;int hr,min;
    Button sub;
    String movie, da, ti, no, co, mo,pn,tn,day,month,year;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tktsell);
thname=(EditText)findViewById(R.id.tname);
        movname = (EditText) findViewById(R.id.mname);
        dp = (DatePicker) findViewById(R.id.mdate);
        dp.setSpinnersShown(false);
        tp = (TimePicker) findViewById(R.id.mtime);
        nos = (EditText) findViewById(R.id.mno);
        cost = (EditText) findViewById(R.id.mcos);
        mobi = (EditText) findViewById(R.id.mob);
        pna=(EditText)findViewById(R.id.pname);
        sub = (Button) findViewById(R.id.sell);
mobi.setOnFocusChangeListener(new View.OnFocusChangeListener() {
    @Override
    public void onFocusChange(View view, boolean b) {
        if((mobi.getText().length()<10)&&(mobi.getText().length()>10))
            mobi.setError("Mobile number should be only 10 digits");
    }
});
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                movie = movname.getText().toString();
                day=""+dp.getDayOfMonth();
                month=""+(dp.getMonth()+1);
                year=""+dp.getYear();

                da=year+"-"+month+"-"+day;
                //da = dat.getText().toString();
                hr=tp.getCurrentHour();
                min=tp.getCurrentMinute();
                ti = hr+":"+min;
                pn=pna.getText().toString();
                no = nos.getText().toString();
                co = cost.getText().toString();
                mo = mobi.getText().toString();

                tn=thname.getText().toString();
                Toast.makeText(Tktsell.this, "" + movie + da + ti + no + co + mo, Toast.LENGTH_SHORT).show();
                String url = "https://msk111297.000webhostapp.com/Project/selltkts.php";
                callurl calUrl = new callurl();
                calUrl.execute(url);

            }
        });
    }

    class callurl extends AsyncTask<String, String, JSONObject> {
        public void onPreExecute() {
            pd = new ProgressDialog(Tktsell.this);
            pd.setMessage("Loading...");
            pd.setIndeterminate(true);
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            // TODO Auto-generated method stub
            ArrayList<NameValuePair> param = new ArrayList<NameValuePair>();
            JsonParser parser = new JsonParser();
            param.add(new BasicNameValuePair("mname", movie));
            param.add(new BasicNameValuePair("mdat", da));
            param.add(new BasicNameValuePair("mtim", ti));
            param.add(new BasicNameValuePair("nus", no));
            param.add(new BasicNameValuePair("cot", co));
            param.add(new BasicNameValuePair("mobil", mo));
param.add(new BasicNameValuePair("pname",pn));
            param.add(new BasicNameValuePair("thname",tn));

            return parser.makehttprequest(params[0], "POST", param);

        }

        public void onPostExecute(JSONObject result) {

            if (pd.isShowing() && pd != null) {
                pd.dismiss();
            }

            int success;
            try {
                success = Integer.parseInt(result.getString("success"));
                if (success == 1) {
                    Toast.makeText(Tktsell.this, "Exchange info added successfully ", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(Tktsell.this, "Failed to add info please try again ", Toast.LENGTH_LONG).show();
                    Intent adminpage = new Intent(Tktsell.this, MainActivity.class);
                    startActivity(adminpage);
                }

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


}


