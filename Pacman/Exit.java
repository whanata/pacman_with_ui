public class Exit
{
    private boolean open;
    private boolean reached;
    private Vector position;

    public Exit(Vector position)
    {
        this.open = false;
        this.reached = false;
        this.position = position;
    }

    public Vector getPosition()
    {
        return position;
    }

    public boolean isOpen()
    {
        return open;
    }

    public boolean isReached()
    {
        return reached;
    }

    public void open()
    {
        open = true;
    }

    public void reach(Vector position)
    {
        if (open && position.equals(this.position))
        {
            reached = true;
        }
    }

    public String toString()
    {
        return Util.objectStr("Exit", position, !open);
    }
}
