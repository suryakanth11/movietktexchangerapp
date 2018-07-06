package in.co.msk.movie2tkt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
Button ex,vi,sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ex=(Button)findViewById(R.id.xch);
        vi=(Button)findViewById(R.id.vie);
        sp=(Button)findViewById(R.id.spc);
        ex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Tktsell.class);
                startActivity(i);
            }
        });
        vi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,ViewTkts.class);
                startActivity(i);
            }
        });
        sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Spi.class);
                startActivity(i);
            }
        });
    }
}
