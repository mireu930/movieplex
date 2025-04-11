package com.movie.plex.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;


@Component
public class FileDown extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		FileDTO fileDTO = (FileDTO)model.get("file");
		String path = (String)model.get("kind");
		path = request.getSession().getServletContext().getRealPath("/resources/images/"+path+"/");
		
		File file = new File(path,fileDTO.getFileName());

		response.setCharacterEncoding("UTF-8");

		response.setContentLength((int)file.length());

		String name = fileDTO.getOldName();

		name = URLEncoder.encode(name, "UTF-8");

		response.setHeader("Content-Disposition", "attachment;fileName=\""+name+"\"");
		response.setHeader("Content-transfer-Encoding", "binary");
		
		FileInputStream fileInputStream = new FileInputStream(file);
		OutputStream outputStream = response.getOutputStream();
		
		FileCopyUtils.copy(fileInputStream, outputStream);

		outputStream.close();
		fileInputStream.close();
	}
}
