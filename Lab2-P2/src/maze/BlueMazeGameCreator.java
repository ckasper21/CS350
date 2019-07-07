package maze;

public class BlueMazeGameCreator extends MazeGameCreator
{
    public BlueMazeGameCreator() {}

    public Wall makeWall()
    {
        return new BlueWall();
    }

    public Room makeRoom(int num)
    {
        return new GreenRoom(num);
    }

    public Door makeDoor(Room r1, Room r2)
    {
        return new BrownDoor(r1,r2);
    }

}

