package com.example.genericdawawalauser.utils;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.CancellationSignal;
import android.provider.MediaStore;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MediaStoreUtil {
    public static void writeImageToMediaStoreForQ(Context context, String filePath, String relativePath) {
        Uri uri = Uri.parse(filePath);
        ContentValues values = new ContentValues();
        values.put("_display_name", uri.getLastPathSegment());
        values.put("mime_type", "image/*");
        values.put("relative_path", relativePath);
        values.put("is_pending", 1);
        File file = new File(filePath);
        Uri item = context.getContentResolver().insert(MediaStore.Images.Media.getContentUri("external_primary"), values);
        if (item != null) {
            try {
                FileOutputStream outputStream = new FileOutputStream(context.getContentResolver().openFileDescriptor(item, "w", (CancellationSignal) null).getFileDescriptor());
                byte[] buffer = new byte[1024];
                FileInputStream inputStream = new FileInputStream(file);
                while (inputStream.read(buffer, 0, buffer.length) != -1) {
                    outputStream.write(buffer);
                }
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            values.clear();
            values.put("is_pending", 0);
            context.getContentResolver().update(item, values, (String) null, (String[]) null);
        }
    }

    public static void writeVideoToMediaStoreForQ(Context context, String path, String relativePath) {
        Uri uri = Uri.parse(path);
        ContentValues values = new ContentValues();
        values.put("_display_name", uri.getLastPathSegment());
        values.put("mime_type", "video/*");
        values.put("relative_path", relativePath);
        values.put("is_pending", 1);
        File file = new File(path);
        Uri item = context.getContentResolver().insert(MediaStore.Video.Media.getContentUri("external_primary"), values);
        if (item != null) {
            try {
                FileOutputStream outputStream = new FileOutputStream(context.getContentResolver().openFileDescriptor(item, "w", (CancellationSignal) null).getFileDescriptor());
                byte[] buffer = new byte[1024];
                FileInputStream inputStream = new FileInputStream(file);
                while (inputStream.read(buffer, 0, buffer.length) != -1) {
                    outputStream.write(buffer);
                }
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            values.clear();
            values.put("is_pending", 0);
            context.getContentResolver().update(item, values, (String) null, (String[]) null);
        }
    }
}
