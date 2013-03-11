package mypackage;

public class TestThread_WaitingOn extends Thread {
	Object lockobj1 = new Object();
	public TestThread_WaitingOn() {
		this.setName(this.getClass().getName());
	}
	@Override
	public void run() {
		fun();
	}
	public void fun() {
		synchronized(lockobj1) {
			fun_wait();
		}
	}
	public void fun_wait() {
		try {
			lockobj1.wait(100000);
		} catch (Exception e ) {
			e.printStackTrace();
		}
	}
}
