package co.il.bulletdance;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MainBoard extends View{
    int x;
    int y;
    Level lvl;
    List<Projectile> Seq;
    Projectile pla;
    Context context;
    Paint p;
    Bitmap BG;

    public MainBoard(Context context){
        super(context);
        this.context = context;
        BG = BitmapFactory.decodeResource(getResources(),R.drawable.board_no_bg_2_40);



        p = new Paint();
        p.setColor(Color.RED);
        p.setStrokeWidth(15);
        x = 1;y = 1;
        pla = new Projectile(10,60,true,x,y);
        //Log.d("I",Double.toString(pla.GetPosX()));
        lvl = new Level();
        lvl.Shoot(pla,0);
        lvl.Shoot(new Projectile(10,300,true,x,1000-y), 1);
        Seq = lvl.GetSeq();
        //Log.d("I3", Integer.toString(Seq.size()));
        //Log.d("I2", Double.toString(Seq.get(0).GetPosX()));
    }

    public MainBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        BG = BitmapFactory.decodeResource(getResources(),R.drawable.board_no_bg_2_40);



        p = new Paint();
        p.setColor(Color.RED);
        p.setStrokeWidth(15);
        x = 1;y = 1;
        pla = new Projectile(10,60,true,x,y);
        //Log.d("I",Double.toString(pla.GetPosX()));
        lvl = new Level();
        lvl.Shoot(pla,0);
        lvl.Shoot(new Projectile(10,300,true,x,1000-y), 1);
        Seq = lvl.GetSeq();
        //Log.d("I3", Integer.toString(Seq.size()));
        //Log.d("I2", Double.toString(Seq.get(0).GetPosX()));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        Log.d("CANVAS", "onDraw: " + canvas.getWidth() +", " + canvas.getHeight());

        if (Seq.isEmpty()){
            lvl.MakeSeq1(canvas);

            Log.i("I10",Integer.toString(Seq.size()));
        }

        Seq = lvl.GetSeq();

        canvas.drawBitmap(BG,25,100,null);
        //Log.w("I10",Integer.toString(Seq.size()));
        for (int i = 0; i <Seq.size(); i++){
           //Log.d("I6",Integer.toString(i) + " " + Integer.toString(Seq.size()));
            DrawProjectile(canvas, Seq.get(i));
            if(Seq.get(i).GetPosY()>canvas.getHeight() || Seq.get(i).GetPosX()>canvas.getWidth()
            || Seq.get(i).GetPosY()<1 || Seq.get(i).GetPosX()<1) {
                Seq.remove(i);
                //Log.d("I8","RESET" + Integer.toString(i));
                i = 0;
            }
        }



        invalidate();
    }

    public void DrawProjectile(Canvas canvas, Projectile proj){
        //Log.d("I7",Double.toString(proj.GetPosX()));
        Bitmap ProjBM = BitmapFactory.decodeResource(getResources(),R.drawable.projtemplate);
        canvas.drawBitmap(ProjBM, proj.GetPosX(),proj.GetPosY(), null);
        proj.Move();
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
