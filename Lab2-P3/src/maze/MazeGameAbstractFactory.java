package maze;

import java.io.*;
import java.util.*;
import maze.ui.MazeViewer;

public class MazeGameAbstractFactory {

    public static Maze createMaze(MazeFactory mf)
    {

        Maze maze = mf.makeMaze();
        System.out.println("The maze does not have any rooms yet!");

        // Create rooms
        Room r0 = mf.makeRoom(0);
        Room r1 = mf.makeRoom(1);
        Room r2 = mf.makeRoom(2);

        // Create door
        Door d0 = mf.makeDoor(r0,r1);
        d0.setOpen(false);

        // Set sides of rooms (room 1 will be above room 0)
        r0.setSide(Direction.North, d0);
        r0.setSide(Direction.South,mf.makeWall());
        r0.setSide(Direction.East, mf.makeWall());
        r0.setSide(Direction.West, mf.makeWall());

        r1.setSide(Direction.North, mf.makeWall());
        r1.setSide(Direction.South, d0);
        r1.setSide(Direction.East, r2);
        r1.setSide(Direction.West, mf.makeWall());

        r2.setSide(Direction.North, mf.makeWall());
        r2.setSide(Direction.South, mf.makeWall());
        r2.setSide(Direction.East, mf.makeWall());
        r2.setSide(Direction.West, r1);

        // Add rooms to maze, set current room
        maze.addRoom(r0);
        maze.addRoom(r1);
        maze.addRoom(r2);

        maze.setCurrentRoom(0);
        return maze;
    }

    public static Maze loadMaze(final MazeFactory mf, final String path)
    {
        Maze maze = mf.makeMaze();

        FileReader file;
        BufferedReader reader;
        HashMap<String,String[]> roomsList = new HashMap<>();
        HashMap<String,String[]> doorsList = new HashMap<>();
        HashMap<String,Room> roomsObjList = new HashMap<>();
        HashMap<String,Door> doorsObjList = new HashMap<>();

        try {
            file = new FileReader(path);
            reader = new BufferedReader(file);

            String line = reader.readLine();

            while (line!=null) {
                String line_split[] = line.split(" ");
                if (line_split[0].equals("room")) {
                    roomsList.put(line_split[1],Arrays.copyOfRange(line_split,2,6));
                } else if (line_split[0].equals("door")) {
                    doorsList.put(line_split[1],Arrays.copyOfRange(line_split,2,5));
                }

                line = reader.readLine();
            }

            // Create and add rooms to maze
            for (String key: roomsList.keySet()){
                roomsObjList.put(key,mf.makeRoom(Integer.parseInt(key)));
                maze.addRoom(roomsObjList.get(key));
            }

            // Create doors
            for (String key: doorsList.keySet()){
                Room r0 = roomsObjList.get(doorsList.get(key)[0]);
                Room r1 = roomsObjList.get(doorsList.get(key)[1]);
                doorsObjList.put(key, mf.makeDoor(r0,r1));

                if (doorsList.get(key)[2].equals("open")) {
                    doorsObjList.get(key).setOpen(true);
                } else {
                    doorsObjList.get(key).setOpen(false);
                }
            }

            // Set walls in rooms
            for (String key: roomsList.keySet()) {
                Room r = roomsObjList.get(key);

                // 0=North,1=South,2=East,3=West
                int directionIdx = 0;

                for (String s: roomsList.get(key)) {
                    MapSite mp = null;

                    if (s.equals("wall")) {
                        mp = mf.makeWall();
                    } else if (roomsObjList.containsKey(s)) {
                        mp = roomsObjList.get(s);
                    } else if (doorsObjList.containsKey(s)) {
                        mp = doorsObjList.get(s);
                    }

                    switch (directionIdx) {
                        case 0:
                            r.setSide(Direction.North, mp);
                            break;
                        case 1:
                            r.setSide(Direction.South, mp);
                            break;
                        case 2:
                            r.setSide(Direction.East, mp);
                            break;
                        case 3:
                            r.setSide(Direction.West, mp);
                            break;
                    }
                    directionIdx++;
                }
            }
            maze.setCurrentRoom(0);

        } catch (IOException x) {
            System.out.println(x);
        }

        return maze;
    }

    public static void main(String[] args)
    {
        if (args.length > 0) {
            // Only  a color/type was specified
            if (args.length == 1) {
                if (args[0].equals("red")) {
                    RedMazeFactory rmf = new RedMazeFactory();
                    Maze maze = createMaze(rmf);
                    MazeViewer viewer = new MazeViewer(maze);
                    viewer.run();
                } else if (args[0].equals("blue")) {
                    BlueMazeFactory bmf = new BlueMazeFactory();
                    Maze maze = createMaze(bmf);
                    MazeViewer viewer = new MazeViewer(maze);
                    viewer.run();
                } else if (args[0].equals("basic")) {
                    MazeFactory mf = new MazeFactory();
                    Maze maze = createMaze(mf);
                    MazeViewer viewer = new MazeViewer(maze);
                    viewer.run();
                }
            } else {
                if (args[0].equals("red")) {
                    RedMazeFactory rmf = new RedMazeFactory();
                    Maze maze = loadMaze(rmf, args[1]);
                    MazeViewer viewer = new MazeViewer(maze);
                    viewer.run();
                } else if (args[0].equals("blue")) {
                    MazeFactory bmf = new BlueMazeFactory();
                    Maze maze = loadMaze(bmf, args[1]);
                    MazeViewer viewer = new MazeViewer(maze);
                    viewer.run();
                } else if (args[0].equals("basic")) {
                    MazeFactory mf = new MazeFactory();
                    Maze maze = loadMaze(mf, args[1]);
                    MazeViewer viewer = new MazeViewer(maze);
                    viewer.run();
                }
            }

        } else {
            System.out.println("Maze type not specified");
            System.exit(0);
        }

    }
}
