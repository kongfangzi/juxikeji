package com.juxi.lingshibang.common.util;//package com.juxi.lingshibang.common.util;
//
//import com.github.tobato.fastdfs.domain.fdfs.MetaData;
//import com.github.tobato.fastdfs.domain.fdfs.StorePath;
//import com.github.tobato.fastdfs.domain.proto.storage.DownloadByteArray;
//import com.github.tobato.fastdfs.service.FastFileStorageClient;
//import com.github.tobato.fastdfs.service.TrackerClient;
//import org.apache.commons.fileupload.FileItem;
//import org.apache.commons.fileupload.FileItemFactory;
//import org.apache.commons.fileupload.disk.DiskFileItemFactory;
//import org.apache.commons.io.FilenameUtils;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.commons.CommonsMultipartFile;
//
//import javax.servlet.ServletOutputStream;
//import javax.servlet.http.HttpServletResponse;
//import java.io.*;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
///**
// * 文件处理公共类
// */
//@Component
//public class FastdfsUtils {
//
//
//    @Autowired
//    private FastFileStorageClient fastFileStorageClient;
//
//    private TrackerClient trackerClient;
//
//
//    /**
//     * 上传
//     *
//     * @param file
//     * @return
//     * @throws IOException
//     */
//    public List<StorePath> upload(MultipartFile[] file) throws IOException {
//        List<StorePath> list = new ArrayList<>();
//        for (MultipartFile a : file) {
//            list.add(upload(a));
//        }
//        return list;
//    }
//
//    /**
//     * 上传
//     *
//     * @param file
//     * @return
//     * @throws IOException
//     */
//    public StorePath upload(MultipartFile file) throws IOException {
//        Set<MetaData> metaDataSet = new HashSet<>();
//        metaDataSet.add(new MetaData("Author", "tobato"));
//        metaDataSet.add(new MetaData("CreateDate", LocalDateUtil.currentDateStr()));
//        // 上传
//        StorePath storePath = fastFileStorageClient.uploadFile(
//                file.getInputStream(), file.getSize(),
//                FilenameUtils.getExtension(file.getOriginalFilename()),
//                metaDataSet);
//        return storePath;
//    }
//
//    /**
//     * 删除
//     *
//     * @param path
//     */
//    public void delete(String path) {
//        fastFileStorageClient.deleteFile(path);
//    }
//
//    /**
//     * 删除
//     *
//     * @param group
//     * @param path
//     */
//    public void delete(String group, String path) {
//        fastFileStorageClient.deleteFile(group, path);
//    }
//
//    /**
//     * 文件下载
//     *
//     * @param path     文件路径
//     * @param filename 下载的文件命名
//     * @return
//     */
//    public InputStream download(String path, String filename) throws IOException {
//        // 获取文件
//        StorePath storePath = StorePath.parseFromUrl(path);
//        if (StringUtils.isBlank(filename)) {
//            filename = FilenameUtils.getName(storePath.getPath());
//        }
//        byte[] bytes = fastFileStorageClient.downloadFile(storePath.getGroup(), storePath.getPath(), new DownloadByteArray());
//        InputStream inputStream = new ByteArrayInputStream(bytes);
//        return inputStream;
//    }
//
//
//    /**
//     * 文件下载
//     *
//     * @param path     文件路径
//     * @param filename 下载的文件命名
//     * @return
//     */
//    public void download(String path, String filename, HttpServletResponse response) throws IOException {
//        // 获取文件
//        StorePath storePath = StorePath.parseFromUrl(path);
//        if (StringUtils.isBlank(filename)) {
//            filename = FilenameUtils.getName(storePath.getPath());
//        }
//        byte[] bytes = fastFileStorageClient.downloadFile(storePath.getGroup(), storePath.getPath(), new DownloadByteArray());
//        response.reset();
//        response.setContentType("applicatoin/octet-stream");
//        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8"));
//        ServletOutputStream out = response.getOutputStream();
//        out.write(bytes);
//        out.close();
//    }
//
//    public MultipartFile getMulFileByPath(String picPath) {
//        FileItem fileItem = createFileItem(picPath);
//        MultipartFile mfile = new CommonsMultipartFile(fileItem);
//        return mfile;
//    }
//
//    private FileItem createFileItem(String filePath) {
//        FileItemFactory factory = new DiskFileItemFactory(16, null);
//        String textFieldName = "textField";
//        int num = filePath.lastIndexOf(".");
//        String extFile = filePath.substring(num);
//        FileItem item = factory.createItem(textFieldName, "text/plain", true,
//                "MyFileName" + extFile);
//        File newfile = new File(filePath);
//        int bytesRead = 0;
//        byte[] buffer = new byte[8192];
//        try {
//            FileInputStream fis = new FileInputStream(newfile);
//            OutputStream os = item.getOutputStream();
//            while ((bytesRead = fis.read(buffer, 0, 8192))
//                    != -1) {
//                os.write(buffer, 0, bytesRead);
//            }
//            os.close();
//            fis.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return item;
//    }
//
//}
