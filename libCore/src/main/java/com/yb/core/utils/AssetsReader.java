package com.yb.core.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;


public class AssetsReader {

    /**
     * 读取assets下的csv文件
     */
    public static LinkedList<String> readAssetsCsv(Context context, String fileName) {
        LinkedList<String> lineList = new LinkedList<>();
        try {
            InputStream is = context.getAssets().open(fileName + ".csv");
            InputStreamReader isr = new InputStreamReader(is, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String strLine = null;
            while ((strLine = br.readLine()) != null) {
                //   Log.e("lyn_readLine", strLine);
                lineList.add(strLine);
            }
            is.close();
            isr.close();
            br.close();
            return lineList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 得到json文件中的内容
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getJson(Context context, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        //获得assets资源管理器
        AssetManager assetManager = context.getAssets();
        //使用IO流读取json文件内容
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName), "utf-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
