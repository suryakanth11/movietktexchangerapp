package in.co.msk.movie2tkt;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewTkts extends AppCompatActivity {
    ListView lv;ProgressDialog pd;List<details>listdet;adap adp;
    String url;//ImageView ph;//LayoutInflater layoutInflater=LayoutInflater.from(ViewTkts.this);
  //  View view=layoutInflater.inflate(R.layout.listtkt,null);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tkts);
        lv=(ListView)findViewById(R.id.list);
        listdet=new ArrayList<>();
       // ph=(ImageView)view.findViewById(R.id.phid);
        url="https://msk111297.000webhostapp.com/Project/viewtkts.php";
        callurl callurl=new callurl();
        callurl.execute(url);

    }
class callurl extends AsyncTask<String,String,JSONObject> {
    public void onPreExecute()
    {
        pd=new ProgressDialog(ViewTkts.this);
        pd.setMessage("Loading...");
        pd.setIndeterminate(true);
        pd.setCancelable(true);
        pd.show();

    }

    @Override
    protected JSONObject doInBackground(String... params) {
        ArrayList<NameValuePair> param=new ArrayList<NameValuePair>();
        JsonParser parser=new JsonParser();
        return parser.makehttprequest(params[0], "POST", param);

    }

    public void onPostExecute(JSONObject result) {
        if(pd.isShowing() && pd!=null)
        {
            pd.dismiss();
        }
        Toast.makeText(ViewTkts.this,"Inside post exec",Toast.LENGTH_LONG).show();
        String mname,date,time,nos,cost,mobile,pname,thname,y ="",m="",d="";
        JSONArray array;
        ArrayList<HashMap<String, String>> list=new ArrayList<HashMap<String, String>>();
        try {
            Toast.makeText(ViewTkts.this,"Inside try",Toast.LENGTH_LONG).show();
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
                Toast.makeText(ViewTkts.this,"Inside for",Toast.LENGTH_LONG).show();

            }
        }
        catch (JSONException e) {

            // TODO Auto-generated catch block
            Toast.makeText(ViewTkts.this,"Inside catch",Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

       // String [] from={"mname","thname","date","time","nos","cost","mobile","pname"};
        //int[] to={R.id.mov,R.id.thn,R.id.dat,R.id.neram,R.id.seat,R.id.kaasu,R.id.ph,R.id.peru};
        //ListAdapter adapter=new SimpleAdapter(ViewTkts.this,list,R.layout.listtkt,from,to);
        adp=new adap(ViewTkts.this,R.layout.listtkt,listdet);
        lv.setAdapter(adp);

    }
}
}
