package co.il.bulletdance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txt;
    Player p;
    Button btnNormalShot;
    Button Up;
    Button Down;
    Button Left;
    Button Right;
    LinearLayout CvsLay;
    MainBoard Mb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //REAL_CODE_ERROR:
        //setContentView(R.layout.activity_main);btnNormalShot = (Button) findViewById(R.id.btnNormalShot);
        setContentView(R.layout.activity_main);


        //CANVAS_VIEW:
        //setContentView(Mb);
        //RIGHT_NOW:
        Up = (Button) findViewById(R.id.btnUp);
        Up.setOnClickListener(this);
        Down = (Button) findViewById(R.id.btnDown);
        Down.setOnClickListener(this);
        Left = (Button) findViewById(R.id.btnLeft);
        Left.setOnClickListener(this);
        Right = (Button) findViewById(R.id.btnRight);
        Right.setOnClickListener(this);
        p = new Player(1);
        txt = (TextView) findViewById(R.id.TxtView);

        Mb = findViewById(R.id.view);




    }


    @Override
    public void onClick(View v) {
        if(v == Up){
            p.SetPosY(p.GetPosY()+1);

        }
        if(v == Down){
            p.SetPosY(p.GetPosY()-1);

        }
        if(v == Left){
            p.SetPosX(p.GetPosX()-1);

        }
        if(v == Right){
            p.SetPosX(p.GetPosX()+1);

        }
        txt.setText(p.GetPosX() + " + " + p.GetPosY());

    }
}
