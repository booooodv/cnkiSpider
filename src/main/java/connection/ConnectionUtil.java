package connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 *  接收参数：String 一个网址
 *  返回参数：List<String> 搜索到的主题名列表
 */

public class ConnectionUtil {
    static int num = -1;
    static Proxy proxy;
    public static List<String> Connect(String address){
        HttpURLConnection conn = null;
        HttpURLConnection conn1 = null;
        URL url = null;
        InputStream in = null;
        InputStream in1 = null;
        BufferedReader reader = null;
        BufferedReader reader1 = null;
        StringBuffer stringBuffer = null;
        List list = new ArrayList();
        while (true){
            num ++;
            try {
               if (num%10 == 0){
                    URL url1 =new URL("http://dynamic.goubanjia.com/dynamic/get/dfce4d366ee0ba5d5ccb9bea6c973cdc%20%20.html?sep=3");
                    url = new URL(address);
                    conn1 = (HttpURLConnection) url1.openConnection();
                    conn1.setConnectTimeout(500);
                    conn1.setReadTimeout(500);
                    conn1.setDoInput(true);
                    conn1.connect();
                    in1 = conn1.getInputStream();
                    reader1 = new BufferedReader(new InputStreamReader(in1));
                    String addProxy = reader1.readLine();
                    String[] addProxys = addProxy.split(":");
                    InetSocketAddress addr = new InetSocketAddress(addProxys[0],Integer.parseInt(addProxys[1]));
                    proxy = new Proxy(Proxy.Type.HTTP, addr); // http 代理
                }
                conn = (HttpURLConnection) url.openConnection(proxy);
                conn.setConnectTimeout(1000);
                conn.setReadTimeout(2000);
                conn.setDoInput(true);
                conn.connect();
                in = conn.getInputStream();
                reader = new BufferedReader(new InputStreamReader(in));
                stringBuffer = new StringBuffer();
                String line = "";
                String lastLine = "";
                String title = "";
                boolean flag1 = true;
                while((line = reader.readLine()) != null){
                    //如果这是第一行就不操作，直接把lastline变为第一行
                    if (flag1){
                        lastLine = line;
                        flag1 = false;
                        continue;
                    }else {
                        //清空stringBuffer，然后把上一行和这一行成为一个字符串，用来寻找需要的
                        //lastLine 是为了找到我需要的<h3><a ....>xxxxxx</a>，因为h3和a标签不在一行，所以两行并为一行来找
                        stringBuffer.setLength(0);
                        //trim()去掉前后空格
                        stringBuffer.append(lastLine.trim());
                        stringBuffer.append(line.trim());
                        title = findTitle(stringBuffer);
                        if (!title.equals("")) {
                            //System.out.println(title);
                            list.add(title);
                        }
                        //System.out.println(stringBuffer.toString());
                        lastLine = line;
                    }
                    stringBuffer.append(line);
                }
            } catch (Exception e) {
//                e.printStackTrace();
                num = -1;
                continue;
            }
            break;
        }
        if (list == null){
            List<String> lis = new ArrayList<String>();
            return lis;
        }
        return list;
    }

    //传入合并两行后的字符串，找到对应的会返回其中的中文
    public static String findTitle(StringBuffer stringBuffer) throws IOException {
        String string = "";
        Pattern imgpattern=Pattern.compile("<h3><a.*</a>");
        Matcher imgmatcher=imgpattern.matcher(stringBuffer);
        while (imgmatcher.find()){
            String imgString=imgmatcher.group();
            int n=imgString.lastIndexOf("\">");
            string=imgString.substring(n+2,imgString.length()-4);
        }
        return string;
    }
}