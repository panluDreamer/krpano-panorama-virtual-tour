package upload;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.krpano.domain.Panorama;
import org.krpano.service.PanoService;
import org.krpano.util.DBoperation;
import org.krpano.util.Path;
import org.krpano.util.PluginInit;

import com.opensymphony.xwork2.ActionSupport;

public class ProducePano extends ActionSupport implements ServletRequestAware {
	private static final long serialVersionUID = 1L;
	public HttpServletRequest request;
	// public String strcmd = "cmd.exe /c start E:\\krpanomin\\control.vbs ";
	public String strcmd = Path.vbsPath;
	// public String basePath = "E:/workspace/krpanoWeb/WebContent/user/";
	// public String basePath = "D:/Tomcat 7.0/webapps/krpanoWeb/user/";//Tomcat
	// 7.0不能加空格
	// 这是eclipse运行的临时目录
	// public String basePath =
	// "E:/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp1/wtpwebapps/krpanoWeb/user/";//
	/**
	 * 5.24更改
	 * */
	public String basePath = Path.basicPath + "/user/";
	// public String defaultMusicPath =
	// "http://localhost:8080/krpanoWeb/default/bgMusic/ren.mp3";
	public String defaultMusicPath = Path.defaultMusicPath;

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		request = arg0;
	}

	public String produceResult;

	public String produce() throws InterruptedException {
		System.out.println("produce pano");
		String pano_id = request.getParameter("pano_id");
		String number = request.getParameter("number");
		String username = request.getParameter("username");
		String pano_name = request.getParameter("pano_name");
		String pano_type = request.getParameter("pano_type");
		String pano_info = request.getParameter("pano_info");
		System.out.println("pano_id=" + pano_id + " number=" + number
				+ " username=" + username + " pano_name = " + pano_name
				+ " pano_type=" + pano_type + " pano_info = " + pano_info);
		String savePath;
		savePath = basePath + username + "/" + pano_id + "/";
		String tempcmd = strcmd;
		tempcmd += savePath + "*.jpg";
		System.out.println("tempcmd = " + tempcmd);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("正在生成全景");
		// System.out.println("开始时间:" + df.format(new Date()));
		run_cmd(tempcmd);
		// 这里能否获得bat文件的执行结束事件
		// System.out.println("结束时间:" + df.format(new Date()));
		// }
		// Thread.sleep(1000);
		String threadName = Thread.currentThread().getName();
		System.out.println("current Thread: " + threadName + " start");
		CheckThread checkThread = new CheckThread(savePath, number);
		checkThread.start();// 开启子线程
		checkThread.join();// 等待子线程结束
		System.out.println("current Thread:" + threadName + " end");
		long timeGap = checkThread.timeGap;
		System.out.println("timeGap = " + timeGap);
		JSONObject jsonObject = new JSONObject();
		if (timeGap >= 40 * 1000) {// 超时,更改为40*1000,just a test
			System.out.println("生成失败，请检查图片是否符合标准");
			jsonObject.element("status", "false");
		} else {
			/**
			 * 初始化插件
			 * */
			String filepath = basePath + username + "/" + pano_id
					+ "/vtour/tour.xml";
			try {
				PluginInit.initializePlugin(filepath);
			} catch (IOException e) {
				e.printStackTrace();
			}
			String uploadTime = df.format(new Date());
			System.out.println("生成成功 时间为:" + uploadTime);
			/**
			 * 数据库操作
			 * */
			String tempPath = savePath + "vtour/panos/";
			File file = new File(tempPath);
			File files[] = file.listFiles();
			String folderName = files[0].getName();
			Panorama panorama = new Panorama();
			panorama.setPano_id(pano_id);
			panorama.setPano_name(pano_name);
			panorama.setPano_path("tour/" + username + "/pano_id=" + pano_id);
			panorama.setPano_type(pano_type);
			panorama.setInfo(pano_info);
			panorama.setCover_img("user/" + username + "/" + pano_id
					+ "/vtour/panos/" + folderName + "/thumb.jpg");
			System.out.println("cover_img = " + panorama.getCover_img());
			DBoperation.connectDB();
			boolean r1 = PanoService.addPanorama(panorama);
			boolean r2 = PanoService.addOtherPanoInfo(username, pano_id,
					uploadTime, defaultMusicPath, 0, 0);
			DBoperation.disconnectDB();
			System.out.println("r1 = " + r1 + " r2 = " + r2);
			if (r1 && r2) {
				jsonObject.element("status", "true");
			} else {
				jsonObject.element("status", "false");
			}
		}
		produceResult = jsonObject.toString();
		return SUCCESS;
	}

	public void run_cmd(String strcmd) {
		Runtime rt = Runtime.getRuntime(); // Runtime.getRuntime()返回当前应用程序的Runtime对象
		Process ps = null; // Process可以控制该子进程的执行或获取该子进程的信息。
		try {
			ps = rt.exec(strcmd); // 该对象的exec()方法指示Java虚拟机创建一个子进程执行指定的可执行程序，并返回与该子进程对应的Process对象实例。
			ps.waitFor(); // 等待子进程完成再往下执行。
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		int i = ps.exitValue(); // 接收执行完毕的返回值
		if (i == 0) {
			System.out.println("执行完成.");
			// execTimes++;
		} else {
			System.out.println("执行失败.");
		}
		ps.destroy(); // 销毁子进程
		ps = null;
	}

	/**
	 * 访问文件夹
	 * */
	public static Vector<String> traverseFolder(String folderPath) {
		Vector<String> imgPath = new Vector<>();
		File file = new File(folderPath);
		if (file.exists()) {
			File[] files = file.listFiles();
			int length = files.length;
			// System.out.println("length = " + length);
			for (int i = 0; i < length; i++) {
				// System.out.println(files[i].getAbsolutePath());
				imgPath.add(files[i].getAbsolutePath());
			}
		} else {
			System.out.println("文件不存在");
		}
		return imgPath;
	}
}
