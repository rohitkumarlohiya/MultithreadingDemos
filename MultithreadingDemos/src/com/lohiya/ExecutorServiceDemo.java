package com.lohiya;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceDemo {

	public static void main(String[] args) {

		/*ExecutorService executorService = Executors.newFixedThreadPool(10);

		executorService.execute(new Runnable() {
			public void run() {
				System.out.println("\""+Thread.currentThread().getName()+"\" is executing -- "+"Asynchronous task");
			}
		});

		executorService.shutdown();*/
		
		/**
		 * execute(Runnable)
		 * 
		 * The execute(Runnable) method takes a java.lang.Runnable object, and
		 * executes it asynchronously. Here is an example of executing a
		 * Runnable with an ExecutorService:
		 */
		
		/*ExecutorService executorService = Executors.newSingleThreadExecutor();

		executorService.execute(new Runnable() {
		    public void run() {
		        System.out.println("Asynchronous task");
		    }
		});

		executorService.shutdown();*/
		
		/**
		 * submit(Runnable)
		 * 
		 * The submit(Runnable) method also takes a Runnable implementation, but
		 * returns a Future object. This Future object can be used to check if
		 * the Runnable as finished executing.
		 */
		
		/*ExecutorService executorService = Executors.newSingleThreadExecutor();

		Future future = executorService.submit(new Runnable() {
		    public void run() {
		    	
		        System.out.println("Asynchronous task");
		        
		        try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		    }
		});

		try {
			System.out.println(future.get());//returns null if the task has finished correctly.
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}  

		executorService.shutdown();*/
		
		/**
		 * submit(Callable)
		 * 
		 * The submit(Callable) method is similar to the submit(Runnable) method
		 * except for the type of parameter it takes. The Callable instance is
		 * very similar to a Runnable except that its call() method can return a
		 * result. The Runnable.run() method cannot return a result.
		 * 
		 * The Callable's result can be obtained via the Future object returned
		 * by the submit(Callable) method.
		 */
		
		/*ExecutorService executorService = Executors.newSingleThreadExecutor();

		Future<String> future = executorService.submit(new Callable<String>(){
		    public String call() throws Exception {
		        System.out.println("Asynchronous Callable");
		        return "Callable Result";
		    }
		});

		try {
			System.out.println("future.get() = " + future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}  

		executorService.shutdown();*/
		
		/**
		 * invokeAny()
		 * 
		 * The invokeAny() method takes a collection of Callable objects, or
		 * subinterfaces of Callable. Invoking this method does not return a
		 * Future, but returns the result of one of the Callable objects. You
		 * have no guarantee about which of the Callable's results you get. Just
		 * one of the ones that finish.
		 * 
		 * If one of the tasks complete (or throws an exception), the rest of
		 * the Callable's are cancelled.
		 */
		
		/*ExecutorService executorService = Executors.newSingleThreadExecutor();

		Set<Callable<String>> callables = new HashSet<Callable<String>>();

		callables.add(new Callable<String>() {
		    public String call() throws Exception {
		        return "Task 1";
		    }
		});
		callables.add(new Callable<String>() {
		    public String call() throws Exception {
		        return "Task 2";
		    }
		});
		callables.add(new Callable<String>() {
		    public String call() throws Exception {
		        return "Task 3";
		    }
		});

		String result = null;
		try {
			result = executorService.invokeAny(callables);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		System.out.println("result = " + result);

		executorService.shutdown();*/
		
		/**
		 * invokeAll()
		 * 
		 * The invokeAll() method invokes all of the Callable objects you pass
		 * to it in the collection passed as parameter. The invokeAll() returns
		 * a list of Future objects via which you can obtain the results of the
		 * executions of each Callable.
		 * 
		 * Keep in mind that a task might finish due to an exception, so it may
		 * not have "succeeded". There is no way on a Future to tell the
		 * difference.
		 */
		
		ExecutorService executorService = Executors.newSingleThreadExecutor();

		Set<Callable<String>> callables = new HashSet<Callable<String>>();

		callables.add(new Callable<String>() {
		    public String call() throws Exception {
		        return "Task 1";
		    }
		});
		callables.add(new Callable<String>() {
		    public String call() throws Exception {
		        return "Task 2";
		    }
		});
		callables.add(new Callable<String>() {
		    public String call() throws Exception {
		        return "Task 3";
		    }
		});

		try {
			List<Future<String>> futures = executorService.invokeAll(callables);

			for(Future<String> future : futures){
			    System.out.println("future.get = " + future.get());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		executorService.shutdown();
		
	}

}
