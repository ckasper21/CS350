package maze;

public class RedMazeFactory extends MazeFactory {

    public Wall makeWall()
    {
        return new RedWall();
    }

    public Room makeRoom(int num)
    {
        return new RedRoom(num);
    }
}
