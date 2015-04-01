package lab6;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class FileCrawlerTest {

	@Test
	public void test() {
		
		

		FileCrawler fc = new FileCrawler();

		//  now start all of the worker threads

		int N = 1;
		ArrayList<Thread> thread = new ArrayList<Thread>(N);
		for (int i = 0; i < N; i++) {
			Thread t = new Thread(fc.createWorker());
			thread.add(t);
			t.start();
		}

		//  now place each directory into the workQ

		fc.processDirectory("/users/asadqureshi/Desktop//lab6test");



		//  indicate that there are no more directories to add

		fc.workQ.finish();

		for (int i = 0; i < N; i++){
			try {
				thread.get(i).join();
			} catch (Exception e) {};
		}
	}
	}

