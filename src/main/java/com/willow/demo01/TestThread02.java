package com.willow.demo01;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.net.URL;

/**
 * 练习，实现多线程下载图片
 * 继承Thread
 *
 * @author MrWillow
 */
public class TestThread02 extends Thread{

    private String url;
    private String fileName;

    public TestThread02(String url, String fileName) {
        this.url = url;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        WebDownloader downloader = new WebDownloader();
        downloader.download(url, fileName);
        System.out.println(fileName + "下载完成！");
    }

    public static void main(String[] args) {
        TestThread02 t1 = new TestThread02("https://gitee.com/bbttboy/pic_bed/raw/master/image-20220110152541076.png", "com/willow/demo01/images02/1.png");
        TestThread02 t2 = new TestThread02("https://gitee.com/bbttboy/pic_bed/raw/master/image-20220110145206584.png", "com/willow/demo01/images02/2.png");
        TestThread02 t3 = new TestThread02("https://gitee.com/bbttboy/pic_bed/raw/master/image-20211229100048659.png", "com/willow/demo01/images02/3.png");
        TestThread02 t4 = new TestThread02("https://gitee.com/bbttboy/pic_bed/raw/master/image-20211230175958102.png", "com/willow/demo01/images02/4.png");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}

/**
 * 图片下载器
 *
 * @author MrWillow
 */
class WebDownloader {

    /**
     * 下载
     */
    public void download(String url, String fileName) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(fileName));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("IO异常，download方法出现问题！");
        }
    }
}
