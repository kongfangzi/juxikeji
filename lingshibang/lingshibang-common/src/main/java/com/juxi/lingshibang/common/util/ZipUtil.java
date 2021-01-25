package com.juxi.lingshibang.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.*;

/**
 * @date 2019-03-21
 */
public final class ZipUtil {

    public static class GZip {
        public static byte[] compress(byte[] bytes) {
            byte[] data = null;
            try {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
                gzipOutputStream.write(bytes);
                gzipOutputStream.finish();
                gzipOutputStream.close();
                data = outputStream.toByteArray();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }
        public static byte[] decompress(byte[] bytes) {
            byte[] data = null;
            try {
                ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
                GZIPInputStream gzipInputStream = new GZIPInputStream(inputStream);
                byte[] buf = new byte[1024];
                int num = -1;
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                while ((num = gzipInputStream.read(buf, 0, buf.length)) != -1) {
                    outputStream.write(buf, 0, num);
                }
                data = outputStream.toByteArray();
                outputStream.flush();
                outputStream.close();
                gzipInputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }
    }

    public static class Zip {
        public static byte[] compress(byte[] bytes) {
            byte[] data = null;
            try {
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
                ZipEntry entry = new ZipEntry("zip");
                entry.setSize(bytes.length);
                zipOutputStream.putNextEntry(entry);
                zipOutputStream.write(bytes);
                zipOutputStream.closeEntry();
                zipOutputStream.close();
                data = outputStream.toByteArray();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }
        public static byte[] decompress(byte[] bytes) {
            byte[] data = null;
            try {
                ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
                ZipInputStream zipInputStream = new ZipInputStream(inputStream);
                while (zipInputStream.getNextEntry() != null) {
                    byte[] buf = new byte[1024];
                    int num = -1;
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    while ((num = zipInputStream.read(buf, 0, buf.length)) != -1) {
                        outputStream.write(buf, 0, num);
                    }
                    data = outputStream.toByteArray();
                    outputStream.flush();
                    outputStream.close();
                }
                zipInputStream.close();
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }
    }

    public static Long getCRC32(byte[] data) {
        CRC32 crc32 = new CRC32();
        Long crc = null;
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
            CheckedInputStream checkedInputStream = new CheckedInputStream(inputStream, crc32);
            while (checkedInputStream.read() != -1) {};
            checkedInputStream.close();
            inputStream.close();
            crc = crc32.getValue();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return crc;
    }
}
