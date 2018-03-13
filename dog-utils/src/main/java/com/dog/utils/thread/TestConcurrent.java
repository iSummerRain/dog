package com.dog.utils.thread;

import junit.framework.TestCase;

import java.io.File;
import java.io.FileFilter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestConcurrent extends TestCase {
	public void main2(String[] args) throws InterruptedException {
		// only two threads
		ExecutorService exec = Executors.newFixedThreadPool(2);
		for (int index = 0; index < 100; index++) {
			Runnable run = new Runnable() {
				public void run() {
					long time = (long) (Math.random() * 100);
					System.out.println(Thread.currentThread().getId()
							+ "Sleeping" + time + "ms");
					try {
						Thread.sleep(time);
					} catch (InterruptedException e) {
					}
				}
			};
			exec.execute(run);
		}
		// must shutdown
		exec.shutdown();
		System.out.println("-------------end-------------");
	}

	public static void main3(String[] args) throws Exception {
		final ScheduledExecutorService scheduler = Executors
				.newScheduledThreadPool(2);
		final Runnable beeper = new Runnable() {
			int count = 0;

			public void run() {
				System.out.println(Thread.currentThread().getId() + ""
						+ new Date() + " beep " + (++count));
			}
		};
		// 1秒钟后运行，并每隔2秒运行一次
		final ScheduledFuture beeperHandle = scheduler.scheduleAtFixedRate(
				beeper, 1, 2, TimeUnit.SECONDS);
		// 2秒钟后运行，并每次在上次任务运行完后等待5秒后重新运行
		final ScheduledFuture beeperHandle2 = scheduler.scheduleWithFixedDelay(
				beeper, 2, 5, TimeUnit.SECONDS);
		// 30秒后结束关闭任务，并且关闭Scheduler
		scheduler.schedule(new Runnable() {
			public void run() {
				beeperHandle.cancel(true);
				beeperHandle2.cancel(true);
				scheduler.shutdown();
			}
		}, 30, TimeUnit.SECONDS);
	}

	public static void main(String[] rags) {
		// 徒步需要的时间: Shenzhen, Guangzhou, Shaoguan, Changsha, Wuhan
		int[] timeWalk = { 5, 8, 15, 15, 10 };
		// 自驾游
		int[] timeSelf = { 1, 3, 4, 4, 5 };
		// 旅游大巴
		int[] timeBus = { 2, 4, 6, 6, 7 };

		class Tour implements Runnable {
			private int[] times;

			private CyclicBarrier barrier;

			private String tourName;

			public Tour(CyclicBarrier barrier, String tourName, int[] times) {
				this.times = times;
				this.tourName = tourName;
				this.barrier = barrier;
			}

			public String now() {
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
				return sdf.format(new Date()) + ":";
			}

			public void run() {
				try {
					Thread.sleep(times[0] * 1000);
					System.out.println(now() + tourName + " Reached Shenzhen");
					barrier.await();
					Thread.sleep(times[1] * 1000);
					System.out.println(now() + tourName + " Reached Guangzhou");
					barrier.await();
					Thread.sleep(times[2] * 1000);
					System.out.println(now() + tourName + " Reached Shaoguan");
					barrier.await();
					Thread.sleep(times[3] * 1000);
					System.out.println(now() + tourName + " Reached Changsha");
					barrier.await();
					Thread.sleep(times[4] * 1000);
					System.out.println(now() + tourName + " Reached Wuhan");
					barrier.await();
				} catch (InterruptedException e) {
				} catch (BrokenBarrierException e) {
				}
			}
		}

		CyclicBarrier barrier = new CyclicBarrier(3);
		ExecutorService exec = Executors.newFixedThreadPool(3);
		exec.submit(new Tour(barrier, "WalkTour", timeWalk));
		exec.submit(new Tour(barrier, "SelfTour", timeSelf));
		exec.submit(new Tour(barrier, "BusTour", timeBus));
		exec.shutdown();

	}

	static long randomTime() {
		return (long) (Math.random() * 1000);
	}

	public static void main5(String[] args) {

		// 能容纳100个文件
		final BlockingQueue queue = new LinkedBlockingQueue(100);
		// 线程池
		final ExecutorService exec = Executors.newFixedThreadPool(5);
		final File root = new File("F:\\JavaLib");
		// 完成标志
		final File exitFile = new File("");
		// 读个数
		final AtomicInteger rc = new AtomicInteger();
		// 写个数
		final AtomicInteger wc = new AtomicInteger();
		// 读线程
		Runnable read = new Runnable() {
			public void run() {
				scanFile(root);
				scanFile(exitFile);
			}

			public void scanFile(File file) {
				if (file.isDirectory()) {
					File[] files = file.listFiles(new FileFilter() {
						public boolean accept(File pathname) {
							return pathname.isDirectory()
									|| pathname.getPath().endsWith(".java");
						}
					});
					for (File one : files)
						scanFile(one);
				} else {
					try {
						int index = rc.incrementAndGet();
						System.out.println("Read0: " + index + ""
								+ file.getPath());
						queue.put(file);
					} catch (InterruptedException e) {
					}
				}
			}
		};
		exec.submit(read);
		// 四个写线程
		for (int index = 0; index < 4; index++) {
			// write thread
			final int NO = index;
			Runnable write = new Runnable() {
				String threadName = "Write" + NO;

				public void run() {
					while (true) {
						try {
							Thread.sleep(randomTime());
							int index = wc.incrementAndGet();
							File file = (File) queue.take();
							// 队列已经无对象
							if (file == exitFile) {
								// 再次添加”标志”，以让其他线程正常退出
								queue.put(exitFile);
								break;
							}
							System.out.println(threadName + ": " + index + ""
									+ file.getPath());
						} catch (InterruptedException e) {
						}
					}
				}
			};
			exec.submit(write);
		}
		exec.shutdown();
	}

	public static void main7(String[] args) {
		try {
			// 开始的倒数锁
			final CountDownLatch begin = new CountDownLatch(1);
			// 结束的倒数锁
			final CountDownLatch end = new CountDownLatch(10);
			// 十名选手
			ExecutorService exec = Executors.newFixedThreadPool(10);
			for (int index = 0; index < 10; index++) {
				final int NO = index + 1;
				Runnable run = new Runnable() {
					public void run() {
						try {
							begin.await();
							Thread.sleep((long) (Math.random() * 10000));
							System.out.println("No." + NO + " arrived");
						} catch (InterruptedException e) {
						} finally {
							end.countDown();
						}
					}
				};
				exec.submit(run);
			}
			System.out.println("Game Start");
			begin.countDown();
			end.await();
			System.out.println("Game Over");
			exec.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public  void testFuture() throws InterruptedException,	ExecutionException {
		final ExecutorService exec = Executors.newFixedThreadPool(5);
		
		Callable call = new Callable() {
			public String call() throws Exception {
				Thread.sleep(1000 * 5);
				return "Other less important but longtime things.";
			}
		};
		Future task = exec.submit(call);
		// 重要的事情
		Thread.sleep(1000 * 3);
		System.out.println("Let’s do  important things.");
		// 其他不重要的事情
		String obj = (String)task.get();
		System.out.println(obj);
		// 关闭线程池
		exec.shutdown();
	}
	
	public static void main8(String[] args) {
		// 线程池
		ExecutorService exec = Executors.newCachedThreadPool();
		// 只能5个线程同时访问
		final Semaphore semp = new Semaphore(5);
		// 模拟20个客户端访问
		for (int index = 0; index < 20; index++) {
			final int NO = index;
			Runnable run = new Runnable() {
				public void run() {
					try {
						// 获取许可
						semp.acquire();
						System.out.println("Accessing: " + NO);
						Thread.sleep((long) (Math.random() * 10000));
						// 访问完后，释放
						semp.release();
					} catch (InterruptedException e) {
					}
				}
			};
			exec.execute(run);
		}
		// 退出线程池
		exec.shutdown();
	}

}
