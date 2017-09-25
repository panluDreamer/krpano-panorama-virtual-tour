package org.krpano.util;

import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class SpaceGetter {
	private ExecutorService service;
	final private AtomicLong pendingFileVisits = new AtomicLong();
	final private AtomicLong totalSize = new AtomicLong();
	final private CountDownLatch latch = new CountDownLatch(1);

	private void updateTotalSizeOfFilesInDir(final File file) {
		long fileSize = 0;
		if (file.isFile())
			fileSize = file.length();
		else {
			final File[] children = file.listFiles();
			if (children != null) {
				for (final File child : children) {
					if (child.isFile())
						fileSize += child.length();
					else {
						pendingFileVisits.incrementAndGet();
						service.execute(new Runnable() {
							public void run() {
								updateTotalSizeOfFilesInDir(child);
							}
						});
					}
				}
			}
		}
		totalSize.addAndGet(fileSize);
		if (pendingFileVisits.decrementAndGet() == 0)
			latch.countDown();
	}

	private long getTotalSizeOfFile(final String fileName)
			throws InterruptedException {
		service = Executors.newFixedThreadPool(100);
		pendingFileVisits.incrementAndGet();
		try {
			updateTotalSizeOfFilesInDir(new File(fileName));
			latch.await(100, TimeUnit.SECONDS);
			return totalSize.longValue();
		} finally {
			service.shutdown();
		}
	}

	/**
	 * 获取文件或目录大小，多线程，返回单位为字节
	 * */
	public static long getSpace(String filepath) throws InterruptedException {
		final long total = new SpaceGetter().getTotalSizeOfFile(filepath);
		return total;
	}
}
