package javaserver.util;


import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

/**
 * <p>
 * 檔案名稱工具集
 * </p>
 * .
 * @author BruceHsu
 * @version 1.0, Dec 12, 2018
 * @see
 * @since
 */
public class FileNameUtilsEx extends FilenameUtils {

    /**
     * 正規化檔案路徑，並將所有路徑分隔子代換為Unix分隔子"/"，.
     * 同時在路徑最後加上分隔子.
     * <pre>
     * "e:\\tmp" = "e:/tmp/";.
     * </pre>
     * @param path
     *            the path.
     * @return the String
     */
    public static String normalizeDir(String path) {

        String dir = FileNameUtilsEx.normalizeNoEndSeparator(path);

        if (StringUtils.isNotBlank(dir)) {

            dir = StringUtils.replace(dir, "\\", "/");

            if (!StringUtils.equals(dir, "/")) {
                dir += "/";
            }
        } else {
            dir = "";
        }

        return dir;

    }

}
