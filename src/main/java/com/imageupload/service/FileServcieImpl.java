package com.imageupload.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServcieImpl implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {

		String name = file.getOriginalFilename();

		/*
		 * Random name generating
		 */		
		String randomID = UUID.randomUUID().toString();

		/*
		 * name.substring(name.lastIndexOf("."))-> this will return the next part of .
		 */		
		String fileName1 = randomID.concat(name.substring(name.lastIndexOf(".")));
	
		String filePath = path + File.separator + fileName1;
	
		/* If the folder is not exits then it will create a new folder */
		File f = new File(path);
		if (!f.exists()) {
			f.mkdir();
		}

		Files.copy(file.getInputStream(), Paths.get(filePath));

		return name;
	}

}
