/*
 * SimpleMazeGame.java
 * Copyright (c) 2008, Drexel University.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Drexel University nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY DREXEL UNIVERSITY ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL DREXEL UNIVERSITY BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package maze;

import maze.ui.MazeViewer;
import java.util.*;
import java.io.*;

/**
 * 
 * @author Sunny
 * @version 1.0
 * @since 1.0
 */
public class SimpleMazeGame
{
	/**
	 * Creates a small maze.
	 */
	public static Maze createMaze()
	{

		Maze maze = new Maze();
		System.out.println("The maze does not have any rooms yet!");

		// Create rooms
		Room r0 = new Room(0);
		Room r1 = new Room(1);

		// Create door
		Door d0 = new Door(r0,r1);
		d0.setOpen(true);

		// Set sides of rooms (room 1 will be above room 0)
		r0.setSide(Direction.North, d0);
		r0.setSide(Direction.South, new Wall());
		r0.setSide(Direction.East, new Wall());
		r0.setSide(Direction.West, new Wall());

		r1.setSide(Direction.North, new Wall());
		r1.setSide(Direction.South, d0);
		r1.setSide(Direction.East, new Wall());
		r1.setSide(Direction.West, new Wall());

		// Add rooms to maze, set current room
		maze.addRoom(r0);
		maze.addRoom(r1);
		maze.setCurrentRoom(0);
		return maze;
	}

	public static Maze loadMaze(final String path)
	{
		Maze maze = new Maze();

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
				roomsObjList.put(key,new Room(Integer.parseInt(key)));
				maze.addRoom(roomsObjList.get(key));
			}

			// Create doors
			for (String key: doorsList.keySet()){
				Room r0 = roomsObjList.get(doorsList.get(key)[0]);
				Room r1 = roomsObjList.get(doorsList.get(key)[1]);
				doorsObjList.put(key, new Door(r0,r1));

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
						mp = new Wall();
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


	//	Maze maze = new Maze();
	//	System.out.println("Please load a maze from the file!");
		return maze;
	}

	public static void main(String[] args)
	{
		if (args.length > 0) {
			Maze maze = loadMaze(args[0]);
			MazeViewer viewer = new MazeViewer(maze);
			viewer.run();
		} else {
			Maze maze = createMaze();
			MazeViewer viewer = new MazeViewer(maze);
			viewer.run();
		}
	}
}
