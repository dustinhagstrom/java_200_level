
public class ClockDriver {
	
	public static void printClock(Clock c) {
		System.out.println(c.getHour());
		System.out.println(c.getMinute());
		System.out.println(c.getSecond());
		System.out.println(c.getIsAM());
	}
	
	public static void main(String[] args) {
		Clock c = new Clock();
		Clock copyC = new Clock(c);
		
		System.out.println(c.equals(copyC));
		
		printClock(copyC);

	}

}
