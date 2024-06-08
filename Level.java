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

    public void MakeSeq1(Canvas canvas, int x1, int y1, int x2, int y2){
        Random R = new Random();
        int seqNum = R.nextInt(7);
        Log.v("Ii", Integer.toString(seqNum));
        if (seqNum == 1){
            this.sequence.add(0,new Projectile(5,90,false,x1 +(x2 - x1)*3/14 - 12,y1));
            this.sequence.add(1,new Projectile(5,90,false,x1 + (x2 - x1)*7/14 - 12,y1));
            this.sequence.add(2,new Projectile(5,90,false,x1 + (x2 - x1)*11/14 - 12,y1));
        }
        if (seqNum == 2){
            this.sequence.add(0,new Projectile(5,90,false,x1 + (x2 - x1)*1/14 - 12,y1));
            this.sequence.add(1,new Projectile(5,90,false,x1 + (x2 - x1)*5/14 - 12,y1));
            this.sequence.add(2,new Projectile(5,90,false,x1 + (x2 - x1)*9/14 - 12,y1));
            this.sequence.add(3,new Projectile(5,90,false,x1 + (x2 - x1)*13/14 - 12,y1));
        }
        if (seqNum == 3){
            this.sequence.add(0, new Projectile(7,45,false,x1,y1));
            this.sequence.add(1, new Projectile(7,315,false,x1,y2-24));
            this.sequence.add(2, new Projectile(7,135,false,x2-24,y1));
            this.sequence.add(3, new Projectile(7,225,false,x2-24,y2-24));
        }
        if (seqNum == 4){
            this.sequence.add(0,new Projectile(4,0,false,x1,y1 + (y2 - y1)*3/14 - 12));
            this.sequence.add(1,new Projectile(4,0,false,x1,y1 + (y2 - y1)*7/14 - 12));
            this.sequence.add(2,new Projectile(4,0,false,x1,y1 + (y2 - y1)*11/14 - 12));
            this.sequence.add(3,new Projectile(4,180,false,x2-24,y1 + (y2 - y1)*1/14 - 12));
            this.sequence.add(4,new Projectile(4,180,false,x2-24,y1 + (y2 - y1)*5/14 - 12));
            this.sequence.add(5,new Projectile(4,180,false,x2-24,y1 + (y2 - y1)*9/14 - 12));
            this.sequence.add(6,new Projectile(4,180,false,x2-24,y1 + (y2 - y1)*13/14 - 12));
        }
        if (seqNum == 5){
            int freelane = R.nextInt(7);
            int counter = 0;
            for( int i = 0; i < 7; i++){
                if(i != freelane){
                    this.sequence.add(counter,new Projectile(5,90,false,x1 + (x2 - x1)*(2*i+1)/14 - 12,y1));
                    counter++;
                }
            }
        }
        if (seqNum == 6){
            int freelane = R.nextInt(7);
            int counter = 0;
            for( int i = 0; i < 7; i++){
                if(i != freelane){
                    this.sequence.add(counter,new Projectile(5,0,false,x1,y1 + (y2 - y1)*(2*i+1)/14 - 12));
                    counter++;
                }
            }
        }

        Log.d("I9",Integer.toString(this.sequence.size()));
    }
}
