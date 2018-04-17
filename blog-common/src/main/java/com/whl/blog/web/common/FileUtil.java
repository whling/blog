package com.whl.blog.web.common;

public class FileUtil {

    private static final String FOLDERPATH = BlogContext.FILEUPLOADPATH;

    public static String getUploadFolder() {
        return FOLDERPATH + DateUtil.getCurDateDD() + "/" + System.currentTimeMillis() + "/";
    }
}
