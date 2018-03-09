package com.huamu668.fastdfsdemo.util;

import java.io.*;

public class FileUtil {

    /**
     * Description: 获取文件后缀名
     *
     * @param fileName
     * @return
     * @see
     */
    public static String getExtensionName(String fileName) {
        String prefix = fileName.substring(fileName.lastIndexOf(".") + 1);
        return prefix;
    }

    /**
     * Description: 获取文件.前名
     *
     * @param fileName
     * @return
     * @see
     */
    public static String getSimpleName(String fileName) {
        String prefix = fileName.substring(0,fileName.lastIndexOf("."));
        return prefix;
    }

    /**
     * 根据path获取文件名
     *
     * @param filename
     * @return
     * @author kokJuis
     * @version 1.0
     * @date 2016-12-12
     */
    public static String getOriginalFilename(String filename) {
        if (filename == null) return "";
        int pos = filename.lastIndexOf("/");
        if (pos == -1) pos = filename.lastIndexOf("\\");
        if (pos != -1)
            return filename.substring(pos + 1);
        else
            return filename;
    }

    /**
     * 获得指定文件的byte数组
     * @param filePath 文件绝对路径
     * @return
     */
    public static byte[] file2Byte(String filePath){
        ByteArrayOutputStream bos =null;
        BufferedInputStream   in  =null;
        try{
            File file=new File(filePath);
            if(!file.exists()){
                throw new FileNotFoundException("file not exists");
            }
            bos=new ByteArrayOutputStream((int)file.length());
            in=new BufferedInputStream(new FileInputStream(file));
            int buf_size=1024;
            byte[] buffer=new byte[buf_size];
            int len=0;
            while(-1 != (len=in.read(buffer,0,buf_size))){
                bos.write(buffer,0,len);
            }
            return bos.toByteArray();
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
        finally{
            try{
                if(in!=null){
                    in.close();
                }
                if(bos!=null){
                    bos.close();
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
    /**
     * 根据byte数组，生成文件
     * @param bfile 文件数组
     * @param filePath 文件存放路径
     * @param fileName 文件名称
     */
    public static void byte2File(byte[] bfile,String filePath,String fileName){
        BufferedOutputStream bos  =null;
        FileOutputStream     fos  =null;
        File                 file =null;
        try{
            File dir=new File(filePath);
            if(!dir.exists() && !dir.isDirectory()){//判断文件目录是否存在
                dir.mkdirs();
            }
            file=new File(filePath+fileName);
            fos=new FileOutputStream(file);
            bos=new BufferedOutputStream(fos);
            bos.write(bfile);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        finally{
            try{
                if(bos != null){
                    bos.close();
                }
                if(fos != null){
                    fos.close();
                }
            }
            catch(Exception e){
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
