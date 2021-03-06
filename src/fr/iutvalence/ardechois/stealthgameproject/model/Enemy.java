package fr.iutvalence.ardechois.stealthgameproject.model;

import java.util.Random;

import fr.iutvalence.ardechois.stealthgameproject.exceptions.InvalidPositionException;

/**
 * Represent an enemy.
 * 
 * @author kelemenn
 * 
 * @version 0.1.0
 */
public class Enemy
{
	/**
	 * The enemy position.
	 * 
	 * @see Position
	 */
	private Position position;
	
	/**
	 * The enemy vision field.
	 * 
	 * @see VisionField
	 */
	private VisionField visionField;
	
	/**
	 * True if the enemy see the target.
	 */
	private boolean see;
	
	/**
	 * Constructor with a position parameter.
	 * 
	 * @param position
	 *            : The starting enemy position.
	 */
	public Enemy(Position position, Direction initDir)
	{
		this.position = position;
		this.visionField = new VisionField(position, initDir);
		this.see = false;
	}
	

	/**
	 * Getter for the attribute position.
	 * 
	 * @return position : The current enemy position.
	 */
	public Position getPosition()
	{
		return this.position;
	}

	/**
	 * Move the enemy to the neighbor square.
	 * 
	 * @param direction
	 * @param map
	 * @throws InvalidPositionException
	 */
	public void move(Direction direction, Map map) throws InvalidPositionException
	{
		checkPositionValidity(direction, map);
		
		int yPosition = this.position.getYPosition();
		int xPosition = this.position.getXPosition();
		if(map.getBlock(new Position(xPosition + direction.getX(), yPosition+direction.getY())) != Blocks.WALL && map.getBlock(new Position(xPosition + direction.getX(), yPosition+direction.getY())) != Blocks.WATER)
			this.position.move(direction);
		
		this.visionField.update(direction);
	}


	private void checkPositionValidity(Direction direction, Map map) throws InvalidPositionException 
	{
		switch (direction)
		{
		case UP:
			if (this.position.getYPosition() - 1 < 0)
				throw new InvalidPositionException();
			break;
		case DOWN:
			if (this.position.getYPosition() + 1 >= map.getMapHeight())
				throw new InvalidPositionException();
			break;
		case LEFT:
			if (this.position.getXPosition() - 1 < 0)
				throw new InvalidPositionException();
			break;
		case RIGHT:
			if (this.position.getXPosition() + 1 >= map.getMapWidth())
				throw new InvalidPositionException();
			break;
		}
	}
	
	
	/**
	 * Getter for the boolean see.
	 * 
	 * @return see
	 * 			The boolean see.
	 */
	public boolean getSee()
	{
		return this.see;
	}
	
	/**
	 * Check if the player is seen.
	 * @param player
	 * @param map
	 * @return
	 */
	public boolean checkVisionField(Player player)
	{
		this.see =  this.visionField.check(player);
		return getSee();
	}
	
	/**
	 * Move the enemy in a random direction.
	 * @param map
	 */
	public void randomMove(Map map)
	{
		Random randomMoveNumber = new Random();
		
		try
		{
			move(Direction.getDirection(randomMoveNumber.nextInt(4)), map);
		} catch (InvalidPositionException e)
		{
			// Do nothing
		}
	}

}
