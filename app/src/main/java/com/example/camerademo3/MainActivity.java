package com.example.camerademo3;

import static androidx.constraintlayout.motion.utils.Oscillator.TAG;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Camera;
import android.media.ImageReader;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Size;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import android.graphics.Color;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    GamePanel gamePanel;

    private Size imageDimension;
    private ImageReader imageReader;
    private File file;
    private File folder;
    private String folderName = "MyPhotoDir";
    private Camera camera;
    private static final int REQUEST_CAMERA_PERMISSION = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_HEIGHT = dm.heightPixels;
        Constants.SCREEN_WIDTH = dm.widthPixels;

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getRealSize(size);
        Constants.WIDTH_X = size.x;
        Constants.HEIGHT_Y = size.y;

        gamePanel = new GamePanel(this);
        setContentView(gamePanel);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        BitmapFactory.Options options = new BitmapFactory.Options();

//        super.onActivityResult(requestCode, resultCode, data);
        Log.d("MyApp","dupa 2111111");

//        camera = Camera.open();
//        Camera.Parameters parameters = camera.getParameters();
//        List<Camera.Size> sizes = parameters.getSupportedPictureSizes();
//        Camera.Size mSize = null;
//
//        for (Camera.Size size : sizes){
//            mSize = size;
//        }
//
////        parameters.setPictureSize(mSize.width, mSize.height);
//        parameters.setPictureSize(2, 1);
//        camera.setParameters(parameters);
//        Log.d("Chosen res", String.valueOf(camera.getParameters().getPictureSize().width));

        if (requestCode == 100) {

            options.inScaled = false;
            //get capture image

            Screen backgroundImage;
            Screen smallImage;

            Bitmap captureImage = (Bitmap) data.getExtras().get("data");

            backgroundImage = new Screen("background", captureImage, 1, 1, 500, 200, captureImage.getWidth(), captureImage.getHeight(), -1, 1, 150, 1, 40);

            gamePanel.currentMainImage ++;
            gamePanel.images.add(backgroundImage);

//            System.out.println("d101 : " + gamePanel.images.get(0).getDesc());
            Log.d("MyApp","dupa 2" + gamePanel.images.get(0).getAnimation().getImage().getWidth());


            int smallImage_h = captureImage.getHeight();
            int smallImage_w = captureImage.getWidth();

//            System.out.println("small image h" + smallImage_h + 5 / 0);
            System.out.println("capture image h" + captureImage.getHeight());

            Bitmap smallImageBMP = Bitmap.createBitmap(smallImage_w, smallImage_h, Bitmap.Config.ARGB_8888);

            int colorA;
            int colorB;
            int colorC;
            int alpha;

            for (int i = 0; i < smallImage_w; i ++) {
                for (int j = 0; j < smallImage_h; j ++) {
                    colorA = Color.red(captureImage.getPixel(i, j));
                    colorB = Color.green(captureImage.getPixel(i, j));
                    colorC = Color.blue(captureImage.getPixel(i, j));
                    alpha = Color.alpha(captureImage.getPixel(i, j));
                    smallImageBMP.setPixel(i, j, Color.argb(alpha, colorA, colorB, colorC));
                }
            }

            smallImage = new Screen("miniature", captureImage, 1, 1, 100 + 400 * gamePanel.imagesAmount, 800, captureImage.getWidth(), captureImage.getHeight(), -1, 1, 150, 1, 160);

            gamePanel.smallImages.add(smallImage);
            gamePanel.imagesAmount ++;

//            Bitmap compare = Bitmap.createBitmap(smallImage_w, smallImage_h, Bitmap.Config.ARGB_8888);

//            for (int i = 1; i < 2500; i ++) {
//                for (int j = 1; j < 1900; j++) {
//
//                    compare.setPixel(i, j, Color.argb(50, 100, 70, 90));
//
//                }
//            }
//
//            Screen backgroundImage = new Screen("miniature", compare, 1, 1, 1500, 1000, 3000, 2250, -1, 1, 150, 1, 160);
//            imageView.setImageBitmap(backgroundImage.getSpritesheet());



//            current_photo ++;
        }
    }
}