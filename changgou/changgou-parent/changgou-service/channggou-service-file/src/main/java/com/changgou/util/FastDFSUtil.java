package com.changgou.util;


import com.changgou.file.FastDFSFile;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * 实现fastDFS文件管理
 * 文件上传
 * 文件删除
 * 文件下载
 * 文件信息获取
 * Stroage信息获取
 * Tracker信息获取
 */
public class FastDFSUtil {
    /**
     * 加载tracker 链接信息
     */
    static{
        try {
//             String fileName = "D:\\javaweb\\changgou\\changgou\\changgou-parent\\changgou-service\\channggou-service-file\\src\\main\\resources\\fdfs_client.conf";
              String path = new ClassPathResource("fdfs_client.conf").getPath();
              //加载tracker信息
              ClientGlobal.init(path);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件上传
     * @param fastDFSFile
     * @return
     */
    public static String[] upload(FastDFSFile fastDFSFile) throws Exception {

        ///附加参数
        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("拍摄作者",fastDFSFile.getAuthor());

        //创建一个Tracker 访问的客户端对象TrackerClient
        TrackerClient trackerClient = new  TrackerClient();

        //通过TrackerClient 访问TrackerServer服务，获取链接信息
        TrackerServer trackerServer = trackerClient.getConnection();

        //通过TrackerServer的链接信息可以获取Stroage的连接信息 ，创建StroageClient对象存储Stroage链接信息
        StorageClient storageClient =  new StorageClient(trackerServer,null);

        /**
         * * 通过StroageClient访问Stroage,实现文件上传，并获取文件上传后的存储信息
         * 1上传文件的字节内容
         * 2文件拓展名
         * 3附加参数
         */

        String[] uploads  = new String[0];
        try {
            uploads = storageClient.upload_file(fastDFSFile.getContent(),fastDFSFile.getName(),meta_list);
            System.out.println("uploads"+uploads);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
        return uploads;
    }

    /**
     * 获取文件信息
     * @param groupName  组名
     * @param  remoteFileName 文件存储路径名
     */
    public static FileInfo getFile(String groupName, String remoteFileName) throws IOException, MyException {
        //创建trackeClient对象 ，通过trackeClient对象访问TrackerServer

        TrackerClient trackerClient = new TrackerClient();
        //通过trackerClinent获取trackerServer的链接对象
        TrackerServer trackerServer = trackerClient.getConnection();
        //通过trackerServer获取storage信息，创建StorageClient对象存储Storage信息
        StorageClient storageClient = new StorageClient(trackerServer,null);

        //获取文件信息
        return  storageClient.get_file_info(groupName,remoteFileName);
    }
    public static void main(String[] args){
        try {
            FileInfo fileInfo = getFile("group1","M00/00/wKjThF1FBz-ARyy5AAoAAFSKsG8238.jpg");
            System.out.println(fileInfo.getSourceIpAddr());
            System.out.println(fileInfo.getFileSize());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (MyException e) {
            e.printStackTrace();
        }
    }
}
