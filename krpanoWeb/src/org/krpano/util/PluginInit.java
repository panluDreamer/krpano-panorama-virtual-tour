package org.krpano.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

/**
 * krpano插件初始化
 * */
public class PluginInit {
	/**
	 * 全景生成成功后，更改tour.xml文件，引入自定义插件，使用了绝对地址
	 * */
	public static String dianzanPlugin = "<include url=\"http://localhost:8080/krpanoWeb/plugin/dianzan/va.xml\" devices=\"desktop\" />";
	public static String qrPlugin = "<include url=\"http://localhost:8080/krpanoWeb/plugin/shareQR/qr.xml\"></include>";
	public static String shuoPlugin = "<include url=\"http://localhost:8080/krpanoWeb/plugin/shuoyishuo/shuo.xml\" devices=\"desktop\"></include>";
	public static String marqueeDataPlugin = "<include url=\"http://localhost:8080/krpanoWeb/plugin/marquee/scrolling_marquee_data.xml\"></include>";
	public static String marqueePlugin = "<include url=\"http://localhost:8080/krpanoWeb/plugin/marquee/scrolling_marquee.xml\"></include>";

	public static void initializePlugin(String filepath) throws IOException {
		Vector<String> vector = new Vector<>();
		vector = readFileByLine(filepath);
		writeplugin(filepath, vector, dianzanPlugin, qrPlugin, shuoPlugin,
				marqueeDataPlugin, marqueePlugin);
		System.out.println("filepath:" + filepath + " 更改成功");
	}

	public static Vector<String> readFileByLine(String filepath)
			throws IOException {
		Vector<String> vector = new Vector<>();
		File file = new File(filepath);
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			while ((tempString = bufferedReader.readLine()) != null) {
				if (line == 1) {
					String templineone = "<";
					templineone += tempString.trim().substring(2);// 到底是1还是2
					tempString = templineone;
				}
				// System.out.println("line" + line + ":" + tempString);
				vector.add(tempString);
				line++;
			}
			bufferedReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return vector;
	}

	public static void writeplugin(String filePath, Vector<String> vector,
			String dianzanPlugin, String qrPlugin, String shuoPlugin,
			String marqueeDataPlugin, String marqueePlugin) {
		System.out.println("vector.size=" + vector.size());
		File file = new File(filePath);
		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {
			fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			int size = vector.size();
			bufferedWriter.write(vector.get(0));
			bufferedWriter.newLine();
			bufferedWriter.write(vector.get(1));
			bufferedWriter.newLine();
			bufferedWriter.write(dianzanPlugin);
			bufferedWriter.newLine();
			bufferedWriter.write(qrPlugin);
			bufferedWriter.newLine();
			bufferedWriter.write(shuoPlugin);
			bufferedWriter.newLine();
			bufferedWriter.write(marqueeDataPlugin);
			bufferedWriter.newLine();
			bufferedWriter.write(marqueePlugin);
			bufferedWriter.newLine();
			for (int i = 2; i < size; i++) {
				bufferedWriter.write(vector.get(i));
				// System.out.println(vector.get(i));
				bufferedWriter.newLine();
			}
			bufferedWriter.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bufferedWriter.close();
				fileWriter.close();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}

	}
}
