package co.il.bulletdance;

class Player{
    private int id;
    private int health;
    private boolean isAlive;
    private int posX;
    private int posY;
    private int fireSpeed;

    public Player(int ID){
        this.id = ID;
        this.isAlive = true;
        this.posX = 3;
        this.posY = 3;
        this.fireSpeed = 1;
        this.health = 5;
    }

    public int GetHealth(){
        return this.health;
    }
    public boolean GetIsAlive() {
        return this.isAlive;
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
        if (IsDead()) this.isAlive = false;
    }
    public boolean IsDead(){
        return this.health<=0;
    }
    public void Shoot(){
        new Projectile(1,90,true,GetPosX(),GetPosY()).Move();
    }
}

