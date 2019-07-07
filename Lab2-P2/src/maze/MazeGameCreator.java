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
//COMMENT HERE
public class MazeGameCreator
{
	/**
	 * Creates a small maze.
	 */
	public Maze createMaze()
	{

		Maze maze = makeMaze();
		System.out.println("The maze does not have any rooms yet!");

		// Create rooms
		Room r0 = makeRoom(0);
		Room r1 = makeRoom(1);
		Room r2 = makeRoom(2);

		// Create door
		Door d0 = makeDoor(r0,r1);
		d0.setOpen(false);

		// Set sides of rooms (room 1 will be above room 0)
		r0.setSide(Direction.North, d0);
		r0.setSide(Direction.South, makeWall());
		r0.setSide(Direction.East, makeWall());
		r0.setSide(Direction.West, makeWall());

		r1.setSide(Direction.North, makeWall());
		r1.setSide(Direction.South, d0);
		r1.setSide(Direction.East, r2);
		r1.setSide(Direction.West, makeWall());

		r2.setSide(Direction.North, makeWall());
		r2.setSide(Direction.South, makeWall());
		r2.setSide(Direction.East, makeWall());
		r2.setSide(Direction.West, r1);

		// Add rooms to maze, set current room
		maze.addRoom(r0);
		maze.addRoom(r1);
		maze.addRoom(r2);

		maze.setCurrentRoom(0);
		return maze;
	}

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

	public static void main(String[] args)
	{
		if (args.length > 0) {
			if (args[0].equals("red")) {
				RedMazeGameCreator redMazeCreator = new RedMazeGameCreator();
				Maze redMaze = redMazeCreator.createMaze();
				MazeViewer viewer = new MazeViewer(redMaze);
				viewer.run();

			} else if (args[0].equals("blue")) {
				BlueMazeGameCreator blueMazeCreator = new BlueMazeGameCreator();
				Maze blueMaze = blueMazeCreator.createMaze();
				MazeViewer viewer = new MazeViewer(blueMaze);
				viewer.run();

			} else if (args[0].equals("basic")) {
				MazeGameCreator basicMazeCreator = new MazeGameCreator();
				Maze maze = basicMazeCreator.createMaze();
				MazeViewer viewer = new MazeViewer(maze);
				viewer.run();
			}

		} else {
			System.out.println("Maze type not specified");
			System.exit(0);
		}

	}
}
