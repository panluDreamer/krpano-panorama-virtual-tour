package upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.krpano.util.Path;
import org.krpano.util.UnicodeUtil;

/**
 * Servlet implementation class UploadAction
 */
public class UploadAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 上传文件的保存路径
	protected String configPath = "upload/widget";
	protected String dirTemp = "upload/widget/temp";
	protected String dirName = "file";

	// public String strcmd =
	// "cmd /c start  D:\\Krpano\\krpano-1.19-pr8\\MAKEVTOURMULTIRESdroplet.bat";
	/**
	 * vbs脚本的路径
	 * */
	// public String strcmd = "cmd.exe /c start E:\\krpanomin\\control.vbs ";

	// public int count = 0;
	// public int execTimes = 0;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadAction() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
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
		String name = request.getParameter("name");
		// System.out.println("name = " + name);
		int name_len = name.length();
		String firstUnicode = UnicodeUtil.gbEncoding(String.valueOf(name
				.charAt(0)));
		String lastUnicode = UnicodeUtil.gbEncoding(String.valueOf(name
				.charAt(name_len - 1)));
		// System.out.println("fisrt Unicode: " + firstUnicode +
		// "  lastUnicode: "
		// + lastUnicode);
		String username = request.getParameter("username");
		// System.out.println("username = " + username);
		// count++;
		// System.out.println("count = " + count);
		PrintWriter out = response.getWriter();
		// out.print("This is Servlet!");
		// 文件保存目录路径
		// String savePath = "E:/";
		// String savePath = "E:/workspace/krpanoWeb/WebContent/user/" +
		// username;
		// String savePath = "D:\\Tomcat 7.0\\webapps\\krpanoWeb\\user\\" +
		// username;
		// 这是eclipse运行的临时目录
		// String savePath =
		// "E:\\workspace\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp1\\wtpwebapps\\krpanoWeb\\user\\"
		// + username;
		/**
		 * 5.24更改
		 * */
		String savePath = Path.basicPath + "/user/" + username;
		// String savePath = this.getServletContext().getRealPath("/") +
		// configPath;
		// 临时文件目录
		String tempPath = this.getServletContext().getRealPath("/") + dirTemp;
		// 获取username第一个字符的ASCII码值,如果1分钟内上传多次？如何唯一标识
		int first = (int) username.charAt(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");// 可能存在6张图片到达的秒数不一样，从而生成2个文件夹
		String ymd = sdf.format(new Date());
		// 上传图片的保存路径，生成唯一的pano_id
		savePath += "/" + ymd + firstUnicode + lastUnicode + "/";
		out.print(ymd + firstUnicode + lastUnicode);// here
		// System.out.println("保存路径为:" + savePath);
		// 创建文件夹
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}

		tempPath += "/" + ymd + "/";
		// System.out.println("tempPath = " + tempPath);
		// 创建临时文件夹
		File dirTempFile = new File(tempPath);
		if (!dirTempFile.exists()) {
			dirTempFile.mkdirs();
		}

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(20 * 1024 * 1024); // 设定使用内存超过5M时，将产生临时文件并存储于临时目录中。
		factory.setRepository(new File(tempPath)); // 设定存储临时文件的目录。
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("UTF-8");
		try {
			List items = upload.parseRequest(request);
			// System.out.println("items = " + items);
			Iterator itr = items.iterator();
			// System.out.println("item size = " + items.size());
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				String fileName = item.getName();
				long fileSize = item.getSize();
				/**
				 * 为什么在IE中，文件名是路径的全名
				 * */
				// System.out.println("文件名:" + fileName + " 文件大小:" + fileSize);
				// System.out.println("item.isFromField:" + item.isFormField());
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
						// System.out.println("上传成功！路径：" + savePath +
						// newFileName);
						// out.print(savePath + newFileName);
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					// String filedName = item.getFieldName();
					// System.out.println("===============");
					// System.out.println(new String(item.getString().getBytes(
					// "iso-8859-1"), "utf-8"));
					// System.out.println("FieldName：" + filedName);
					// 获得裁剪部分的坐标和宽高
					// System.out.println("String：" + item.getString());
				}
			}// end of while
				// System.out.println("run cmd:");
			// run_cmd(strcmd);

		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		out.flush();
		out.close();
	}
}
