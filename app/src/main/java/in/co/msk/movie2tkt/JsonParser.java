package in.co.msk.movie2tkt;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class JsonParser {


    InputStream  is;
    String line,sb="",json="";
    JSONObject jobj;

    public JSONObject makehttprequest(String url,String method,List<NameValuePair> params)
    {


        if(method=="POST")
        {
            DefaultHttpClient httpclient= new DefaultHttpClient();
            HttpPost httppost=new HttpPost(url);
            try {

                httppost.setEntity(new UrlEncodedFormEntity(params));
                HttpResponse httpresponse=httpclient.execute(httppost);
                HttpEntity httpentity=httpresponse.getEntity();
                is=httpentity.getContent();

            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
        else if(method=="GET")
        {
            DefaultHttpClient httpclient=new DefaultHttpClient();
            String paramstring=URLEncodedUtils.format(params, "utf-8");
            url=url+"?"+paramstring;

            HttpGet httpget=new HttpGet(url);

            try {
                HttpResponse httpresponse = httpclient.execute(httpget);
                httpresponse = httpclient.execute(httpget);
                HttpEntity httpentity=httpresponse.getEntity();
                is=httpentity.getContent();

            } catch (ClientProtocolException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }


        BufferedReader br=new BufferedReader(new InputStreamReader(is));
        try {
            while((line=br.readLine())!=null)
            {
                sb=sb+line;
            }
            is.close();


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            jobj=new JSONObject(sb);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Log.d("JSON",jobj.toString());
        return jobj;

    }

    public JSONObject getJSONObject(int i) {
        // TODO Auto-generated method stub
        return null;
    }


}

