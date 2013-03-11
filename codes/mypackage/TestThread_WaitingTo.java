package mypackage;

public class TestThread_WaitingTo extends Thread {
	Object lock = null;
	public TestThread_WaitingTo(Object lock_) {
		this.lock = lock_;
		this.setName(this.getClass().getName());
	}
	@Override
	public void run() {
		fun();
	}
	public void fun() {
		synchronized(lock) {
			fun_longtime();
		}
	}
	public void fun_longtime() {
		try {
			Thread.sleep(20000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
