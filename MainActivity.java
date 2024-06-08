package co.il.bulletdance;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txt;
    Player p;
    Button btnNormalShot;
    Button Up;
    Button Down;
    Button Left;
    Button Right;
    Button Shoot;
    LinearLayout CvsLay;
    MainBoard Mb;
    Timer timer;
    int score;

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
        txt = (TextView) findViewById(R.id.TxtView);
        Up.setBackgroundColor(Color.GREEN);
        Down.setBackgroundColor(Color.GREEN);
        Left.setBackgroundColor(Color.GREEN);
        Right.setBackgroundColor(Color.GREEN);
        Shoot = (Button) findViewById(R.id.btnShoot);
        Shoot.setOnClickListener(this);


        Mb = findViewById(R.id.view);
        p = Mb.getPlayer();

        timer = new Timer(txt);
        timer.start();

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.setTR(false); // Stop the timer when the activity is destroyed
        }
        score = 10000-50*Mb.totalSeq+25*Mb.successful- 10*Integer.parseInt(txt.getText().toString());
    }


    @Override
    public void onClick(View v) {
        if(v == Up){
            p.SetPosY(p.GetPosY()-1);
            Down.setBackgroundColor(Color.GREEN);
            if(p.GetPosY() == 1){ColorCheck(Up);}
            else Down.setClickable(true);
        }
        if(v == Down){
            p.SetPosY(p.GetPosY()+1);
            Up.setBackgroundColor(Color.GREEN);
            if(p.GetPosY() == 7){ColorCheck(Down);}
            else Up.setClickable(true);
        }
        if(v == Left){
            p.SetPosX(p.GetPosX()-1);
            Right.setBackgroundColor(Color.GREEN);
            if(p.GetPosX() == 1){ColorCheck(Left);}
            else Right.setClickable(true);
        }
        if(v == Right){
            p.SetPosX(p.GetPosX()+1);
            Left.setBackgroundColor(Color.GREEN);
            if(p.GetPosX() == 7){ColorCheck(Right);}
            else Left.setClickable(true);
        }
        if(v == Shoot){
            Mb.PlayerShoots();
            Log.w("Shoot", "Shoot");
            Shoot.setClickable(false);
            Shoot.setClickable(true);
        }
    }

    public void ColorCheck(View v) {
        if (v == Up) {
            Up.setBackgroundColor(Color.RED);
            Up.setClickable(false);
        }
        if (v == Down) {
            Down.setBackgroundColor(Color.RED);
            Down.setClickable(false);
        }
        if (v == Left) {
            Left.setBackgroundColor(Color.RED);
            Left.setClickable(false);
        }
        if (v == Right) {
            Right.setBackgroundColor(Color.RED);
            Right.setClickable(false);
        }
    }
}
