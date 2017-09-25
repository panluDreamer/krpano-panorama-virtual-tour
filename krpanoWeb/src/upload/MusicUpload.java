package upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.krpano.service.UserService;
import org.krpano.util.Path;

/**
 * Servlet implementation class MusicUpload
 */
@WebServlet("/MusicUpload")
public class MusicUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected String dirTemp = "upload/widget/temp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MusicUpload() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		System.out.println("MusicUpload servlet");
		String username = request.getParameter("username");
		System.out.println("username=" + username);
		String pano_id = request.getParameter("pano_id");
		System.out.println("pano_id=" + pano_id);
		// String savePath =
		// "E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\krpanoWeb\\user\\lupan\\image";//
		// test
		// String savePath =
		// "D:\\Tomcat 7.0\\webapps\\krpanoWeb\\user\\lupan\\image";// test
		// lupan
		// eclipse运行的临时目录

		String savePath = org.krpano.util.Path.basicPath + "/user/" + username
				+ "/music";
		String tempPath = this.getServletContext().getRealPath("/") + dirTemp;
		String copyFileName = "error.mp3";
		File dirTempFile = new File(tempPath);
		if (!dirTempFile.exists()) {
			dirTempFile.mkdirs();
		}
		/**
		 * 当music文件夹不存在时，创建
		 * */
		File savePathFile = new File(savePath);
		if (!savePathFile.exists()) {
			savePathFile.mkdirs();
		}
		PrintWriter out = response.getWriter();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(20 * 1024 * 1024); // 设定使用内存超过5M时，将产生临时文件并存储于临时目录中。
		factory.setRepository(new File(tempPath)); // 设定存储临时文件的目录。
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");

		try {
			List items = upload.parseRequest(request);
			// System.out.println("items = " + items);
			Iterator itr = items.iterator();
			System.out.println("items list size = " + items.size());
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				String fileName = item.getName();

				long fileSize = item.getSize();
				/**
				 * 为什么在IE中，文件名是路径的全名
				 * */
				/**
				 * 判断是普通文本表单域还是文件上传表单域
				 * */
				if (!item.isFormField()) {
					// String fileExt = fileName.substring(
					// fileName.lastIndexOf(".") + 1).toLowerCase();
					// SimpleDateFormat df = new SimpleDateFormat("HHmmss");
					String newFileName;
					// = df.format(new Date()) + "_"
					// + new Random().nextInt(1000) + "." + fileExt;
					newFileName = fileName;// 新文件名暂定和原来的一样
					copyFileName = newFileName;
					System.out.println("savePath=" + savePath + " filename="
							+ newFileName);
					try {
						File uploadedFile = new File(savePath, newFileName);

						OutputStream os = new FileOutputStream(uploadedFile);
						InputStream is = item.getInputStream();
						byte buf[] = new byte[1024];// 可以修改 1024 以提高读取速度
						int length = 0;
						while ((length = is.read(buf)) > 0) {
							os.write(buf, 0, length);
						}
						// 关闭流
						os.flush();
						os.close();
						is.close();
						System.out.println("成功");
						// System.out.println("上传成功！路径：" + savePath +
						// newFileName);
						// out.print(savePath + newFileName);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
				}
			}// end of while
				// System.out.println("run cmd:");
			// run_cmd(strcmd);

		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/**
		 * 在这里直接更新数据库中panorama表中的cover_img
		 * */
		String music_path = Path.host + "/user/" + username + "/music/"
				+ copyFileName;
		Boolean res = UserService
				.modifyMusic(pano_id, music_path, copyFileName);
		JSONObject jsonObject = new JSONObject();
		jsonObject.element("status", res.toString());
		out.flush();
		out.print(jsonObject.toString());
		out.close();
	}

}
