package com.rahul.blog.app.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.rahul.blog.app.service.FileService;

@Service
@Transactional
public class FileServieImpl implements FileService{

	@Override
	public String uploadImage(String path, MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		
		
		System.out.println("File name-----" +file.getContentType());
		
		//File Name
		String name = file.getOriginalFilename();
		
		String randomId=UUID.randomUUID().toString();
		String fileName1=randomId.concat(name.substring(name.lastIndexOf('.')));
		
		//Full Path
		String filePath=path + File.separator+fileName1;
		
		
		
		
		//Create Folder
		
		File f=new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
			
		
		//Copy File
		
		Files.copy(file.getInputStream(), Paths.get(filePath));
		return fileName1;
	}

	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		String fullpath= path + File.separator+fileName;
		
		InputStream is =new FileInputStream(fullpath);
		
		return is;
	}

}
