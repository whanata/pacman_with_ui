import java.util.*;

public class Util
{
    public static final Scanner keyboard = new Scanner(System.in);

    public static String coordsStr(Vector p, boolean show)
    {
        return show ? p.toString() : "(-,-)";
    }

    public static String objectStr(String name, Vector p, boolean show)
    {
        return name + coordsStr(p, show);
    }

    public static char readChar(String prompt)
    {
        System.out.print(prompt);
        String line = keyboard.nextLine();
        if (line.isEmpty())
            return '\0';
        else
            return line.charAt(0);
    }

    public static char at(Vector position)
    {
        return Layout.ROWS[position.getY()].charAt(position.getX());
    }
    
    public static boolean obstacle(Vector position)
    {
        return Util.at(position) == '#';
    }
    
    public static boolean between(int x,int y)
    {
        return x >= 0 && y >=0;
    }
    
    public static boolean parameters(int x,int y)
    {
        return between(x,y) && x < Layout.ROWS[0].length() && y < Layout.ROWS.length;
    }
    
    public static int xLimit()
    {
        return Layout.ROWS[0].length();
    }
    
    public static int yLimit()
    {
        return Layout.ROWS.length;
    }
    
    public static int readInt(String prompt)
    {
        System.out.print(prompt);
        int i = keyboard.nextInt();
        keyboard.nextLine();
        return i;
    }
}
