package com.my.sevices.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Service
public class StorageServiceImpl {

	public enum FileType {
		ALL, IMAGE
	}

	public UploadResult store(MultipartFile[] files, FileType type, String uploadPath) {

		switch (type) {
		case ALL:
			return doUpload(files, uploadPath);
		case IMAGE:
			return doImageUpload(files, uploadPath);
		}
		return new UploadResult(null, "Can not upload. Please choose file upload type");
	}

	public boolean delete(String uploadPath, String delFileName) {
		String detetePath = uploadPath + File.separator + delFileName;
		File delFile = new File(detetePath);
		return delFile.delete();
	}

	private UploadResult doUpload(MultipartFile[] files, String uploadPath) {
		String successInfo = "File uploaded: ";
		String failInfo = "Can not upload: ";
		List<File> uploadedFiles = new ArrayList<File>();

		File uploadDir = new File(uploadPath);
		// Create directory if it not exists.
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}

		for (MultipartFile file : files) {
			String name = file.getOriginalFilename();
			if (name != null && name.length() > 0) {
				try {
					File serverFile = new File(uploadDir.getAbsolutePath() + File.separator + name);
					BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
					stream.write(file.getBytes());
					stream.flush();
					stream.close();
					uploadedFiles.add(serverFile);
					successInfo += name + " ";
				} catch (Exception e) {
					failInfo += name + " ";
				}
			}
		}
		return new UploadResult(uploadedFiles, successInfo + failInfo);
	}

	private UploadResult doImageUpload(MultipartFile[] files, String uploadPath) {
		MultipartFile[] imageFiles = getImageFiles(files);
		return doUpload(imageFiles, uploadPath);
	}

	private MultipartFile[] getImageFiles(MultipartFile[] files) {
		List<MultipartFile> imageFiles = new ArrayList<MultipartFile>();
		for (MultipartFile file : files) {
			String name = file.getOriginalFilename();
			StringTokenizer token = new StringTokenizer(name, ".");
			if (token.hasMoreElements()) {
				token.nextToken();
				String extension = token.nextToken();
				if (isImagefile(extension)) {
					imageFiles.add(file);
				}
			}
		}
		return (MultipartFile[]) imageFiles.toArray(new MultipartFile[imageFiles.size()]);
	}

	private boolean isImagefile(String extension) {
		String[] imgExtentions = { "jpg", "jpeg", "gif", "png" };
		for (String imgEx : imgExtentions) {
			if (extension.toLowerCase().equals(imgEx))
				return true;
		}
		return false;
	}

	@Data
	public class UploadResult {
		private List<File> uploadedFiles;
		private String massage;

		public UploadResult(List<File> uploadedFiles, String massage) {
			this.uploadedFiles = uploadedFiles;
			this.massage = massage;
		}
	}

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("aaa");
		list.add("ccc");
		list.add("ddd");
		System.out.println(list.toArray()[1]);
	}

//	public void init() {
//	};

//	public Stream<Path> loadAll() {
//	};
//
//	public Path load(String filename) {
//	};
//
//	public Resource loadAsResource(String filename) {
//	};
//
//	public void deleteAll() {
//	};

}
