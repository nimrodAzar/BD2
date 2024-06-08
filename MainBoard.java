package co.il.bulletdance;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
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
    MainActivity MA;
    boolean isRunning;
    int BG_X1;
    int BG_Y1;
    int BG_X2;
    int BG_Y2;
    Level lvl;
    List<Projectile> fSeq;
    Projectile tProj;
    boolean tb;
    Context context;
    Paint p;
    //Bitmap BG;
    Player playy;
    int successful;
    int totalSeq;
    Target Tarr;
    Bitmap bTarr;
    boolean fr;
    Bitmap Projjie;
    public Player getPlayer(){
        return playy;
    }

    public MainBoard(Context context){
        super(context);
        this.context = context;
        MA = (MainActivity) context;
        int screen_width = getResources().getDisplayMetrics().widthPixels;
        BG_X1 = screen_width/16;BG_Y1 = screen_width/16;
        BG_X2 = screen_width*15/16;BG_Y2 =screen_width*15/16;
        playy = new Player(1);


        //BG = scaleBitmap(getResources(),R.drawable.board_no_bg,BG_X1,BG_Y1,BG_X2,BG_Y2);


        p = new Paint();
        p.setColor(Color.RED);
        p.setStrokeWidth(15);
        //Log.d("I",Double.toString(pla.GetPosX()));
        lvl = new Level();
        fSeq = lvl.GetSeq();
        //Log.d("I3", Integer.toString(Seq.size()));
        //Log.d("I2", Double.toString(Seq.get(0).GetPosX()));
    }

    public MainBoard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        MA = (MainActivity) context;
        isRunning = true;

        int screen_width = getResources().getDisplayMetrics().widthPixels;
        BG_X1 = screen_width/16;BG_Y1 = screen_width/16;
        BG_X2 = screen_width*15/16;BG_Y2 =screen_width*15/16;
        Log.d("ISize",Integer.toString(BG_X1) + " " + Integer.toString(BG_Y1) + " " + Integer.toString(BG_X2) + " " + Integer.toString(BG_Y2));
        Tarr = new Target(5);
        bTarr = BitmapFactory.decodeResource(getResources(),R.drawable.target_normal);
        Tarr.setX((BG_X1+BG_X2)/2-bTarr.getWidth()/2);
        Tarr.setY(BG_Y1-bTarr.getHeight());
        Tarr.setDirection(1);

        //BG = scaleBitmap(getResources(),R.drawable.board_no_bg,BG_X1,BG_Y1,BG_X2,BG_Y2);
        playy = new Player(1);
        tProj = null;
        tb = false;
        fr = true;
        Projjie = BitmapFactory.decodeResource(getResources(),R.drawable.projtemplate);





        p = new Paint();

        p.setColor(Color.RED);
        p.setStrokeWidth(15);
        //Log.d("I",Double.toString(pla.GetPosX()));
        lvl = new Level();
        fSeq = lvl.GetSeq();
        //Log.d("I3", Integer.toString(Seq.size()));
        //Log.d("I2", Double.toString(Seq.get(0).GetPosX()));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //Log.d("CANVAS", "onDraw: " + canvas.getWidth() +", " + canvas.getHeight());
        DrawMap(canvas,BG_X1,BG_Y1,BG_X2,BG_Y2);
        Bitmap heart = BitmapFactory.decodeResource(getResources(),R.drawable.heart_good);
        Bitmap brokenHeart = BitmapFactory.decodeResource(getResources(),R.drawable.heart_bad);
        for (int i = -2; i < 3; i++){
            if(i < 3 - playy.GetHealth()){
                canvas.drawBitmap(brokenHeart,(BG_X1+BG_X2)/2-brokenHeart.getWidth()/2+brokenHeart.getWidth()*i,BG_Y2+1,p);
            }
            else canvas.drawBitmap(heart,(BG_X1+BG_X2)/2-heart.getWidth()/2+heart.getWidth()*i,BG_Y2+1,p);
        }
        DrawPlayer(canvas);


        if (fSeq.isEmpty()){
            lvl.MakeSeq1(canvas,BG_X1,BG_Y1,BG_X2,BG_Y2);
            successful++;
            totalSeq++;
            Log.i("I10",Integer.toString(fSeq.size()));
        }
        fSeq = lvl.GetSeq();

        if (Tarr.getLastHit() != totalSeq){bTarr = BitmapFactory.decodeResource(getResources(),R.drawable.target_normal);}
        DrawTarget(canvas);



        //canvas.drawBitmap(BG,BG_X1,BG_Y1,null);
        //Log.w("I10",Integer.toString(Seq.size()));
        for (int i = 0; i <fSeq.size(); i++){
           //Log.d("I6",Integer.toString(i) + " " + Integer.toString(Seq.size()));
            DrawProjectile(canvas, fSeq.get(i));
            if(fSeq.get(i).GetPosY()>BG_Y2 || fSeq.get(i).GetPosX()>BG_X2
            || fSeq.get(i).GetPosY()<BG_Y1 || fSeq.get(i).GetPosX()<BG_X1) {
                fSeq.remove(i);
                //Log.d("I8","RESET" + Integer.toString(i));
                i = 0;
            }
            else if(!fSeq.get(i).GetIsPassive()&&CheckCollision(fSeq.get(i))){
                for (int j = 0; j<fSeq.size();j++){fSeq.get(j).SetIsPassive(true);}
                playy.TakeHit();
                successful--;
                canvas.drawBitmap(brokenHeart,(BG_X1+BG_X2)/2-brokenHeart.getWidth()/2+brokenHeart.getWidth()*2,BG_Y2+1,p);
                if (playy.GetHealth() == 0) GameEnd(false);
            }
        }
        if (tb){
            DrawProjectile(canvas, tProj);
            if (tProj.GetPosY()<BG_Y1){
                tb=false;
                Log.d("xp", Integer.toString(tProj.GetPosX()));
                Log.d("x+p", Integer.toString(tProj.GetPosX()+Projjie.getWidth()));
                Log.d("tp", Integer.toString(Tarr.getX()));
                Log.d("t+p",Integer.toString(Tarr.getX()+Projjie.getWidth()));
                if (tProj.GetPosX()>Tarr.getX() && tProj.GetPosX()+Projjie.getWidth()<Tarr.getX()+bTarr.getWidth()) {
                    Tarr.takeHit(totalSeq);
                    if (Tarr.getHealth() == 0) {GameEnd(true);}
                    bTarr = BitmapFactory.decodeResource(getResources(),R.drawable.target_hit);
                    Log.w("Health---->>",Integer.toString(Tarr.getHealth()));
                }
            }
        }



        if(isRunning)invalidate();
    }

    public void DrawTarget(Canvas canvas){
        if (Tarr.getX() < BG_X1) Tarr.setDirection(1);
        if (Tarr.getX()+bTarr.getWidth() > BG_X2) Tarr.setDirection(-1);
        Tarr.setX(Tarr.getX()+5*Tarr.getDirection());
        canvas.drawBitmap(bTarr,Tarr.getX(),Tarr.getY(),p);
    }

    public void DrawProjectile(Canvas canvas, Projectile proj){
        //Log.d("I7",Double.toString(proj.GetPosX()));
        Bitmap ProjBM = BitmapFactory.decodeResource(getResources(),R.drawable.projtemplate);
        if (proj.GetPColor()) ProjBM = BitmapFactory.decodeResource(getResources(),R.drawable.projpass);
        canvas.drawBitmap(ProjBM, proj.GetPosX(),proj.GetPosY(), null);
        proj.Move();
        //try {
            //TimeUnit.MILLISECONDS.sleep(1);
        //} catch (InterruptedException e) {
            //throw new RuntimeException(e);
        //}
    }
    public void PlayerShoots(){
        if (!tb) {
            tb = true;
            Log.v("here!!!!!!!!",Boolean.toString(tb));
            Projectile projji = playy.Shoot();
            projji.SetPosX(BG_X1 + (BG_X2 - BG_X1) * (2 * (playy.GetPosX() - 1) + 1) / 14);
            projji.SetPosY(BG_Y1 + (BG_Y2 - BG_Y1) * (2 * (playy.GetPosY() - 1) + 1) / 14);
            tProj = projji;
        }
        else Log.e("no","already shooting");
    }
    public void DrawMap(Canvas canvas, int x1, int y1, int x2, int y2){
        canvas.drawRect(x1,y1,x2,y2,p);
        //Log.i("Isize",Integer.toString(x2-x1) + " " + Integer.toString(y2-y1));
        int SWidth = x2-x1;
        int SHeight = y2-y1;
        Paint p2 = new Paint();
        p2.setColor(Color.GRAY);
        for (int i = 0; i < 7; i++){
            for (int j = 0; j < 7; j+=2){
                if (i%2==1 && j == 0) j = 1;
                canvas.drawRect(x1+i*SWidth/7,y1+j*SHeight/7,x1+(i+1) * SWidth/7,y1+(j+1)*SHeight/7,p2);
            }
        }
    }
    public void DrawPlayer(Canvas canvas){
        Paint p2 = new Paint();
        p2.setColor(Color.GREEN);
        int x = BG_X1 + (BG_X2-BG_X1)*(2*(playy.GetPosX()-1)+1)/14;
        int y = BG_Y1 + (BG_Y2-BG_Y1)*(2*(playy.GetPosY()-1)+1)/14;
        canvas.drawCircle(x,y,25,p2);
    }

    public boolean CheckCollision(Projectile proj){
        int x1 = proj.GetPosX();
        int y1 = proj.GetPosY();
        int x2 = proj.GetPosX() + BitmapFactory.decodeResource(getResources(),R.drawable.projtemplate).getWidth();
        int y2 = proj.GetPosY() + BitmapFactory.decodeResource(getResources(),R.drawable.projtemplate).getHeight();
        for (int i = 0; i < 4; i++){
            int x = 0; int y = 0;
            if (i < 2) x = x1;
            else x = x2;
            if (i%2 == 0) y = y1;
            else y = y2;
            if (isInCircle(x,y,BG_X1 + (BG_X2-BG_X1)*(2*(playy.GetPosX()-1)+1)/14,BG_Y1 + (BG_Y2-BG_Y1)*(2*(playy.GetPosY()-1)+1)/14,25))
                return true;
        }

        return false;
    }
    public boolean isInCircle(int x, int y, int centerX, int centerY, int radius) {
        int dx = x - centerX;
        int dy = y - centerY;
        return dx * dx + dy * dy <= radius * radius;
    }

    public static Bitmap scaleBitmap(Resources resources, int imageResourceId,
                                     float topLeftX, float topLeftY, float bottomRightX, float bottomRightY) {

        // Calculate the width and height of the target rectangle
        float targetWidth = bottomRightX - topLeftX;
        float targetHeight = bottomRightY - topLeftY;

        // Get the original dimensions of the bitmap
        Bitmap originalBitmap = BitmapFactory.decodeResource(resources, imageResourceId);
        int originalWidth = originalBitmap.getWidth();
        int originalHeight = originalBitmap.getHeight();

        // Calculate scaling factors
        float scaleX = targetWidth / originalWidth;
        float scaleY = targetHeight / originalHeight;

        // Create a Matrix for scaling and translation
        Matrix matrix = new Matrix();
        matrix.postScale(scaleX, scaleY);
        matrix.postTranslate(topLeftX, topLeftY); // Move to the top-left corner

        // Create a new bitmap with the scaled dimensions
        Bitmap scaledBitmap = Bitmap.createBitmap((int) targetWidth, (int) targetHeight, Bitmap.Config.ARGB_8888);

        // Draw the original bitmap onto the scaled bitmap using the matrix
        Canvas canvas = new Canvas(scaledBitmap);
        canvas.drawBitmap(originalBitmap, matrix, null);

        // Return the scaled bitmap
        return scaledBitmap;
    }
    public int getSuccessful(){return successful;}
    public void GameEnd(boolean isGood){
        isRunning = false;
        MA.onDestroy();
        Log.v("here!!!!!!!!","GameEnd()");
    }


}
