package com.example.camerademo3;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.media.Image;
import android.media.ImageReader;
import android.os.HandlerThread;
import android.provider.MediaStore;
import android.util.Log;
import android.util.Size;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.TextureView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class OnTouchEvent extends Activity {
    Screen backgroundImage;
    GamePanel gamePanel;

    private static final String TAG = "AndroidCameraAPI";
    private TextureView textureView;
    protected CameraDevice cameraDevice;
    private String cameraId;
    protected CameraCaptureSession cameraCaptureSession;
    protected CaptureRequest.Builder captureRequestBuilder;
    private Size size;
    private ImageReader imageReader;
    private File file;
    private File folder;
    private String folderName = "MyPhotoDir";
    private static final int REQUEST_CAMERA_PERMISSION = 200;
    private Handler mBackgroundHandler;
    private HandlerThread mBackgroundThread;

    private static final SparseIntArray ORIENTATIONS = new SparseIntArray();

    static {
        ORIENTATIONS.append(Surface.ROTATION_0, 90);
        ORIENTATIONS.append(Surface.ROTATION_90, 0);
        ORIENTATIONS.append(Surface.ROTATION_180, 270);
        ORIENTATIONS.append(Surface.ROTATION_270, 180);
    }

    private boolean photoTaken = false;

    private final CameraDevice.StateCallback stateCallback = new CameraDevice.StateCallback() {
        @Override
        public void onOpened(@NonNull CameraDevice camera) {
            cameraDevice = camera;
//            createCameraPreview();

        }

        @Override
        public void onDisconnected(@NonNull CameraDevice camera) {
            cameraDevice.close();
        }

        @Override
        public void onError(@NonNull CameraDevice camera, int i) {
            cameraDevice.close();
            cameraDevice = null;
        }
    };


//
//    public void takePicture() throws CameraAccessException {
//        CameraManager manager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
//        try{
//            CameraCharacteristics characteristics = manager.getCameraCharacteristics(cameraDevice.getId());
//            Size[] jpegSizes = null;
//            if (characteristics != null){
//                jpegSizes = characteristics.get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP).getOutputSizes(ImageFormat.JPEG);
//            }
//            int width = 640;
//            int height = 480;
//            if (jpegSizes != null && jpegSizes.length > 0){
//                width = jpegSizes[0].getWidth();
//                height = jpegSizes[0].getHeight();
//            }
//            ImageReader reader = ImageReader.newInstance(width, height, ImageFormat.JPEG, 1);
//            List<Surface> outputSurfaces = new ArrayList<>(2);
//            outputSurfaces.add(reader.getSurface());
//            outputSurfaces.add(new Surface(textureView.getSurfaceTexture()));
//            final CaptureRequest.Builder captureBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_ZERO_SHUTTER_LAG);
//            captureBuilder.addTarget(reader.getSurface());
//            captureBuilder.set(CaptureRequest.CONTROL_MODE, CameraMetadata.CONTROL_MODE_AUTO);
//
//            int rotation = getWindowManager().getDefaultDisplay().getRotation();
//            captureBuilder.set(CaptureRequest.JPEG_ORIENTATION, ORIENTATIONS.get(rotation));
//            file = null;
//            folder = new File (folderName);
//            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//            String imageFileName = "IMG_" + timeStamp + ".jpg";
//
//            file = new File (getExternalFilesDir(folderName), "/" + imageFileName);
//            if (!folder.exists()){
//                folder.mkdirs();
//            }
//            ImageReader.OnImageAvailableListener readerListener = new ImageReader.OnImageAvailableListener() {
//                @Override
//                public void onImageAvailable(ImageReader imageReader) {
//                    Image image = null;
//                    try{
//                        image = reader.acquireLatestImage();
//                        ByteBuffer buffer = image.getPlanes()[0].getBuffer();
//                        byte [] bytes = new byte [buffer.capacity()];
//                        buffer.get(bytes);
//                        //save
//                    }catch(FileNotFoundException e){
//                        e.printStackTrace();
//                    }catch (IOException e){
//                        e.printStackTrace();
//                    }finally{
//                        if (image != null){
//                            image.close();
//                        }
//                    }
//                }
//            }
//        }
//    }

    public static void takePhoto(Context context){
//        if (ContextCompat.checkSelfPermission(context,
//                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
//            ActivityCompat.requestPermissions((Activity) context,
//                    new String[]{
//                            Manifest.permission.CAMERA
//                    },
//                    100);
//        }
//        System.out.printf("dupa 1001");
        Log.d("MyApp","dupa 1001");
        ((Activity)context).startActivityForResult(new Intent(MediaStore.ACTION_IMAGE_CAPTURE), 100);



    }

    public static boolean onTouchEvent(MotionEvent event, GamePanel gamePanel, int factorXY1, int factorXY2){
        int xTouch = (int)event.getX();
        int yTouch = (int)event.getY();
        System.out.printf("touch!!");
        

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                System.out.printf("PHOTO!!! ");
                Log.d("MyApp","I am here");
                takePhoto(gamePanel.getContext());


//                System.out.printf("img desc" + gamePanel.images.get(0).getDesc());

//                System.out.printf("size x " + backgroundImage.getAnimation().getImage().getWidth());


                break;

//            default:
//                throw new IllegalStateException("Unexpected value: " + event.getAction());
        }
        return true;
    }
}
