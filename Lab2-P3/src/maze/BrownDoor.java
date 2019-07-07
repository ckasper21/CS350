package maze;

import java.awt.Color;

public class BrownDoor extends Door
{
    private final Room[] rooms = new Room[2];
    private boolean open;
    public static final Color BROWN = new Color(102,51,0);

    public BrownDoor(Room r1, Room r2)
    {
        super(r1,r2);
    }

    @Override
    public Color getColor()
    {
        return BROWN;
    }
}