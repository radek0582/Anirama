package com.example.camerademo3;

import static com.example.camerademo3.Constants.HEIGHT_Y;
import static com.example.camerademo3.Constants.WIDTH_X;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;

    protected ArrayList<Screen> images;
    protected ArrayList<Screen> smallImages;
    protected ArrayList<Screen> imgToRemove;
    protected ArrayList<Screen> imgToAdd;
    protected ArrayList<Screen> imgToPaintBackground;

    protected int currentMainImage = 0;
    protected int imagesAmount = 0;


    protected int gameBoardState [] [] = new int [3000][2250];

    protected double averageFPS;

    int factorXY1;
    int factorXY2;

    boolean playing = false;

    public int varA = 100;

    boolean isLandscape;
    protected int[] pixels;
    protected int[][] pixels2D;
    protected int[] tempPixels;

    protected int shiftX = 0;
    protected int shiftY = 0;
    protected int prevX = 0;
    protected int prevY = 0;
    protected int diffX = 0;
    protected int diffY = 0;

    protected int movingDist = 0;

    protected int pressedX = 0;
    protected int pressedY = 0;

    int holdingtime = 0;

    long currentCounterTime = 0;
    long startCounterTime = 0;

    protected int wind = 1; // 1 - from north, 2 - from east...

    boolean firstRun = true;

    MediaPlayer mp = new MediaPlayer();
    boolean fingerMenu = false;
    boolean screenPressed = false;

    protected boolean startedGame = false;

    protected Screen tempBitmap;

    boolean moveScreenOn = false;

    BitmapFactory.Options options = new BitmapFactory.Options();

    public void update() {
        Update.update(this);
    }


    public GamePanel(Context context) {
        super(context);

         float xy1 = WIDTH_X/HEIGHT_Y;
        float xy2 = 16/9;

        if (xy1 <= xy2)
            isLandscape = false;
        else
            isLandscape = true;

        if (isLandscape == true) {
            factorXY1 = HEIGHT_Y;
            factorXY2 = 1080;
        }
        else {
            factorXY1 = WIDTH_X;
            factorXY2 = 1920;
        }

        images = new ArrayList<>();
        smallImages = new ArrayList<>();
//        capturedImagesGP = new ArrayList<>();

        getHolder().addCallback(this);
        obstacleManager();
        setFocusable(true);
    }

    public void obstacleManager (){
        ObstacleManager.obstacleManager(this);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event){
        return OnTouchEvent.onTouchEvent(event, this, factorXY1, factorXY2);
    }

@Override
public void surfaceChanged(SurfaceHolder holder, int format, int widht, int height) {
    System.out.printf("surface changed");
}

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);
        thread.setRunning(true);

        if (thread.getState() == Thread.State.NEW)
            thread.start();

        System.out.printf("surface created");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        int counter = 0;

        while (retry && counter<1000) {
            counter ++;
            try {
                thread.setRunning(false);
                thread.join();
                retry = false;
                thread = null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("surface destroyed");
    }

    @Override
    public void draw(Canvas canvas)
    {
        if (firstRun) {
            if (isLandscape == false)
                canvas.setDensity(160 * factorXY1 / factorXY2);
            else
                canvas.setDensity(160 * factorXY1 / factorXY2);

            firstRun = false;
        }

        if (canvas != null){
            super.draw(canvas);
//            canvas.drawColor(Color.rgb(16,16,16));
        }
        Draw.draw (this, canvas);
    }
}
