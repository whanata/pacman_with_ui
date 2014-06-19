public class Game
{
    private Player player;
    private Dot[] dots;
    private Exit[] exit;
    private Enemy[] enemy;
    private Screen screen;
    private Wall[] wall;
    private static int xLimit;
    private static int yLimit;

    public Game()
    {        
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;
        dots = new Dot[count('.')];
        enemy = new Enemy[count('e')];
        exit = new Exit[count('x')];
        wall = new Wall[count('#')];
        xLimit = Util.xLimit();
        yLimit = Util.yLimit();
        screen = new Screen(xLimit,yLimit);
        for (int row = 0; row < yLimit; row++)
        {            
            for (int col = 0; col < xLimit; col++)
            {                
                Vector pos = new Vector(col, row);
                char state = Util.at(pos);
                screen.setPixel(pos,state);
                switch (state)
                {
                    case 'x': exit[i] = new Exit(pos);
                        i++;
                        break;
                    case '.': dots[j] = new Dot(pos);
                        j++;
                        break;
                    case 'e': enemy[k] = new Enemy(pos);
                        k++;
                        break;
                    case 'p': player = new Player(pos);
                        break;
                    case '#': screen.setPixel(pos,'\u25AF');
                        wall[l] = new Wall(pos);
                        l++;
                        break;
                    default: break;
                }
            }
        }
    }   

    public void start()
    {
        do
        {
            display();
            input();
        }
        while (!over());
        display();
        player.finish();
    }

    public void reprint()
    {
        for (int j = 0; j < wall.length; j++)
        {
            screen.setPixel(wall[j].getPosition(),'\u25AF');
        }
        for (int i = 0; i < exit.length; i++)
        {
            char state;
            if (exit[i].isOpen())
            {            
                state = 'o';          
            }
            else
            {
                state = 'x';
            }
            screen.setPixel(exit[i].getPosition(),state); 
        }
        for (int i = 0; i < dots.length; i++)
        {
            if (dots[i].exists())
            {
                screen.setPixel(dots[i].getPosition(),'.'); 
            }
        }
        for (int j = 0; j < enemy.length; j++)
        {
            screen.setPixel(enemy[j].getPosition(),'e');            
        }
        screen.setPixel(player.getPosition(),'p');
    }

    public void display()
    {        
        screen.clear();
        reprint();
        screen.print();
    }        

    public boolean over()
    {
        for (int i = 0; i < exit.length; i++)
        {
            if (exit[i].isReached() || !player.isAlive())
            {
                return true;
            }
        }
        return false;
    }

    public void input()
    {
        move(readMove());
    }

    private Vector readMove()
    {
        switch (Util.readChar("Move (l/r/u/d): "))
        {
            case 'l': return Vector.DIRECTION[0];
            case 'u': return Vector.DIRECTION[1];
            case 'r': return Vector.DIRECTION[2];            
            case 'd': return Vector.DIRECTION[3];
            default: return player.getQueuedDirection();           
        }
    }

    public void move(Vector direction)
    {
        player.move(direction);
        for (int i = 0; i < dots.length; i++)
        {
            player.collect(dots[i]);
        }
        for (int i = 0; i < exit.length; i++)
        {
            if (player.getCollectedDots() == dots.length)
            {
                exit[i].open();            
            }
            player.reach(exit[i]);
        }
        for (int i = 0; i < enemy.length; i++)
        {
            enemy[i].move(player,i); 
        }
    }

    public static int count(char c)
    {
        int count = 0;
        for (int row = 0; row < Layout.ROWS.length; row++)
        {
            for (int col = 0; col < Layout.ROWS[row].length(); col++)
            {
                if (Layout.ROWS[row].charAt(col) == c)
                {
                    count++;
                }
            }
        }
        return count;
    }

    public String toString()
    {
        String multiple = player.toString();
        for (int i = 0; i < dots.length; i++)
        {
            multiple += (" " + dots[i]);
        }
        for (int i = 0; i < exit.length; i++)
        {
            multiple += (" " + exit[i]);
        }
        for (int i = 0; i < enemy.length; i++)
        {
            multiple += (" " + enemy[i]);
        }        
        return multiple;
    }
}
