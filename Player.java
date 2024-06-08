package co.il.bulletdance;

class Player{
    private int id;
    private int health;
    private int posX;
    private int posY;
    private int fireSpeed;

    public Player(int ID){
        this.id = ID;
        this.posX = 4;
        this.posY = 5;
        this.fireSpeed = 1;
        this.health = 5;
    }

    public int GetHealth(){
        return this.health;
    }
    public int GetId(){return this.id;}
    public int GetPosX(){
        return this.posX;
    }
    public int GetPosY(){
        return this.posY;
    }
    public void SetPosX(int x){
        this.posX = x;
    }
    public void SetPosY(int y){
        this.posY = y;
    }
    public void Move(double angle){
        SetPosX((int)(Math.cos(angle)));
        SetPosY((int)(Math.sin(angle)));
    }
    public void TakeHit(){
        this.health--;
    }
    public boolean IsDead(){
        return this.health<=0;
    }
    public Projectile Shoot(){
        return new Projectile(10,270,true,GetPosX(),GetPosY());
    }
}

