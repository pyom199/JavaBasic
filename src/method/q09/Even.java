package method.q09;

public class Even {
	public static void main(String[] args) {
		int num = 5;
		boolean isEven = checkEven(num);
		if (isEven) {
			System.out.println(num + "は偶数です。");
		} else {
			System.out.println(num + "は奇数です。");
		}
	}

	public static boolean checkEven(int number) {
		return number % 2 == 0;
	}
}