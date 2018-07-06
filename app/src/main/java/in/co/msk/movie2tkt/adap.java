package in.co.msk.movie2tkt;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sony on 3/27/2018.
 */
public class adap extends ArrayAdapter<details> {
    Context ctx;
    int listlayout;
    List<details>det;
    // List<imgstock> imgstocks;
    // SQLiteDatabase db;
    TextView tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, d, da;

    adap(Context ctx, int listlayout, List<details>det) {
        super(ctx, listlayout, det);
        this.ctx = ctx;
        this.listlayout = listlayout;
        this.det = det;
        //this.imgstocks = imgstocks;

        //Toast.makeText(this,"Inside const",Toast.LENGTH_SHORT).show();

    }
    private static class ViewHolder {

        ImageView imageView;

    }

    @Override
    @NonNull
    public View getView(int position, @Nullable View convertview, @NonNull ViewGroup parent) {
        //ViewHolder viewHolder;
        final LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.listtkt, parent,false);
        final details sub = det.get(position);
        //final imgstock h = imgstocks.get(position);
        //viewHolder = new ViewHolder();
        ImageView imageView = (ImageView)view.findViewById(R.id.phid);
        //Button butt=(Button)view.findViewById(R.id.b1);
        //view.setTag(viewHolder);

        //TextView sno=(TextView) view.findViewById(R.id.sn);
        final TextView mov = (TextView) view.findViewById(R.id.mov);
        TextView thea=(TextView)view.findViewById(R.id.thn);
        TextView da=(TextView)view.findViewById(R.id.dat);
        TextView ne=(TextView)view.findViewById(R.id.neram);
        TextView se=(TextView)view.findViewById(R.id.seat);
        TextView pn=(TextView)view.findViewById(R.id.peru);
        TextView ka=(TextView)view.findViewById(R.id.kaasu);
        final TextView mob=(TextView)view.findViewById(R.id.ph);
        mov.setText(sub.getMovname());
        thea.setText(sub.getThename());
        da.setText(sub.getDat());
        ne.setText(sub.getTime());
        se.setText(sub.getNos());
        pn.setText(sub.getPname());
        ka.setText(sub.getCost());
        mob.setText(sub.getPhone());
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String no = mob.getText().toString();

                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" +no));

                if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                ctx.startActivity(intent);

               /* String item=sn1.getText().toString();
                Toast.makeText(ctx,"Hi"+item,Toast.LENGTH_SHORT).show();
                Intent desc=new Intent(ctx,Desc.class);
                desc.putExtra("itemname",item);ctx.startActivity(desc);
*/
            }
        });
        //byte[] decodeString = Base64.decode(sub.getPic(), Base64.DEFAULT);
        //Bitmap decodebitmap = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
      /*  URL url;
        sn1.setText(sub.getName());
        tn1.setText(sub.getPic());
        Picasso.with(ctx).load(sub.getPic()).placeholder(R.drawable.loading).error(R.drawable.loading).into(imageView);
        *////      DownloadImageTask downloadImageTask=new DownloadImageTask(imageView);
        ////    downloadImageTask.execute(sub.getPic());
        ////Toast.makeText(getContext(),"Download Image task", Toast.LENGTH_SHORT).show();
        /*try
        {
            url = new URL(sub.getPic());
            Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
*/
        //byte[] decodeString = Base64.decode(sub.getPic(), Base64.DEFAULT);
        //Bitmap decodebitmap = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.length);
        //// Toast.makeText(getContext(),"Img deccoded",Toast.LENGTH_SHORT).show();
        // InputStream in=new java.net.URL(sub.getPic()).openStream();
        //   Bitmap bitmap= BitmapFactory.decodeStream(in);
        ////imageView.setImageBitmap(image);

        ////      }
       /* catch (Exception e)
       // {
            e.printStackTrace();
        }*/
        //imageView.setImageResource(R.drawable.i1);
        //tn1.setText(sub.getPic());
        //imageView.setImageResource(R.drawable.i1);
        //imageView.setImageResource(R.drawable.cart);
        //ImageView im1 = (ImageView) view.findViewById(R.id.i11);




        return view;
    }
}

