package fr.iutvalence.ardechois.stealthgameproject.model;


/**
 * Position of an element.
 * 
 * @author vanbossm
 *
 */
public class Position
{

	/** X coordinate position. */
	private int xPosition;

	/** Y coordinate position. */
	private int yPosition;

	/**
	 * Constructor of Position.
	 * 
	 * @param p_xPosition
	 *            : position x
	 * @param p_yPosition
	 *            : position y
	 */
	public Position(int p_xPosition, int p_yPosition)
	{
		this.xPosition = p_xPosition;
		this.yPosition = p_yPosition;
	}

	/**
	 * Getter Position x.
	 * 
	 * @return positionX
	 */
	public int getX()
	{
		return xPosition;
	}

	/**
	 * Getter Position y.
	 * 
	 * @return positionY
	 */
	public int getY()
	{
		return yPosition;
	}

	/**
	 * Move with x and y deltas.
	 * @param x
	 * @param y
	 */
	public void move(int x, int y)
	{
		setPosition(this.xPosition + x, this.yPosition + y);
	}
	
	/**
	 * Move the position with given direction.
	 * @param direction
	 */
	public void move(Direction direction)
	{
		setPosition(this.xPosition + direction.getX(), this.yPosition + direction.getY());
	}

	/**
	 * Setter for the Position.
	 * @param x
	 * @param y
	 */
	public void setPosition(int x, int y)
	{
		this.xPosition = x;
		this.yPosition = y;
	}
	
	@Override
	public boolean equals(Object position)
	{
		if (this.xPosition == ((Position) position).getX() && this.yPosition == ((Position) position).getY())
			return true;
		return false;
	}
}
