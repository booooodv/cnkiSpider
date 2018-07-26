package main;

import connection.ConnectionUtil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static file.ReadTxt.readTxtFile;
import static file.WriteTxt.writeTxt;

public class Analyze {
    public static void main(String[] args) throws InterruptedException {
        ConnectionUtil connectionUtil = new ConnectionUtil();
        //        String add = "http://search.cnki.net/Search.aspx?q=基金：01ADJ001";
        //        List list = new ConnectionUtil().Connect(add.substring(0,37)+getURLEncoderString(add.substring(37)));
        //System.out.println(list);
        String inputFile = "C:\\Users\\nik\\Desktop\\now\\爬虫\\查询网址.txt";
        String outputFile = "C:\\Users\\nik\\Desktop\\now\\爬虫\\result.txt";
        //从文件里得到地址
        List<String> addresses = readTxtFile(inputFile);
        List<String> resultList = new ArrayList();
        int i = 0;
        for (String address : addresses) {    //得到每个地址，用来
            System.out.println(address);
            resultList = connectionUtil.Connect(address.substring(0, 37) + getURLEncoderString(address.substring(37)));
            for (String result : resultList) {
                writeTxt(outputFile, result, address.substring(37));
                System.out.println("第" + i + "条记录找到啦~~~");
                i++;
            }
            Thread.sleep(100);
        }
    }



    public static String getURLEncoderString(String str) {

        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            //e.printStackTrace();
        }
        return result;
    }

}