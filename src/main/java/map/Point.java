package map;

public class Point {
	
	private int x;
	private int y;
	private boolean fortPresent;
	
	public Point(int x, int y, boolean fortPresent) {
		this.x = x;
		this.y = y;
		this.fortPresent=fortPresent;
	}
	
	public Point() {
		x=0;
		y=0;
		fortPresent = false;
	}
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public boolean getFortPresent() {
		return fortPresent;
	}
	
	public void setFortPresent(boolean fortPresent) {
		this.fortPresent = fortPresent;
	}
}
