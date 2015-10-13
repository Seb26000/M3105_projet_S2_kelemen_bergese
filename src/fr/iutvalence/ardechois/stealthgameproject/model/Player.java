package fr.iutvalence.ardechois.stealthgameproject.model;

import fr.iutvalence.ardechois.stealthgameproject.exceptions.InvalidPositionException;
import fr.iutvalence.ardechois.stealthgameproject.view.PlayerGetter;

/**
 * Player that is playing the game.
 * 
 * @author kelemenn
 *
 */
public class Player implements PlayerGetter
{
	/**
	 * The character position
	 * 
	 * @see Position
	 */
	private Position position;

	/**
	 * Constructor whit a position parameter.
	 * 
	 * @param position
	 *            :
	 * 
	 */
	public Player(Position position)
	{
		this.position = new Position(position.getXPosition(), position.getYPosition());
	}

	/**
	 * Getter for the attribute position.
	 * 
	 * @return position : The current character position.
	 */
	public Position getPosition()
	{
		return this.position;
	}

	/**
	 * Move the character to the neighbor square.
	 * 
	 * @param direction
	 * @param map
	 * @throws InvalidPositionException
	 */
	public void move(Direction direction, Map map) throws InvalidPositionException
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
		
		Blocks nextBlock = map.getBlock(new Position(this.position.getXPosition() + direction.getX(), this.position.getYPosition()+direction.getY()));
		if(nextBlock != Blocks.WALL && nextBlock != Blocks.WATER)
			this.position.move(direction);
	}
}
