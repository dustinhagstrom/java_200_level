
public class QuizOne {

//look at Clock and ClockDriver files for code snippets within quizOne

	static double log2(double a) {
		return Math.log10(a) * ((Math.log10(10)) / (Math.log10(2)));
	}

	public static void main(String[] args) {
		System.out.println(log2(1.0));
		System.out.println(Math.log10((Double.MAX_VALUE + Double.MAX_VALUE)));
		System.out.println(Double.NaN);
	}
}
