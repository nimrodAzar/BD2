package co.il.bulletdance;

public class Target {
    private int x;
    private int y;
    private boolean isHit;
    private int lastHit;
    private int health;
    private int direction;
    public Target(int h){
        this.health = h;
        this.direction = 0;
        this.isHit = false;
        this.lastHit = 0;
    }
    public void setMove(boolean b){
        this.isHit = b;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public int getDirection(){
        return this.direction;
    }
    public void setDirection(int d){
        this.direction = d;
    }
    public int getHealth(){
        return this.health;
    }
    public void takeHit(int n){
        this.health--;
        this.lastHit = n;
    }
    public boolean getIsHit(){
        return this.isHit;
    }
    public int getLastHit(){
        return this.lastHit;
    }
}
