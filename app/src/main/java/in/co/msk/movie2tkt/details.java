package in.co.msk.movie2tkt;

/**
 * Created by Sony on 3/27/2018.
 */
public class details {
    String movname,thename,dat,time,nos,pname,cost,phone;
    // int tnh,nha;float per;
    details(String movname, String thename,String dat,String time,String nos,String pname,String cost,String phone)    {
        //  this.sno=sno;
        this.movname=movname;
        this.thename=thename;
        this.dat=dat;
        this.time=time;
        this.nos=nos;
        this.pname=pname;
        this.cost=cost;
        this.phone=phone;
    }
    public String getMovname()
    {
        return  movname;

    }
    public String getThename()
    {
        return  thename;

    }
    public String getDat()
    {
        return  dat;

    }
    public String getTime()
    {
        return  time;

    }
    public String getNos()
    {
        return  nos;

    }
    public String getPname()
    {
        return  pname;

    }
    public String getCost()
    {
        return  cost;

    }
    public String getPhone()
    {
        return  phone;

    }
   /* public Bitmap getBitmap() {
      /*  Bitmap bitmap = null;

        InputStream in = null;
        try {
            in = new URL(picurl).openStream();bitmap = BitmapFactory.decodeStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
//return pic;

    //  }*/
}


