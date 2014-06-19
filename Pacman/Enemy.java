public class Enemy
{
    private Vector position;
    private Vector direction;
    private int turns;

    public Enemy(Vector position)
    {
        this.position = position;
        this.direction = new Vector(0,0);
        this.turns = -1;
    }

    public Vector getPosition()
    {
        return position;
    }

    public void backward()
    {
        position.add(direction.opposite());
    }

    public void move(Player player,int counter)
    {
        kill(player);        
        decisionMaking(player.getPosition(),counter);
        if (Util.obstacle(position))
        {                  
            backward();
        }
        kill(player);
    }

    public double power(int player, int enemy)
    {
        return Math.pow(player - enemy,2);
    }

    public double root(double a,double b)
    {
        return Math.sqrt(a + b);
    }

    public void decisionMaking(Vector player,int number)
    {        
        int direct1;
        int counter = 0;
        Vector previousPosition = position.clone();
        int rightDirect = 0;
        int wrongDirect = 0;
        double[] direct = new double[4];
        Vector[] pos = new Vector[4];
        double smallest = Double.POSITIVE_INFINITY;
        double largest = Double.NEGATIVE_INFINITY;
        int previousDirect = 0;
        for (int i = 0; i < pos.length; i++)
        {
            pos[i] = position.clone();
            pos[i].add(Vector.DIRECTION[i]);
        }
        backward();
        for (int i = 0; i < pos.length; i++)
        {
            if (Util.parameters(pos[i].getX(),pos[i].getY()))
            {
                if (!Util.obstacle(pos[i]))
                {
                    if (!pos[i].equals(position))
                    {
                        direct[i] = root(power(player.getX(),pos[i].getX()),power(player.getY(),pos[i].getY()));
                        if (direct[i] < smallest)
                        {
                            smallest = direct[i];
                            rightDirect = i;                   
                        }
                        if (direct[i] > largest)
                        {
                            largest = direct[i];
                            wrongDirect = i;
                        }
                        counter++;
                    }
                    else
                    {
                        previousDirect = i;
                    }
                }
            }
        }
        if (counter > 1)
        {
            turns++;
        }
        if (turns % (number + 1) == 0)
        {
            direct1 = rightDirect;
        }
        else if (counter == 0)
        {
            direct1 = previousDirect;
        }
        else
        {
            direct1 = wrongDirect;
        }
        position = pos[direct1].clone();        
        pos[direct1].add(previousPosition.opposite());
        direction = pos[direct1];
    }

    private void kill(Player player)
    {
        player.die(position);
    }

    public String toString()
    {
        return Util.objectStr("Enemy", position, true);
    }
}
