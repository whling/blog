package com.whl.blog.common;

public class FileUtil {

    private static final String FOLDERPATH = BlogContext.FILEUPLOADPATH;

    public static String getUploadFolder() {
        return FOLDERPATH + DateUtil.getCurDateDD() + "/" + System.currentTimeMillis() + "/";
    }
}
