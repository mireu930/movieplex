package com.movie.plex.files;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {

	public String file(String path, MultipartFile profile) throws Exception {
		File file = new File(path);
	
	if(!file.exists()) {
		file.mkdirs();
	}
	

	String f = UUID.randomUUID().toString();
	f = f+"_"+profile.getOriginalFilename();
	

	file = new File(file, f);
	

	FileCopyUtils.copy(profile.getBytes(), file);		
	
	return f;
}

	public void delete(String path, String fileName) throws Exception {
		File file = new File(path, fileName);
	
		boolean check = file.delete();
	
	}
}
