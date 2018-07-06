package in.co.msk.movie2tkt;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Mov extends AppCompatActivity {
TextView tm;ProgressDialog pd;String mvname,url;
    List<details> listdet;adap adp; ListView lv;
@Override
public  void onBackPressed()
{
super.onBackPressed();
    Intent i= new Intent(Mov.this,MainActivity.class);
    startActivity(i);
}

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mov);
        lv=(ListView)findViewById(R.id.list1);
        //tm=(TextView)findViewById(R.id.tm);

        listdet=new ArrayList<>();
        mvname=getIntent().getStringExtra("mvname");
        // ph=(ImageView)view.findViewById(R.id.phid);
        url="https://msk111297.000webhostapp.com/Project/viewspeci.php";
        callurl callurl=new callurl();
        callurl.execute(url);

        //tm.setText("Selected movie: "+mvname);
    }
    class callurl extends AsyncTask<String,String,JSONObject> {
        public void onPreExecute()
        {
            pd=new ProgressDialog(Mov.this);
            pd.setMessage("Fetching avaialable tickets...");
            pd.setIndeterminate(true);
            pd.setCancelable(true);
            pd.show();

        }

        @Override
        protected JSONObject doInBackground(String... params) {
            ArrayList<NameValuePair> param=new ArrayList<NameValuePair>();
            JsonParser parser=new JsonParser();

            param.add(new BasicNameValuePair("mname", mvname));
            return parser.makehttprequest(params[0], "POST", param);

        }

        public void onPostExecute(JSONObject result) {
            if(pd.isShowing() && pd!=null)
            {
                pd.dismiss();
            }
            Toast.makeText(Mov.this,"Inside post exec",Toast.LENGTH_LONG).show();
            String mname,date,time,nos,cost,mobile,pname,thname,y ="",m="",d="";
            JSONArray array;
            ArrayList<HashMap<String, String>> list=new ArrayList<HashMap<String, String>>();
            try {
                Toast.makeText(Mov.this,"Inside try",Toast.LENGTH_LONG).show();
                array = result.getJSONArray("getrow");
                for (int i = 0; i < array.length(); i++) {

                    JSONObject obj = array.getJSONObject(i);
                    //sn=obj.getString("seno");
                    mname= obj.getString("mname");
                    date = obj.getString("date");
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
                    time= obj.getString("time");
                    nos = obj.getString("nos");
                    cost= obj.getString("cost");
                    mobile= obj.getString("mobile");
                    pname= obj.getString("pname");
                    thname=obj.getString("thname");
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

                    HashMap<String, String> map = new HashMap<String, String>();
                    map.put("mname",mname);
                    map.put("date",date);
                    map.put("time",time);
                    map.put("nos",nos);
                    map.put("cost",cost);
                    map.put("mobile",mobile);
                    map.put("pname",pname);
                    map.put("thname",thname);
                    listdet.add(new details(mname,thname,date,time,nos,pname,cost,mobile));
                    //list.add(map);
                    Toast.makeText(Mov.this,"Inside for",Toast.LENGTH_LONG).show();

                }
            }
            catch (JSONException e) {

                // TODO Auto-generated catch block
                Toast.makeText(Mov.this,"Inside catch",Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }

            // String [] from={"mname","thname","date","time","nos","cost","mobile","pname"};
            //int[] to={R.id.mov,R.id.thn,R.id.dat,R.id.neram,R.id.seat,R.id.kaasu,R.id.ph,R.id.peru};
            //ListAdapter adapter=new SimpleAdapter(ViewTkts.this,list,R.layout.listtkt,from,to);
            adp=new adap(Mov.this,R.layout.listtkt,listdet);
            lv.setAdapter(adp);

        }
    }
}


