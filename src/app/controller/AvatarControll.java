package app.controller;

import app.config.PageConfig;
import app.dao.user_info.IUserInfo;
import app.dao.user_info.UserInfoDAO;
import app.model.User_Info;
import app.services.auth.Auth;
import app.services.auth.SessionGuard;

import app.services.view.TextView;
import app.services.view.View;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;


@WebServlet(name = "uploadAvatar", urlPatterns = "/uploadAvatar")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	maxFileSize = 1024 * 1024 * 50,                   // 50MB
	maxRequestSize = 1024 * 1024 * 50)                // 50MB
public class AvatarControll extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		View view = new TextView(req, resp);
		view.setContentType(PageConfig.TYPE_CONTENT_JSON);

		String json = null;

		JSONObject jsonObject = new JSONObject();

		Auth auth = new SessionGuard(req, resp);
		if (auth.check()) {
			long user_id = auth.user().getId();

			User_Info user_info = auth.user().getInfo();
			if (user_info == null)
				return;

			String oldEXT = user_info.getAvatar_extension();
			if (oldEXT != null && oldEXT.length() > 1){
				String urlOLD = this.getFolderUpload().getAbsolutePath() + File.separator + user_id + "." + oldEXT;
				File check = new File(urlOLD);
				if (check.exists()){
					check.delete();
				}
			}

			for (Part part: req.getParts()) {
				String fileName = extractFileName(part);
				fileName = new File(fileName).getName();

				String extension = fileName.substring(fileName.lastIndexOf(".")+1);
				extension = extension.toLowerCase();

				String path = this.getFolderUpload().getAbsolutePath() + File.separator + user_id + "." + extension;
				part.write(path);

				user_info.setAvatar_extension(extension);

				IUserInfo iUserInfo = new UserInfoDAO();
				iUserInfo.update(user_info);
				jsonObject.put("url", File.separator + PageConfig.IMAGES_PATH + File.separator + PageConfig.AVATAR_PATH + File.separator + user_id + "." + extension);
			}
			jsonObject.put("status", 1);
		}else {
			jsonObject.put("status", 2);
		};

		json = jsonObject.toString();
		view.setLayout(json);
		view.get();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.sendRedirect("/");
	}

	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}

	public File getFolderUpload() {
		File folderUpload = new File(PageConfig.ROOT_PATH + File.separator + PageConfig.IMAGES_PATH);
		if (!folderUpload.exists()) {
			folderUpload.mkdirs();
		}

		folderUpload = new File(PageConfig.ROOT_PATH + File.separator + PageConfig.IMAGES_PATH + File.separator + PageConfig.AVATAR_PATH);
		if (!folderUpload.exists()) {
			folderUpload.mkdirs();
		}
		return folderUpload;
	}
}
