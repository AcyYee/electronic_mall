package com.sunwuo.electronic_mall.util;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Update by acy on 2017/8/3.
 */

public class FileUp {

    //图片类型文件
    public static final int IMAGE = 1;
    //音频类型文件
    public static final int AUDIO = 2;
    //视频类型文件
    public static final int VIDEO = 3;
    //文档类型文件
    public static final int DOCUMENT = 4;
    //其他类型文件
    public static final int OTHER = 5;

    
    private static int index=0;
    /**
     * 实现SpringMVC中文件的上传的功能
     *
     * @param files     上传的文件,类型:MultipartFile
     * @param dirNames  文件夹名,多层文件夹名用,号隔开例如:"1,2,3"
     * @param type      类型 图片为1,音频为2,视频为3,文档为4,其他为5 可使用类内的静态变量
     * @param isNewName 是否使用新文件名 boolean 是为true
     * @return 返回(文件夹名和文件名)或者空 例如:["/dsda/1.jpg","/dsda/2.jpg","/dsda/3.jpg"]
     */
    public static void UpFiles(MultipartFile[] files, HttpServletRequest request, String dirNames, int type, boolean isNewName, ResultObject[] rs) {
        for (int i = 0; i < files.length; i++) {
            rs[i].setCode(UpFile(files[i], request, dirNames, type, isNewName, rs[i]));
        }
    }

    public static int UpFile(MultipartFile file, HttpServletRequest request, String dirName, int type, boolean isNewName, ResultObject rs) {
        String newName;
        String[] newDirs;
        if (file == null || file.isEmpty()) {
            rs.setResult("文件为空");
            return ResultObject.RESULT_ERROR;
        }
        int index = file.getOriginalFilename().lastIndexOf(".");
        String postfix = file.getOriginalFilename().substring(index);

        if (!isTrueType(type, postfix)) {
            rs.setResult("文件格式不正确");
            return  ResultObject.RESULT_ERROR;
        }
        if (isNewName) {
            newName = newFileName(type, postfix);
        } else {
            newName = file.getOriginalFilename();
        }
        String path = request.getSession().getServletContext().getRealPath("/");
        newDirs = dirName.split(",");
        StringBuilder dirNames = new StringBuilder("");
        if (newDirs.length > 1) {
            for (String newDir : newDirs) {
                dirNames.append("/");
                dirNames.append(newDir);
            }
        } else {
            dirNames.append("/");
            dirNames.append(dirName);
        }
        StringBuilder builder = new StringBuilder(path);
        builder.append(dirNames);
        File temp = new File(builder.toString().trim());
        File temp2 = new File(builder.toString(), newName.trim());
        if (!temp.exists()) {
            if (!temp.mkdirs()) {
                rs.setResult("无法创建文件夹");
                return ResultObject.RESULT_ERROR;
            }
        }
        try {
            file.transferTo(temp2);
            if(type==1)
            Thumbnails.of(temp2).scale(1.0f).toFile(temp2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        rs.setResult(dirNames.toString() + "/" + newName);
        return ResultObject.RESULT_SUCCESS;
    }

    private static boolean isTrueType(int type, String postfix) {
        if (type == IMAGE) {
            if (!".jpg".equals(postfix) && !".jpeg".equals(postfix) && !".png".equals(postfix) && !".ico".equals(postfix) && !".gif".equals(postfix)) {
                return false;
            }
        } else if (type == AUDIO) {
            if (!postfix.equals(".mp3") && !postfix.equals(".silk") && !postfix.equals(".wav")) {
                return false;
            }
        } else if (type == VIDEO) {
            if (!postfix.equals(".mp4") && !postfix.equals(".flv") && !postfix.equals(".avi")) {
                return false;
            }
        } else if (type == DOCUMENT) {
            if (!postfix.equals(".doc") && !postfix.equals(".ppt") && !postfix.equals(".xls") && !postfix.equals(".docx") && !postfix.equals(".pptx") && !postfix.equals(".xlsx") && !postfix.equals(".txt") && !postfix.equals(".pdf")) {
                return false;
            }
        } else return type == OTHER;
        return true;
    }

    public static String makeNewDir(HttpServletRequest request, String dirName) {
        StringBuilder path = new StringBuilder(request.getSession().getServletContext().getRealPath("/"));
        StringBuilder dirNames = new StringBuilder();
        String[] newDirs = dirName.split(",");
        if (newDirs.length > 1) {
            dirNames.append("");
            for (String newDir : newDirs) {
                dirNames.append("/");
                dirNames.append(newDir);
            }
        } else {
            dirNames.append("/");
            dirNames.append(dirName);
        }
        path.append(dirNames.toString());
        File temp = new File(path.toString().trim());
        if (!temp.exists()) {
            if (!temp.mkdirs()) {
                request.getSession().setAttribute("error", "无法创建文件夹");
            }
        }
        return dirNames.toString();
    }

    private synchronized static String newFileName(int type, String postfix) {
    		if (type == IMAGE) {
    			index++;
    			return "image" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+"-"+index + postfix;
    		} else if (type == AUDIO) {
    			index++;
    			return "audio" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+"-"+index + postfix;
    		} else if (type == VIDEO) {
    			index++;
    			return "video" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+"-"+index + postfix;
    		} else if (type == DOCUMENT) {
    			index++;
    			return "document" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+"-"+index + postfix;
    		} else {
    			index++;
    			return "file" + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+"-"+index + postfix;
    		}
    }

    public static void deleteFolder(String deletePath, HttpServletRequest request) {
        StringBuilder path = new StringBuilder(request.getSession().getServletContext().getRealPath("/"));
        StringBuilder dirNames = new StringBuilder("");
        String[] newDirs = deletePath.split(",");
        if (newDirs.length > 1) {
            for (int j = newDirs.length - 1; j >= 0; j--) {
                dirNames.append("/");
                dirNames.append(newDirs[j]);
            }
        }
        path.append(dirNames);
        System.out.println(path);
        File temp = new File(path.toString().trim());
        temp.delete();
    }

    public static String[] rename(String path, int type, String oldName, String newName, HttpServletRequest request) {
        String[] temp = new String[2];
        StringBuilder dirNames = new StringBuilder("");
        String[] newDirs = path.split(",");
        if (newDirs.length > 1) {
            for (int j = newDirs.length - 1; j >= 0; j--) {
                dirNames.append("/");
                dirNames.append(newDirs[j]);
            }
        }
        if (type == 1) {
            String realPath = request.getServletContext().getRealPath("/") + dirNames;
            File file1 = new File(realPath + "/" + oldName);
            //将原文件夹更改为A，其中路径是必要的。注意
            file1.renameTo(new File(realPath + "/" + newName));
            temp[0] = file1.getName();
            temp[1] = dirNames + "/" + newName;
        } else {
            String realPath = request.getServletContext().getRealPath("/") + dirNames;
            if (newName.contains(".")) {
                File file1 = new File(realPath + "/" + oldName);
                //将原文件夹更改为A，其中路径是必要的。注意
                file1.renameTo(new File(realPath + "/" + newName));
                temp[0] = newName;
                temp[1] = dirNames + "/" + newName;
            } else {
                int index = oldName.lastIndexOf(".");
                String postfix = oldName.substring(index);
                File file1 = new File(realPath + "/" + oldName);
                //将原文件夹更改为A，其中路径是必要的。注意
                file1.renameTo(new File(realPath + "/" + newName + postfix));
                temp[0] = newName + postfix;
                temp[1] = dirNames + "/" + newName + postfix;
            }
        }
        return temp;
    }

    /**
     * 压缩图片
     *
     * @param file 文件路径
     * @param size 文件大小
     */
    public static void compress(String file, long size) {
        try {
            if (size < 50)
                Thumbnails.of(file).scale(1.0f).toFile(file);
            if (size >= 50 && size < 200)
                Thumbnails.of(file).scale(0.8f).toFile(file);
            if (size >= 200 && size < 500)
                Thumbnails.of(file).scale(0.6f).toFile(file);
            if (size >= 500 && size < 800)
                Thumbnails.of(file).scale(0.4f).toFile(file);
            if (size >= 800)
                Thumbnails.of(file).scale(0.3f).toFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}