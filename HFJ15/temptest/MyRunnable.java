public class MyRunnable implements Runnable{
	public void run(){
		go();
	}

	public void go(){
		try{
			Thread.sleep(2000);
		}catch(Exception e){
			e.printStackTrace();
		}
		doMore();
	}

	public void doMore(){
		System.out.println("top o' the stack");
	}
}
