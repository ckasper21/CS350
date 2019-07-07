package maze;

public class RedMazeGameCreator extends MazeGameCreator
{
    public RedMazeGameCreator() {}

    public Wall makeWall()
    {
        return new RedWall();
    }

    public Room makeRoom(int num)
    {
        return new RedRoom(num);
    }
}

