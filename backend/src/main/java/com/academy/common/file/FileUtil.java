package com.academy.common.file;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 파일 업로드 유틸리티
 * @author Y.K.KIM
 * @since 2025.11
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      수정자           수정내용
 *  -------    --------    ---------------------------
 *   2025.11  Y.K.KIM          최초 생성
 *
 * </pre>
 */
@Component("fileUtil")
public class FileUtil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Value("${file.upload.path:C:/upload/}")
    private String uploadPath;

    /**
     * 현재 날짜시간을 포맷팅하여 반환
     * @return yyyyMMddHHmmssSSS 포맷의 문자열
     */
    private String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(new Date());
    }

    /**
     * 이미지를 리사이징한다.
     * @param originalImage 원본 이미지
     * @param targetWidth 목표 너비
     * @param targetHeight 목표 높이
     * @return 리사이징된 이미지
     */
    private BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) {
        Image scaledImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = resizedImage.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawImage(scaledImage, 0, 0, null);
        g2d.dispose();

        return resizedImage;
    }

    public List<HashMap<String, Object>> uploadFiles(List<MultipartFile> uploadFileList, String rootPath, String subPath) throws IllegalStateException, IOException {
        List<HashMap<String, Object>> fileInfoList = new ArrayList<HashMap<String, Object>>();

        for(MultipartFile file:uploadFileList) {
            HashMap<String, Object> info = uploadFile(file, rootPath, subPath);
            fileInfoList.add(info);
        }

        return fileInfoList;
    }

    public HashMap<String, Object> uploadFile(MultipartFile uploadFile, String rootPath, String subPath) throws IllegalStateException, IOException {
        HashMap<String, Object> info = new HashMap<String, Object>();
        String path = rootPath + subPath;
        if(!uploadFile.isEmpty()) {
            File dir = new File(path);
            if(!dir.exists()) dir.mkdirs();

            String originalFileName = uploadFile.getOriginalFilename();
            if (originalFileName == null || originalFileName.isEmpty()) {
                return info;
            }
            String originalFileExt = originalFileName.substring(originalFileName.lastIndexOf("."));
            String newFileName = getCurrentDateTime() + originalFileExt;

            String fileFullPath = path + newFileName;
            String fileSubPath = subPath + newFileName;

            File newFile = new File(fileFullPath);
            uploadFile.transferTo(newFile);

            info.put("fileName", 		  originalFileName);
            info.put("fileFullPath", 	  fileSubPath);
            info.put("fileSize", 		  String.valueOf(uploadFile.getSize()));
            info.put("fileExt",           originalFileExt);
            info.put("fileNewName", newFileName);
        }

        return info;
    }

    public HashMap<String, Object> uploadFile(MultipartFile uploadFile, String rootPath, String subPath, int width, int height, String opt)
            throws IllegalStateException, IOException {

        HashMap<String, Object> info = new HashMap<String, Object>();
        String path = rootPath + subPath;
        if(!uploadFile.isEmpty()) {
            File dir = new File(path);
            if(!dir.exists()) dir.mkdirs();

            String originalFileName = uploadFile.getOriginalFilename();
            if (originalFileName == null || originalFileName.isEmpty()) {
                return info;
            }
            String originalFileExt = originalFileName.substring(originalFileName.lastIndexOf(".")+1);
            String newFileName = getCurrentDateTime() + "." + originalFileExt;

            String fileFullPath = path + newFileName;
            File newFile = new File(fileFullPath);
            uploadFile.transferTo(newFile);

            if(width == 0) width = 32;
            if(height == 0) height = 32;

            BufferedImage orgImage = ImageIO.read(newFile);
            BufferedImage thumbImg = resizeImage(orgImage, width, height);

            String thumbFileName = getCurrentDateTime()+"_thumb"+width+"x"+height+ "." + originalFileExt;
            String thumbFullPath = path + thumbFileName;
            String thumbSubPath = subPath + thumbFileName;
            File thumbFile = new File(thumbFullPath);
            ImageIO.write(thumbImg, originalFileExt, thumbFile);

            info.put("fileName",       originalFileName);
            info.put("fileFullPath",    thumbSubPath);
            info.put("fileSize",         String.valueOf(uploadFile.getSize()));
            info.put("fileExt",           "."+originalFileExt);
            info.put("fileNewName", thumbFileName);
        }

        return info;
    }

    public HashMap<String, Object> uploadFileForRealName(MultipartFile uploadFile, String rootPath, String subPath) throws IllegalStateException, IOException {
        HashMap<String, Object> info = new HashMap<String, Object>();
        String path = rootPath + subPath;
        if(!uploadFile.isEmpty()) {
            File dir = new File(path);
            if(!dir.exists()) dir.mkdirs();

            String originalFileName = uploadFile.getOriginalFilename();
            if (originalFileName == null || originalFileName.isEmpty()) {
                return info;
            }
            String originalFileExt = originalFileName.substring(originalFileName.lastIndexOf("."));
            String newFileName = getCurrentDateTime() + originalFileExt;

            String fileFullPath = path + originalFileName;
            String fileSubPath = subPath + originalFileName;

            File newFile = new File(fileFullPath);
            uploadFile.transferTo(newFile);

            info.put("fileName", 		  originalFileName);
            info.put("fileFullPath", 	  fileSubPath);
            info.put("fileSize", 		  String.valueOf(uploadFile.getSize()));
            info.put("fileExt",           originalFileExt);
            info.put("fileNewName", newFileName);
        }

        return info;
    }

    public List<HashMap<String, Object>> uploadFilesWthField(List<MultipartFile> uploadFileList, String rootPath, String subPath) throws IllegalStateException, IOException {
        List<HashMap<String, Object>> fileInfoList = new ArrayList<HashMap<String, Object>>();

        for(MultipartFile file:uploadFileList) {
            HashMap<String, Object> info = uploadFileWthField(file, rootPath, subPath);
            fileInfoList.add(info);
        }

        return fileInfoList;
    }

    public HashMap<String, Object> uploadFileWthField(MultipartFile uploadFile, String rootPath, String subPath) throws IllegalStateException, IOException {
        HashMap<String, Object> info = new HashMap<String, Object>();
        String path = rootPath + subPath;
        if(!uploadFile.isEmpty()) {
            File dir = new File(path);
            if(!dir.exists()) dir.mkdirs();

            String originalFileName = uploadFile.getOriginalFilename();
            if (originalFileName == null || originalFileName.isEmpty()) {
                return info;
            }
            String originalFileExt = originalFileName.substring(originalFileName.lastIndexOf("."));
            String newFileName = getCurrentDateTime() + originalFileExt;

            String fileFullPath = path + newFileName;
            String fileSubPath = subPath + newFileName;

            File newFile = new File(fileFullPath);
            uploadFile.transferTo(newFile);

            info.put("fileName",       originalFileName);
            info.put("fileFullPath",    fileSubPath);
            info.put("fileSize",         String.valueOf(uploadFile.getSize()));
            info.put("fileExt",           originalFileExt);
            info.put("fileNewName", newFileName);

            // Note: File ID should be set by the caller if needed
            // DB insertion should be handled by the service layer
        }

        return info;
    }

    public void deleteFile(String fileFullPath) throws IllegalStateException, IOException {
        File f = new File(fileFullPath);
        if(f.exists())
            f.delete();
    }

    /** +
     * MethodName   : filemake(String filename, String msg)
     * Description  : 파일 업로드
     * @param       :
     * @return      :
     * @exception   :
     */
    public void filemake(String filename, String desc, String subPath)
    {
        PrintWriter logfile = null;
        boolean isappend = true;
        try {
            String rootPath = uploadPath + subPath;
            File dir = new File(rootPath);
            if(!dir.exists()) dir.mkdirs();
            logfile = new PrintWriter(new FileWriter(rootPath + filename, isappend), true);
            logfile.println(desc);
            logfile.flush();
        } catch (Exception e)  {
            if(logfile != null) logfile.close();
            e.printStackTrace();
        } finally {
            if(logfile != null) logfile.close();
        }
    }

}
