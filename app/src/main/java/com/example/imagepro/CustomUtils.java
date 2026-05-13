package com.example.imagepro;

import android.content.Context;
import android.content.pm.PackageManager;
import android.widget.EditText;

import androidx.core.content.ContextCompat;

public class CustomUtils {

    public static boolean isNotEmpty(String str) {
        return str != null && !str.trim().isEmpty();
    }

    public static String getString(EditText editText) {
        return editText.getText().toString().trim();
    }

    public static boolean checkPermission(Context context, String permission) {
        int result = ContextCompat.checkSelfPermission(context, permission);
        return result == PackageManager.PERMISSION_GRANTED;
    }
}

