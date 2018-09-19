public class Point {
	
	private int x;
	private int y;
	
	public static final int INIT_X = 0;
	public static final int INIT_Y = 0;
	
	public Point() {
		this(INIT_X, INIT_Y);
	}
	
	public Point(int abs, int ord) {
		x = abs;
		y = ord;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
@Override
	public String toString() {
		return "[" + getX() + ";" + getY() + "]";
	}
	
@Override
	public boolean equals(Object obj) {
		if(obj instanceof Point) {
			Point point = (Point) obj;
			return ((this.x == point.x) && (this.y == point.y));
		} else {
			return false;
		}
	}

	public Point clone() {
		return new Point(x, y);
	}

	public double distance(Point p) {
		int dx = p.getX() - this.getX();
		int dy = p.getX() - this.getY();
		int dx2 = dx * dx;
		int dy2 = dy * dy;
		return Math.sqrt(dx2 + dy2);
	}

}