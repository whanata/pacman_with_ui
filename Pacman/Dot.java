public class Dot
{
    private Vector position;
    private boolean exists;

    public Dot(Vector position)
    {
        exists = true;
        this.position = position;
    }

    public Vector getPosition()
    {
        return position;
    }

    public boolean exists()
    {
        return exists;
    }
    
    public int disappear(Vector position)
    {
        if (position.equals(this.position) && exists)
        {
            exists = false;
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public String toString()
    {
        return Util.objectStr("Dot", position, exists);
    }
}
