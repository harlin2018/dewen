package com.dewen.project.utils;

import java.awt.*;
import java.io.InputStream;

public class FontUtils {

    public Font loadFont(){

        String fontfilename = "/Font/simhei.ttf";
        InputStream is = this.getClass().getResourceAsStream(fontfilename);
        Font actionJsonBase = null;
        try {
            Font actionJson = Font.createFont(Font.TRUETYPE_FONT,is);//返回一个指定字体类型和输入数据的font
            actionJsonBase = actionJson.deriveFont(Font.PLAIN,12);//通过复制此 Font 对象并应用新样式和大小，创建一个新 Font 对象。
            return actionJsonBase;

        }catch (Exception e){
            e.printStackTrace();
        }
        return actionJsonBase;
    }
}
