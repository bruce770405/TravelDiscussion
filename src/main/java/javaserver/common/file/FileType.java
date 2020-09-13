package javaserver.common.file;

/**
 * <p>
 * 檔案類型.
 * </p>
 *
 * @author BruceHsu
 * @version 1.0, 2018年5月27日
 * @see
 * @since
 */
public enum FileType {

	
	/** PDF. */
	PDF(1, "PDF"),

	/** CSV. */
	CSV(2, "CSV"),

	/** XLS. */
	XLS(3, "XLS"),

	/** TXT. */
	TXT(4, "TXT"),
	/** JPG. */
	JPG(5, "JPG"),
	
	/** PNG. */
	PNG(6, "PNG"),

	/** 未知. */
	UNKNOWN(-1, "未知");

	/** 代碼. */
	private int code;
	

	/** 備註(code gen多國語言使用). */
	private String memo;

	/**
	 * Constructor.
	 * 
	 * @param code
	 *            the code.
	 * @param memo
	 *            the memo.
	 */
	FileType(int code, String memo) {
		this.code = code;
		this.memo = memo;
	}

	/**
	 * 依據代碼取得FileType.
	 * 
	 * @param code
	 *            the code.
	 * @return the FileType
	 */
	public static FileType find(int code) {

		for (FileType value : FileType.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		return FileType.UNKNOWN;
	}

	/**
	 * @return {@link #code}
	 */
	public final int getCode() {
		return code;
	}

	/**
	 * @return {@link #memo}
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * @param memo
	 *            {@link #memo}
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * @param code
	 *            {@link #code}
	 */
	public void setCode(int code) {
		this.code = code;
	}

}
