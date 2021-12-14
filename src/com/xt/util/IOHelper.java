package com.xt.util;

import javax.imageio.ImageIO;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

public class IOHelper {

    /**
     * 把CLASS文件转成BYTE
     *
     * @throws Exception
     */
    public static byte[] getClassFileBytes(File file) throws Exception {
        byte[] cLassBytes = null;
//        Path path;
//        try {
//            path = Paths.get(new URI("file:///D:/develop_workspace/ActivityProxy.class"));
//            cLassBytes = Files.readAllBytes(path);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return cLassBytes;
//
//        //采用NIO读取
        FileInputStream fis = new FileInputStream(file);
        FileChannel fileC = fis.getChannel();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        WritableByteChannel outC = Channels.newChannel(baos);
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        while (true) {
            int i = fileC.read(buffer);
            if (i == 0 || i == -1) {
                break;
            }
            buffer.flip();
            outC.write(buffer);
            buffer.clear();
        }
        fis.close();
        return baos.toByteArray();
    }

    /**
     * @return
     */
    public static void fromIsToOsByCode(InputStream is, OutputStream os,
                                        String incode, String outcode) {
//        BufferedReader reader = null;
//        BufferedWriter writer = null;
//        try {
//            reader = new BufferedReader(new InputStreamReader(is, incode));
//            writer = new BufferedWriter(new OutputStreamWriter(os, outcode));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                writer.write(line + "\n");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                reader.close();
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is, incode))) {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, outcode));
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String readStrByCode(InputStream is, String code)
            throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = null;

        reader = new BufferedReader(new InputStreamReader(is, code));
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line + "\n");
        }
        try {
            reader.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static String fromIputStreamToString(InputStream is) {
        if (is == null)
            return null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i = -1;
        try {
            while ((i = is.read()) != -1) {
                baos.write(i);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return baos.toString();
    }

    public static List<String> readListStrByCode(InputStream is, String code)
            throws IOException {
        List<String> list = new ArrayList<>();
        BufferedReader reader = null;

        reader = new BufferedReader(new InputStreamReader(is, code));
        String line;
        while ((line = reader.readLine()) != null) {
            list.add(line);
        }
        try {
            reader.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    public static String listToStr(List<String> list) {
        StringBuilder builder = new StringBuilder();

        for (String line : list) {
            builder.append(line);
            builder.append("\n");
        }
        return builder.toString();
    }

    public static int[] getImageInfoByUrl(String urlStr) {
        int[] wh = new int[2];

        wh = getImageInfoByUrl1(urlStr);
        if (wh[0] != 0) {
            return wh;
        }
        wh = getImageInfoByUrl1(urlStr);
        if (wh[0] != 0) {
            return wh;
        }
        wh = getImageInfoByUrl2(urlStr);
        if (wh[0] != 0) {
            return wh;
        }

        return wh;
    }

    public static int[] getImageInfoByUrl1(String urlStr) {
        int[] wh = new int[2];
        try {
            BufferedImage read = ImageIO.read(new URL(urlStr));
            if (read == null) {
                return wh;
            }
            int height = read.getHeight();
            int width = read.getWidth();
            wh[0] = width;
            wh[1] = height;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
        return wh;
    }

    public static int[] getImageInfoByUrl2(String urlStr) {
        int[] wh = new int[2];
        InputStream is = null;
        try {
            is = new URL(urlStr).openStream();
            byte[] bytes = new byte[30];
            is.read(bytes, 0, bytes.length);
            int width = ((int) bytes[27] & 0xff) << 8 | ((int) bytes[26] & 0xff);
            int height = ((int) bytes[29] & 0xff) << 8 | ((int) bytes[28] & 0xff);
            wh[0] = width;
            wh[1] = height;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
        return wh;
    }


    public static boolean fromIputStreamToFile(InputStream is,
                                               String outfilepath) {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;

        try {
            inBuff = new BufferedInputStream(is);

            outBuff = new BufferedOutputStream(
                    new FileOutputStream(outfilepath));

            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            outBuff.flush();
        } catch (Exception e) {
            return false;
        } finally {
            try {
                if (inBuff != null)

                    inBuff.close();
                if (outBuff != null)
                    outBuff.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    public static InputStream fromFileToIputStream(String infilepath) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(new File(infilepath));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return fis;
    }

    public static InputStream fromFileToIputStream(File file) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return fis;
    }

    public static InputStream fromStringToIputStream(String s) {
        if (s != null && !s.equals("")) {
            try {

                ByteArrayInputStream stringInputStream = new ByteArrayInputStream(
                        s.getBytes());
                return stringInputStream;
            } catch (Exception e) {

                e.printStackTrace();
            }
        }
        return null;
    }

    public static InputStream getInputStreamFromUrl(String urlstr) {
        try {
            InputStream is = null;
            HttpURLConnection conn = null;
            System.out.println("urlstr:" + urlstr);
            URL url = new URL(urlstr);
            conn = (HttpURLConnection) url.openConnection();
            if (conn.getResponseCode() == 200) {
                is = conn.getInputStream();
                return is;
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public static void writerStrByCode(OutputStream os, String outcode,
                                       String str) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(os, outcode));
            writer.write(str);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param file
     * @param outcode
     * @param append    是否追加
     * @param str
     */
    public static void writerStrByCodeToFile(File file, String outcode,
                                             boolean append, String str) {
        BufferedWriter writer = null;
        try {
            FileOutputStream out = new FileOutputStream(file, append);
            writer = new BufferedWriter(new OutputStreamWriter(out, outcode));
            writer.write(str);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class TrustAnyTrustManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }

    private static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }
}
