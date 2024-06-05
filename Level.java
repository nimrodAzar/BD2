package co.il.bulletdance;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

class Level{
    private List<Projectile> sequence;
    private List<Double> timer;

    public Level(){}
    public void Shoot(Projectile proj, double time){
        this.sequence.add(proj);
        this.timer.add(time);
    }
    public List<Projectile> GetSeq(){return this.sequence;}
}
