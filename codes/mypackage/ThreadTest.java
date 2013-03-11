package mypackage;

public class ThreadTest {
	public static void main(String[] args) {
		Object shareobj = new Object();
		TestThread_Locked thread1 = new TestThread_Locked(shareobj);
		thread1.start();

		TestThread_WaitingTo thread2 = new TestThread_WaitingTo(shareobj);
		thread2.start();

		TestThread_WaitingOn thread3 = new TestThread_WaitingOn();
		thread3.start();
	}
}
