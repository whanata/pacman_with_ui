public class Player
{
    private Vector position;
    private int collectedDots;
    private boolean alive;
    private Vector oldPosition;
    private Vector direction;
    private Vector queuedDirection;
    public Player(Vector position)
    {
        this.position = position;
        this.collectedDots = 0;
        this.alive = true;
        this.direction = new Vector(0,0);
    }
    
    public Vector getQueuedDirection()
    {                
        if (Util.obstacle(position))
        {
            return queuedDirection;
        }
        return direction;       
    }
    
    public void previousMove(Vector direction)
    {
        oldPosition = position.clone();
        this.direction = direction;
    }
    
    public void obstacle()
    {
        position = oldPosition.clone();
    }
    
    public int getCollectedDots()
    {
        return collectedDots;
    }

    public void move(Vector direction)
    {
        previousMove(direction);
        position.add(direction);
        if (Util.obstacle(position))
        {
            obstacle();   
            position.add(queuedDirection); 
            if (Util.obstacle(position))
            {
                obstacle();
            }
        }
        else
        {
            queuedDirection = direction;
        }
    }

    public void collect(Dot dot)
    {
        collectedDots += dot.disappear(position);
    }

    public void die(Vector enemyPosition)
    {
        if (position.equals(enemyPosition))
        {
            alive = false;
        }
    }

    public void reach(Exit exit)
    {
        exit.reach(position);
    }

    public Vector getPosition()
    {
        return position;
    }

    public void finish()
    {
        System.out.println(alive ? "You win!" : "You lose!");
    }

    public boolean isAlive()
    {
        return alive;
    }

    public String toString()
    {
        return "Player[" + stars() + "]" + Util.coordsStr(position, alive);
    }

    private String stars()
    {
        String s = "";
        for (int i = 0; i < collectedDots; i++)
            s += "*";
        return s;
    }
}
