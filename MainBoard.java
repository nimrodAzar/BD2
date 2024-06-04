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
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        canvas.drawBitmap(BG,25,100,null);
    }
}
