package co.il.bulletdance;

class Projectile{
    private int speed;
    private double angle;
    private boolean isPassive;
    private int posX;
    private int posY;
    private boolean pColor;

    public Projectile(int vel, double ang, boolean IsP, int x, int y){
        this.speed = vel;
        this.angle = ang;
        this.isPassive = IsP;
        this.posX = x;
        this.posY = y;
        this.pColor = IsP;
    }

    public boolean GetPColor(){return this.pColor;}
    public int GetSpeed(){return this.speed;}
    public double GetAngle(){return this.angle;}
    public boolean GetIsPassive(){return this.isPassive;}
    public int GetPosX(){return this.posX;}
    public int GetPosY(){return this.posY;}
    public void SetSpeed(int val){this.speed = val;}
    public void SetAngle(double val){this.angle = val;}
    public void SetIsPassive(boolean val){this.isPassive = val;}
    public void SetPosX(int val){this.posX = val;}
    public void SetPosY(int val){this.posY = val;}
    public void Move(){
        SetPosX(GetPosX() + (int)(GetSpeed()*Math.cos(Math.toRadians(GetAngle()))));
        SetPosY(GetPosY() + (int)(GetSpeed()*Math.sin(Math.toRadians(GetAngle()))));
    }
}
