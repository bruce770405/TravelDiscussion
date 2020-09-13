/*
 * ===========================================================================
 * IBM Confidential
 * AIS Source Materials
 * 
 * 
 * (C) Copyright IBM Corp. 2018.
 *
 * ===========================================================================
 */
package javaserver.common.file;

import java.io.Serializable;
import java.security.SecureRandom;

/**
 * <p> TODO description</p>
 *
 * @author  BruceHsu
 * @version 1.0, 2018年5月27日
 * @see	    
 * @since 
 */
public class DownloadFileBean implements Serializable {

	    /** The Constant serialVersionUID. */
	    private static final long serialVersionUID = 1L;

	    /**
	     * 檔案名稱.
	     */
	    private String fileName;

	    /**
	     * Key值.
	     */
	    private String timeKey;

	    /**
	     * FileType.
	     */
	    private FileType fileType;

	    /**
	     * 是否要將檔案移除.
	     */
	    private byte[] downloadData;

	    /**
	     * 建構子.
	     */
	    public DownloadFileBean() {
	        SecureRandom secureRandom = new SecureRandom();
	        timeKey = String.valueOf(secureRandom.nextLong());
	    }

	    /**
	     * @return {@link #timeKxy}
	     */
	    public String getTimeKey() {
	        return timeKey;
	    }

	    /**
	     * @param timeKxy
	     *            {@link #timeKxy}
	     */
	    public void setTimeKey(String timeKey) {
	        this.timeKey = timeKey;
	    }

	    /**
	     * @return {@link #fileName}
	     */
	    public String getFileName() {
	        return fileName;
	    }

	    /**
	     * @param fileName
	     *            {@link #fileName}
	     */
	    public void setFileName(String fileName) {
	        this.fileName = fileName;
	    }

	    /**
	     * @return {@link #downloadData}
	     */
	    public byte[] getDownloadData() {
	        return downloadData;
	    }

	    /**
	     * @param downloadData
	     *            {@link #downloadData}
	     */
	    public void setDownloadData(byte[] downloadData) {
	        this.downloadData = downloadData;
	    }

	    /**
	     * @return {@link #fileType}
	     */
	    public FileType getFileType() {
	        return fileType;
	    }

	    /**
	     * @param fileType
	     *            {@link #fileType}
	     */
	    public void setFileType(FileType fileType) {
	        this.fileType = fileType;
	    }

}



 