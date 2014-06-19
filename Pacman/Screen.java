public class Screen
{
    private char[][] pixels;

    public Screen(int width, int height)
    {
        pixels = new char[height][width];
    }
    
    public char at(Vector position)
    {
        int x = position.getX();
        int y = position.getY();
        return pixels[y][x];
    }

    public void clear()
    {
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < pixels[row].length; col++)
            {
                pixels[row][col] = ' ';
            }
        }
    }

    public void setPixel(int x, int y, char c)
    {
        if (y >= 0 && y < pixels.length && x >= 0 && x < pixels[y].length)
        {
            pixels[y][x] = c;
        }
    }

    public void setPixel(Vector position, char c)
    {
        setPixel(position.getX(), position.getY(), c);
    }

    public void print()
    {
        for (int row = 0; row < pixels.length; row++)
        {
            for (int col = 0; col < pixels[row].length; col++)
            {
                System.out.print(pixels[row][col] + " ");
            }
            System.out.println();
        }
    }
}
