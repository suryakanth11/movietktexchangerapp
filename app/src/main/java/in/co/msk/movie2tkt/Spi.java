package in.co.msk.movie2tkt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Spi extends AppCompatActivity {
Spinner spin;ArrayList<String> listItems=new ArrayList<>();
    ProgressDialog pd;Button fit;
    ArrayAdapter<String> adapter;String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spi);
        fit=(Button)findViewById(R.id.ft);
        url="https://msk111297.000webhostapp.com/Project/viewspin.php";
    spin=(Spinner)findViewById(R.id.spin);
        adapter=new ArrayAdapter<String>(this,R.layout.spinner_layout,R.id.txt,listItems);
        spin.setAdapter(adapter);
        fit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Spi.this,Mov.class);
                i.putExtra("mvname",spin.getSelectedItem().toString());
                startActivity(i);
            }
        });
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(Spi.this,""+spin.getSelectedItem().toString(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void onStart(){
        super.onStart();
        callurl bt=new callurl();
        bt.execute(url);
    }
    class callurl extends AsyncTask<String,String,JSONObject> {
        ArrayList<String> list1;
        protected void onPreExecute(){
            super.onPreExecute();
            pd=new ProgressDialog(Spi.this);
            pd.setMessage("Fetching Movie Details...");
            pd.setIndeterminate(true);
            pd.setCancelable(true);
            pd.show();
            list1=new ArrayList<>();
        }

        @Override
        protected JSONObject doInBackground(String... params) {
            ArrayList<NameValuePair> param=new ArrayList<NameValuePair>();
            JsonParser parser=new JsonParser();
            return parser.makehttprequest(params[0], "POST", param);

        }

        public void onPostExecute(JSONObject result) {

            Toast.makeText(Spi.this,"Inside post exec",Toast.LENGTH_LONG).show();
            String mname,date,time,nos,cost,mobile,pname,thname,y ="",m="",d="";
            JSONArray array;
            ArrayList<HashMap<String, String>> list=new ArrayList<HashMap<String, String>>();
            try {
                Toast.makeText(Spi.this,"Inside try",Toast.LENGTH_LONG).show();
                array = result.getJSONArray("getrow");
                for (int i = 0; i < array.length(); i++) {

                    JSONObject obj = array.getJSONObject(i);
                    //sn=obj.getString("seno");
                    mname = obj.getString("mname");
                    list1.add(obj.getString("mname"));
                }//   date = obj.getString("date");
                /*for(int j=0;j<date.length();j++)
                {
                    if(j<4)
                    {
                        y+=date.charAt(j);
                    }
                    if(j>=5&&j<=6)
                        m+=date.charAt(j);
                    else if(j>=8&&j<=10)
                        d+=date.charAt(j);
                }
String dat=d+"-"+"-"+m+"-"+y;*/

              /*  final String finalMobile = mobile;
                ph.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + finalMobile));

        if (ActivityCompat.checkSelfPermission(ViewTkts.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(intent);
    }
});*/


            }
            catch (JSONException e) {

                // TODO Auto-generated catch block
                Toast.makeText(Spi.this,"Inside catch",Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

            // String [] from={"mname","thname","date","time","nos","cost","mobile","pname"};
            //int[] to={R.id.mov,R.id.thn,R.id.dat,R.id.neram,R.id.seat,R.id.kaasu,R.id.ph,R.id.peru};
            //ListAdapter adapter=new SimpleAdapter(ViewTkts.this,list,R.layout.listtkt,from,to);

            //           adp=new adap(ViewTkts.this,R.layout.listtkt,listdet);
   //         lv.setAdapter(adp);
            listItems.addAll(list1);
            adapter.notifyDataSetChanged();
        }
    }
}
