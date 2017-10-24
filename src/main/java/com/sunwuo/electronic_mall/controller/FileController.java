package com.sunwuo.electronic_mall.controller;

import com.sunwuo.electronic_mall.util.FileUp;
import com.sunwuo.electronic_mall.util.ResultObject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("file")
public class FileController {

	/**
	 * 单文件上传
	 * @param file 文件对象
	 * @param type 文件类型
	 * @return 返回结果
	 */
    @PostMapping("upload")
    public ResultObject upload(MultipartFile file, Integer type, HttpServletRequest request, HttpServletResponse rep){
        rep.setHeader("Access-Control-Allow-Origin","*");
        ResultObject rs = new ResultObject();
        rs.setCode(FileUp.UpFile(file,request,"upload,resources",type,true,rs));
        return rs;
    }
    

    /**
	 * 多文件上传s
	 * @param type 文件类型
	 * @return 返回结果
	 */
    @PostMapping("uploads")
    public ResultObject[] uploads(MultipartFile[] files, Integer type, HttpServletRequest request){
        ResultObject[] rs = new ResultObject[files.length];
        FileUp.UpFiles(files,request,"upload,resources",type,true,rs);
        return rs;
    }

}
