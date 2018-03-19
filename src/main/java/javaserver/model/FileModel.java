package javaserver.model;

import java.util.ArrayList;
import java.util.List;

public class FileModel {

	private List<String> fileList = new ArrayList<>();
	private String username;

	public FileModel(String username, List<String> list) {
		this.setUsername(username);
		this.setFileList(list);
	}

	public List<String> getFileList() {
		return fileList;
	}

	public void setFileList(List<String> fileList) {
		this.fileList = fileList;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
