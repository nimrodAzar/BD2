package co.il.bulletdance;

import java.util.List;
class Level{
    private List<Projectile> sequence;
    private List<Double> timer;

    public Level(Projectile firstProj){}
    public void Shoot(Projectile proj, double time){
        this.sequence.add(proj);
        this.timer.add(time);
    }
}
