package com.doyoon.android.cookbook.util;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;

/**
 * Created by DOYOON on 5/26/2017.
 */

public class FileUtil {

    private static final String TAG = "FILE_UTIL"; // 많이 나오니까 미리 변경

    public static boolean write(Context context, String filename, String content){
        boolean result = false;
        // 1. 파일 스트림을 연다
        // 클래스를 바로 사용할수 없고, 안드로이드 리소스를 사용해서 열어야 한다.
        FileWriter writer = null;
        try {
           FileOutputStream fos =  context.openFileOutput(filename, context.MODE_PRIVATE);
            File file = new File(filename);
//            FileWriter writer = new FileWriter(file);
//            writer.write(content);
            result = true;
        } catch (FileNotFoundException e) {
            Log.e(TAG, e.toString());
        } finally {
            try {
                writer.close(); // TODO : CLOSE 계열의 함수는 무조건 finally 절로 보내야 한다.
                                // close 하다가 에러가 날수 있으니 try - catch로 한번 더 싼다.
            } catch (Exception e){
                Log.e(TAG, e.toString());
            }
        }

        // 2. 버퍼에 담는다.(속도문제)
        // Buffer에 바로 담을수는 없고 Reader

       // 3. 쓴다.

        // 4. 스트림을 닫는다.

        // 파일이 정상적으로 생성되었다면 true를 리턴한다.

        // 에러일 경우 에러 메시지를 토스트로 띄우고 false를 리턴한다.

        return result;
    }

    public static String read(String filename){
        String content = "";
        //TODO 파일을 읽어서 content에 담는다.
        // 1. 스트림을 생성하고
        // 2. 버퍼에 담은후
        // 3. 파일을 일곡
        // 4. 스트림을 닫아준다.
        // 5. 에러 발생시 메시지를 Toast 메시지를 출력하고, content에 "" 공백을 전달한다.


        return content;
    }
}
