package com.eulerity.hackathon.imagefinder;

class ThreadJoining extends Thread 
{ 
    @Override
    public void run() 
    { 
        for (int i = 0; i < 2; i++) 
        { 
            try
            { 
                 
                System.out.println("Current Thread: "
                        + Thread.currentThread().getName()); 
            } 
  
            catch(Exception ex) 
            { 
                System.out.println("Exception has" + 
                                " been caught" + ex); 
            } 
            System.out.println(i); 
        } 
        try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    } 

    public static void main (String[] args) 
    { 
    	System.out.println("Current Thread: SART "
                );
        // creating two threads 
        ThreadJoining t1 = new ThreadJoining(); 
        ThreadJoining t2 = new ThreadJoining(); 
        ThreadJoining t3 = new ThreadJoining(); 
  
        // thread t1 starts 
        t1.start(); 
        t2.start();
        t3.start();
        // starts second thread after when 
        // first thread t1 has died. 
        try
        { 
            System.out.println("Current Thread: "
                  + Thread.currentThread().getName()); 
            t1.join(); 
        } 
  
        catch(Exception ex) 
        { 
            System.out.println("Exception has " + 
                                "been caught" + ex); 
        } 
  
        
        System.out.println("Current Thread: MAIN "
                ); 
        // t2 starts 
         
  
        // starts t3 after when thread t2 has died. 
		/*
		 * try { System.out.println("Current Thread: " +
		 * Thread.currentThread().getName()); t2.join(); }
		 * 
		 * catch(Exception ex) { System.out.println("Exception has been" + " caught" +
		 * ex); }
		 */
  
         
    } 
} 