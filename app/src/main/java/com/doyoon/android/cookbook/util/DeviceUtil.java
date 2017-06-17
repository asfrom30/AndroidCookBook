package com.doyoon.android.cookbook.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;

import com.doyoon.android.cookbook.BuildConfig;

import java.io.File;
import java.io.IOException;


/*
    Version : 0.0.1 on 6/10/2017.
 */
/*
    Version : 0.0.2 on 6/17/2017
        -. update Image immediately after take a photo
        -. implement callback funtion
 */
/*
    need to do
    1. File Path auto input and recognize when call method(Handle file_path.xml automatically)
    2. test under lower version
 */

public class DeviceUtil {

    public static String TAG = DeviceUtil.class.getName();
    public static final int CAMERA_REQ_CODE = 1;

    /* Camera Util */
    /* Note : 세개가 모두 일치해야됨.*/
    //        1. Path 일치
    //            -. 임시 생성파일과
    //            -. xml 파일패스가 일치
    //        2. Request 코드일치,
    //            -. Send Intent
    //            -. Result Intent
    //        3. Authority 일치
    //            -. Manifest안의 Provider 태그의 Authorites와
    //            -. 코드안에 String Autority 변수가 일치.

    /* Description */
    //    롤리팝 이하 버전에서는 카메라 캡쳐 파일이 인텐트로 들어왔고 => onActivityResult()함수 안에서 Intent.getdata()로 바로 획득
    //    이상버전부터는 URI를 통해서만 접근이 가능하다. => URI를 전역변수로 선언해서 넘겨준다.
    //
    //    근데 이 URI가 또 왜 그러냐면
    //    마시멜로우 이하부터는 FileProvider없이 File만 있으면 권한 없이 그냥 URI를 획득할 수 있는데 => fileURI = Uri.fromFile(tempFile);
    //    마시멜로우 이상 버전부터는 FileProvider를 통해서만 권한을 획득할 수 있다. => fileURI = FileProvider.getUriForFile((activity.getBaseContext()), authority, tempFile) */

    public static class Camera{
        public static String currentPhotoAbsolutePath = null;
        public static Uri currentPhotoUri = null;

        public static void takePhoto(Activity activity, String xmlExternalPath/* Folder Name */ , int requestCode) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

            /* Under Lollipop Version Doesn't need Convert File to URI*/
            /* Just send Intent and In onActivityResult, You can get simply File's URI using Intent.getData(); */
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
                activity.startActivityForResult(intent, requestCode);
                Log.e("TAG", "1");
                return;
            }

            currentPhotoUri = null;
            File tempFile = null;

            try {
                /* Create temp file for saving photo */
                tempFile = createFileForCamera(xmlExternalPath); //TODO : Need to change File Util
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (tempFile == null) {
                Log.e(TAG, "Created File is null for Camera(Photo Capture)");
                return;
            }

            /* Under Marshmallow Version doesn't need File Provider during access URI*/
            Uri tempUri = null;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                tempUri = Uri.fromFile(tempFile);
            } else { /* You have to access Using File Privider */
                // File Provider need Authority
                // Authority should be set in manifest file using <provider> tag for run below code
                /* Import package again */
                String authority = BuildConfig.APPLICATION_ID + ".provider"; // get app ID from Gradle
                tempUri = FileProvider.getUriForFile((activity.getBaseContext()), authority, tempFile);
            }

            Camera.currentPhotoAbsolutePath = tempFile.getAbsolutePath();
            Camera.currentPhotoUri = tempUri;

            if (currentPhotoUri == null) {
                Log.e(TAG, "Current Photo가 null 입니다.");
            } else {
                Log.e(TAG, currentPhotoUri.toString());
            }

            /* Send Intent */
            intent.putExtra(MediaStore.EXTRA_OUTPUT, currentPhotoUri);
            activity.startActivityForResult(intent, requestCode); // NOTE : Context doesn't have startActivityForResult() method.
        }

        // 파일생성에 관한 함수를 만들고 Exception에 담는다.
        private static File createFileForCamera(String xmlExternalPath) throws IOException {
            // 임시 파일명 생성
            String tempFilename = "TEMP_" + System.currentTimeMillis();
            // 임시파일 저장용 디렉토리 생성
            // 익스터널 스토리지의 루트 경로(실제 시스템의 루트가 아님)
            // XML에서 설정했던 그 경로에 대한 권한을 얻는 것이다.
            File tempDir = new File(Environment.getExternalStorageDirectory() +"/" + xmlExternalPath + "/"); // path라는 것을 알려주기 위해 /로 랩한다
            if(!tempDir.exists()){
                tempDir.mkdir(); // 없으면 모두 생성
            }

            //실제 임시파일을 생성
            File tempFile = File.createTempFile(
                    tempFilename,// 파일네임
                    ".jpg",     // suffix
                    tempDir     // 디렉토리
            );
            return tempFile;
        }

        public static void onActivityResult(int requestCode, int resultCode, Intent data, Context context, Callback callback) {
            // Get Current Photo URI, the way is different depend on android version
            Uri uri = Camera.getCurrentPhotoUri(data);

            // update Gallery
            Camera.updateGallery(context);

            // 호출한 쪽에서 원하는 함수를 실행한다.
            callback.postProcAfterCamera(uri);
        }

        private static void updateGallery(Context context){
            /* Update Gallery */
            MediaScannerConnection.scanFile(context, new String[] { Camera.currentPhotoAbsolutePath }, null, new MediaScannerConnection.OnScanCompletedListener() {
                public void onScanCompleted(String path, Uri uri) {
                    Log.i("ExternalStorage", "Scanned " + path + ":");
                    Log.i("ExternalStorage", "-> uri=" + uri);
                }
            });
        }

        /* 버전에 따라 Photo Uri를 획득하는 과정이 다르다.
        * 버전이 낮은 경우 result Intent에서 바로 꺼내 쓸 수 있고, 버전이 높은 경우 보안 때문에 Intent가 null로 넘어온다.
        * 이 경우 호출시 미리 Uri를 생성하고 기억하고 있어야 한다. */
        private static Uri getCurrentPhotoUri(Intent data){
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                return data.getData(); // Return URI
            } else {
                return Camera.currentPhotoUri;  // takePhoto() method에서 기억하고 있는것을 저장해두었다가 넘겨준다.
            }
        }

        public interface Callback{
            void postProcAfterCamera(Uri currentPhotoUri);
        }
    }



}
