package javaserver.util;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.FastDateFormat;

/**
 * 檔案處理工具集.
 * <p>
 * 檔案工具集主要功能繼承自{@link org.apache.commons.io.FileUtils}， 並增加下列功能：
 * <ul>
 * <li>read file to byte array</li>
 * <li>write file to byte array</li>
 * </ul>
 * </p>
 * @author BruceHsu
 * @version 1.0, 2018/05/27
 * @see {@link org.apache.commons.io.FileUtils}
 * @since
 */
public class FileUtilsEx extends FileUtils {

   
    /**
     * Read file to byte array.
     * @param file
     *            the file.
     * @return the byte[]
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public static byte[] readFileToByteArray(File file) throws IOException {

        InputStream in = new java.io.FileInputStream(file);
        try {
            return IOUtils.toByteArray(in);
        } finally {
            IOUtils.closeQuietly(in);
        }

    }

    /**
     * Read file to byte array.
     * @param sFileName
     *            the s file name.
     * @return the byte[]
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public static byte[] readFileToByteArray(String sFileName) throws IOException {

        if (StringUtils.isBlank(sFileName)) {
            return null;
        }

        byte[] data = null;

        File file = new File(sFileName);

        data = readFileToByteArray(file);

        return data;
    }

    /**
     * Read file to byte array quietly.
     * @param sFileName
     *            the s file name.
     * @return the byte[]
     */
    public static byte[] readFileToByteArrayQuietly(String sFileName) {

        if (StringUtils.isBlank(sFileName)) {
            return null;
        }

        byte[] data = null;

        try {
            File file = new File(sFileName);

            data = readFileToByteArray(file);
        } catch (IOException e) {
            // quiet

            data = null;

        }

        return data;
    }

    /**
     * Read file to string.
     * @param sFileName
     *            the s file name.
     * @param sEncoding
     *            the s encoding.
     * @return the String
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public static String readFileToString(String sFileName, String sEncoding) throws IOException {
        if (StringUtils.isBlank(sFileName)) {
            return null;
        }

        String sData = null;

        File file = new File(sFileName);

        sData = readFileToString(file, sEncoding);

        return sData;
    }

    /**
     * Read file to string quietly.
     * @param sFileName
     *            the s file name.
     * @param sEncoding
     *            the s encoding.
     * @return the String
     */
    public static String readFileToStringQuietly(String sFileName, String sEncoding) {
        if (StringUtils.isBlank(sFileName)) {
            return null;
        }

        String sData = null;

        try {
            File file = new File(sFileName);

            sData = readFileToString(file, sEncoding);
        } catch (IOException e) {
            // quiet

            sData = null;

        }

        return sData;
    }

    // /**
    // * <p>
    // * Writes data to a file. The file will be created if it does not exist.
    // * </p>
    // * @param file
    // * the file to write.
    // * @param data
    // * The content to write to the file.
    // * @throws IOException
    // * in case of an I/O error.
    // */
    // public static void writeByteArrayToFile(File file, byte[] data) throws
    // IOException {
    // OutputStream out = new java.io.FileOutputStream(file);
    // try {
    // out.write(data);
    // } finally {
    // IOUtils.closeQuietly(out);
    // }
    // }

    /**
     * <p>
     * Writes data to a file. The file will be created if it does not exist.
     * </p>
     * @param sFileName
     *            the file name to write.
     * @param sData
     *            the content to write to the file name.
     * @param sEncoding
     *            encoding to use.
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public static void writeStringToFile(String sFileName, String sData, String sEncoding) throws IOException {

        if (StringUtils.isBlank(sFileName) || StringUtils.isBlank(sEncoding)) {
            return;
        }

        File file = new File(sFileName);
        writeStringToFile(file, sData, sEncoding);
        /*
         * BufferedOutputStream out = null; try {
         * out = new BufferedOutputStream(new FileOutputStream(sFileName));
         * out.write(sData.getBytes(sEncoding)); } finally { if (out != null) {
         * out.flush(); out.close(); } }
         */
    }

    /**
     * <p>
     * Writes data to a file. The file will be created if it does not exist.
     * </p>
     * @param sFileName
     *            the file name to write.
     * @param data
     *            the content to write to the file name.
     */
    public static void writeByteArrayToFileQuietly(String sFileName, byte[] data) {

        if (StringUtils.isBlank(sFileName)) {
            return;
        }

        try {
            File file = new java.io.File(sFileName);

            writeByteArrayToFile(file, data);
        } catch (IOException e) {
            // quietly
        }
    }

    /**
     * Write byte array to file.
     * @param sFileName
     *            the s file name.
     * @param data
     *            the data.
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public static void writeByteArrayToFile(String sFileName, byte[] data) throws IOException {

        if (StringUtils.isBlank(sFileName)) {
            return;
        }

        File file = new java.io.File(sFileName);

        writeByteArrayToFile(file, data);

    }

    /**
     * Copies a file to a directory preserving the file date. <br>
     * @param srcFile
     *            an existing file to copy, must not be null.
     * @param destDir
     *            the directory to place the copy in, must not be null.
     */
    public static void copyFileToDirectoryQuietly(File srcFile, File destDir) {

        try {

            copyFileToDirectory(srcFile, destDir);
        } catch (IOException e) {
            // quietly
        }

    }

    /**
     * List file names by dir path name.
     * @param filePath
     *            file dir.
     * @param recursive
     *            list dir recursively.
     * @return List<String>
     */
    public static List<String> listFileNamesQuietly(String filePath, boolean recursive) {

        List<String> fileNames = new ArrayList<>();

        try {
            File dir = new File(filePath);

            Collection<File> files = listFiles(dir, null, recursive);

            for (File file : files) {

                fileNames.add(file.getName());

            }

        } catch (Exception e) {
        	e.printStackTrace();
//            logger.error(e);
        }
        return fileNames;
    }

    /**
     * Force mkdir.
     * @param dirName
     *            the dir name.
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public static void forceMkdir(String dirName) throws IOException {
        if (StringUtils.isBlank(dirName)) {
            return;
        }

        dirName = FileNameUtilsEx.normalizeDir(dirName);
        File dir = new java.io.File(dirName);

        FileUtilsEx.forceMkdir(dir);
    }

    /**
     * <p>
     * 備份.
     * </p>
     * <pre>
     * backup("e:/tmp/a.txt", "e:/tmp/backup") => e:/tmp/backup/yyyyMMdd/a.txt.HHmmss
     * </pre>
     * @param srcFileName
     *            the src file name.
     * @param backupPath
     *            the backup path.
     */
    public static void backup(String srcFileName, String backupPath) {

        if (StringUtils.isBlank(srcFileName) || StringUtils.isBlank(backupPath)) {
            return;
        }

        File srcFile = new File(srcFileName);

        backup(srcFile, backupPath);
    }

    /**
     * <p>
     * 備份.
     * </p>
     * <pre>
     * backup("e:/tmp/a.txt", "e:/tmp/backup") => e:/tmp/backup/yyyyMMdd/a.txt.HHmmss
     * </pre>
     * @param srcFile
     *            the src file.
     * @param backupPath
     *            the backup path.
     */
    public static void backup(File srcFile, String backupPath) {

        if (null == srcFile || StringUtils.isBlank(backupPath)) {
            return;
        }

        if (!srcFile.exists()) {
            logger.warn("no source file = " + srcFile.getName());
            return;
        }

        // 備份附加檔名
        FastDateFormat formatter1 = FastDateFormat.getInstance("HHmmss");
        String ext = formatter1.format(new Date());

        try {

            // 產生備份目錄
            File backupDir = makeBackupDir(backupPath);

            backupFile(backupDir, srcFile, ext);

        } catch (IOException e) {
            logger.error("cannot backup file name = " + srcFile.getName(), e);
        }

    }

    /**
     * Backup File.
     * @param backupDir
     *            the backup dir.
     * @param srcFile
     *            the src file.
     * @param ext
     *            the ext.
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private static void backupFile(File backupDir, File srcFile, String ext) throws IOException {

        if (null == srcFile) {

            return;
        }

        if (!srcFile.exists()) {

            return;
        }

        String backupPath = FileNameUtilsEx.normalizeDir(backupDir.getPath());

        StringBuffer backupFileName = new StringBuffer();

        backupFileName.append(backupPath);
        backupFileName.append(srcFile.getName());
        backupFileName.append(".");
        backupFileName.append(ext);

        logger.info("backup file name = " + backupFileName);

        // Target File
        File backupFile = new File(backupFileName.toString());

        FileUtilsEx.copyFile(srcFile, backupFile);

        // Delete Source File
        FileUtilsEx.forceDelete(srcFile);
    }

    /**
     * 產生備份目錄.
     * <pre>
     * 
     * <$BAKDIR>\YYYYMMDD\
     * </pre>
     * @param backupPath
     *            the backup path.
     * @return the File
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    private static File makeBackupDir(String backupPath) throws IOException {

        backupPath = FileNameUtilsEx.normalizeDir(backupPath);

        // 備份目錄
        StringBuffer path = new StringBuffer();

        // 備份的開始目錄 <$BAKDIR>
        path.append(backupPath);

        // yyyyMMdd
        FastDateFormat formatter = FastDateFormat.getInstance("yyyyMMdd");

        path.append(formatter.format(new Date())).append("/");

        File backupDir = new File(path.toString());

        FileUtilsEx.forceMkdir(backupDir);

        return backupDir;
    }

    /**
     * 是否為可接受的ZIP檔(.CMS or .ZIP).
     * @param sFileType
     *            the s file type.
     * @return true, if is zip file type
     */
    public static boolean isZipFileType(String sFileType) {

        // 檔案類型為空不檢核
        if (StringUtils.isBlank(sFileType)) {
            return false;
        }

        return StringUtils.equalsIgnoreCase(sFileType, "ZIP") || StringUtils.equalsIgnoreCase(sFileType, "CMS");
    }

    /**
     * ZIP檔案格式檢核.
     * 不寫在isValidFileType內，因為isValidFileType為國外信用狀檢核圖檔(JPG,GIF...)與word，pdf專用，若加入ZIP格式可能會造成交易錯誤.
     * @param bFileContent
     *            the b file content.
     * @param sFileType
     *            the s file type.
     * @return true, if is valid zip file type
     */
    public static boolean isValidZipFileType(byte[] bFileContent, String sFileType) {
        // 檔案內容為空不檢核
        if (bFileContent == null || bFileContent.length == 0) {
            return false;
        }

        if (isZipFileType(sFileType)) {
            String sFileContentHex = ConvertUtilsEx.byteArray2HexString(bFileContent).toUpperCase();
            return sFileContentHex.startsWith("504B0304");
        }

        return false;
    }

}
