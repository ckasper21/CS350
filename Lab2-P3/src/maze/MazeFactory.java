package maze;

public class MazeFactory {
    public Maze makeMaze()
    {
        return new Maze();
    }

    public Wall makeWall()
    {
        return new Wall();
    }

    public Door makeDoor(Room r1, Room r2)
    {
        return new Door(r1,r2);
    }

    public Room makeRoom(int num)
    {
        return new Room(num);
    }
}
