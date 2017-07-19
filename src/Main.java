
public class Main {

	public static void main(String[] args) {

		MainApp reader = new MainApp();
		Thread t = new Thread((Runnable) reader);
		t.start();
	}
	
}
