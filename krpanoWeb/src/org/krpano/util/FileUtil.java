package org.krpano.util;

import java.io.File;

public class FileUtil {
	/**
	 * 删除某一目录下的全部文件
	 * */
	public static void deleteAllFilesInDir(File dirpath) {
		if (!dirpath.exists()) {
			return;
		}
		if (dirpath.isFile()) {
			dirpath.delete();
			return;
		}
		File[] files = dirpath.listFiles();
		for (int i = 0; i < files.length; i++) {// 递归删除
			// System.out.println(files[i].getName());
			deleteAllFilesInDir(files[i]);
		}
		dirpath.delete();
	}
	/**
	 * 根据文件夹路径删除该文件夹下特定名字的文件
	 * */
	public static Boolean deleteFileByPathAndName(String path,String name){
		Boolean res = true;
		File dir = new File(path);
		File[]files = dir.listFiles();
		for(int i=0;i<files.length;i++){
			if(files[i].getName().equals(name)){
				res = files[i].delete();
				break;
			}
		}
		return res;
	}
}
