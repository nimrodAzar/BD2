package co.il.bulletdance;

import android.graphics.Canvas;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

class Level{
    private List<Projectile> sequence = new ArrayList<>();
    private List<Double> timer = new ArrayList<>();

    public Level(){}
    public void Shoot(Projectile proj, double time){
        Log.d("I4", Integer.toString(proj.GetSpeed()));
        this.sequence.add(proj);
        this.timer.add(time);
    }
    public List<Projectile> GetSeq(){
        //Log.d("I5", Integer.toString(this.sequence.size()));
        return this.sequence;}

    public void MakeSeq1(Canvas canvas){
        int bbl = (canvas.getWidth()-50);
        Random R = new Random();
        int seqNum = R.nextInt(4);
        Log.v("Ii", Integer.toString(seqNum));
        if (seqNum == 1){
            this.sequence.add(0,new Projectile(5,90,false,bbl*2/7,1));
            this.sequence.add(1,new Projectile(5,90,false,bbl*4/7,1));
            this.sequence.add(2,new Projectile(5,90,false,bbl*6/7,1));
        }
        if (seqNum == 2){
            this.sequence.add(0,new Projectile(5,90,false,bbl*1/7,1));
            this.sequence.add(1,new Projectile(5,90,false,bbl*3/7,1));
            this.sequence.add(2,new Projectile(5,90,false,bbl*5/7,1));
            this.sequence.add(3,new Projectile(5,90,false,bbl-25,1));
        }
        if (seqNum == 3){
            this.sequence.add(0, new Projectile(7,45,false,1,1));
            this.sequence.add(1, new Projectile(7,315,false,1,bbl-1));
            this.sequence.add(2, new Projectile(7,135,false,bbl-1,1));
            this.sequence.add(3, new Projectile(7,225,false,bbl-1,bbl-1));
        }

        Log.d("I9",Integer.toString(this.sequence.size()));
    }
}
