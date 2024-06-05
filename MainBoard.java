package co.il.bulletdance;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

import androidx.annotation.NonNull;

public class MainBoard extends View{
    int x;
    int y;
    int x1, y1;
    Level lvl;
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
        x = 1;y = 1;x1 = 1; y1 = 1000;
        pla = new Projectile(10,60,true,x,y);
        lvl = new Level();
        lvl.Shoot(pla,0);
        lvl.Shoot(new Projectile(10,300,true,x1,y1), 1);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawBitmap(BG,25,100,null);
        for (int i = 0; i <2; i++){
            DrawProjectile(canvas, lvl.GetSeq().get(i));
        }



        invalidate();
    }

    public void DrawProjectile(Canvas canvas, Projectile proj){
        Bitmap ProjBM = BitmapFactory.decodeResource(getResources(),R.drawable.projtemplate);
        canvas.drawBitmap(ProjBM, proj.GetPosX(),proj.GetPosY(), null);
        x = x + ((int)(proj.GetSpeed()*Math.cos(Math.toRadians(proj.GetAngle()))));
        y= y + ((int)(proj.GetSpeed()*Math.sin(Math.toRadians(proj.GetAngle()))));
    }
}
