package com.dog.utils.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.Blob;
import java.sql.Clob;
import java.text.SimpleDateFormat;
import java.util.*;

public class ConvertUtil {

    private static Logger logger = LoggerFactory.getLogger(ConvertUtil.class);
    private static SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    private static final char[] HEXCHAR = {'0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static List<Map<String, String>> convertSqlMap2JavaMap(List<Map<String, Object>> list) {
        List beans = new ArrayList();
        try {
            for (Map map : list) {
                Map bean = new HashMap();
                Iterator iter = map.entrySet().iterator();
                while (iter.hasNext()) {
                    Map.Entry entry = (Map.Entry) iter.next();
                    Object key = entry.getKey();
                    Object val = entry.getValue();
                    if (val == null) {
                        bean.put(key.toString(), "");
                    } else if ((val instanceof Date)) {
                        val = dateFormat.format(val);
                        bean.put(key.toString(), val.toString());
                    } else if ((val instanceof Clob)) {
                        try {
                            val = clobToString((Clob) val);
                            bean.put(key.toString(), val.toString());
                        } catch (Exception e) {
                            logger.error("convertSqlMap2JavaMap", "clobToString", e);
                        }
                    } else if ((val instanceof Blob)) {
                        try {
                            val = blobToString((Blob) val);
                            bean.put(key.toString(), val.toString());
                        } catch (Exception e) {
                            logger.error("convertSqlMap2JavaMap", "blobToString", e);
                        }
                    } else if (val.getClass() == Byte.class){
                        try {
                            val = new String((byte[]) val, "UTF-8");
                            bean.put(key.toString(), val.toString());
                        } catch (UnsupportedEncodingException e) {
                            logger.error("convertSqlMap2JavaMap", "byte[]ToString", e);
                        }
                    } else if ((val instanceof Byte)) {
                        val = val.toString();
                        bean.put(key.toString(), val.toString());
                    } else {
                        val = String.valueOf(val);
                        bean.put(key.toString(), val.toString());
                    }

                }

                beans.add(bean);
            }
        } catch (Exception e) {
            logger.error("convertSqlMap2JavaMap", "", e);
        }
        return beans;
    }

    public static String blobToString(Blob blob)
            throws Exception {
        byte[] returnValue = blob.getBytes(1L, (int) blob.length());
        return bytes2String(returnValue);
    }

    public static String clobToString(Clob val)
            throws Exception {
        if (val == null) {
            return null;
        }
        Reader reader = null;
        StringBuffer buf = new StringBuffer();
        BufferedReader bfReader = null;
        try {
            reader = val.getCharacterStream();

            bfReader = new BufferedReader(reader);
            String s = bfReader.readLine();
            while (s != null) {
                buf.append(s);
                s = bfReader.readLine();
            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (bfReader != null)
                    bfReader.close();
            } catch (IOException e) {
                throw e;
            }
        }
        return buf.toString();
    }

    public static String bytes2String(byte[] bytes) {
        StringBuilder builder = new StringBuilder(bytes.length * 2);
        for (int i = 0; i < bytes.length; i++) {
            builder.append(HEXCHAR[((bytes[i] & 0xF0) >>> 4)]);
            builder.append(HEXCHAR[(bytes[i] & 0xF)]);
        }
        return builder.toString();
    }

    public static byte[] string2Bytes(String str) {
        byte[] bytes = new byte[str.length() / 2];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) Integer.parseInt(str.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

    public static InputStream string2InputStream(String str) {
        try {
            return new ByteArrayInputStream(string2Bytes(str));
        } catch (Exception e) {
            logger.error("string2InputStream", "", e);
            e.printStackTrace();
        }
        return null;
    }

    public static String file2String(File file)
            throws Exception {
        InputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[(int) file.length()];
        inputStream.read(bytes);
        return bytes2String(bytes);
    }

    public static String inputStream2String(InputStream inputStream) throws Exception {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inputStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in_b = swapStream.toByteArray();
        return bytes2String(in_b);
    }

    public static String getMethodByField(String field) {
        if (field == null) {
            return null;
        }
        return "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
    }


    public static String getErrorMsgByCode(String key) {
        Properties pps = new Properties();
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            if(loader == null) {
                loader = ConvertUtil.class.getClassLoader();
            }
            InputStream inputFile = loader.getResourceAsStream("exception/errors.properties");
            InputStream in = new BufferedInputStream (inputFile);
            pps.load(new InputStreamReader(in, "utf-8"));
            String value = pps.getProperty(key);
            return value;
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
