package upload;

import java.io.File;

/**
 * 检测全景是否生成成功
 * */
public class CheckThread extends Thread {
	public String filePath;
	public int number;
	public long timeGap = 0; // 设置超时时间(单位ms)

	public CheckThread(String path, String number) {
		super("check Thread");
		this.filePath = path;
		this.number = Integer.valueOf(number);
	}

	@Override
	public void run() {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + " start");
		System.out.println("filePath:" + filePath);
		long startTime = System.currentTimeMillis();
		File file = new File(filePath + "vtour");
		int count = 0;
		while (true) {
			count++;
			timeGap = (System.currentTimeMillis() - startTime);
			if (timeGap >= 40*1000) {
				break;
			}
			if (!file.exists()) {
				// System.out.println("file 不存在");
			} else {
				int size = file.listFiles().length;
				// System.out.println("size = " + size);
				if (size == 10) {//
					break;
				} else {
					// System.out.println("curr file len:"
					// + file.listFiles().length);
				}
			}
		}
		System.out.println(threadName + " end" + "  count=" + count);
	}
}
