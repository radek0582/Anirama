package com.example.camerademo3;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.Surface;
import android.view.SurfaceHolder;

import java.util.ArrayList;

/**
 * Created by Radek on 2017-11-11.
 */

public class MainThread extends Thread {
    public static final int MAX_FPS = 30;

    private SurfaceHolder surfaceHolder;
    private GamePanel gamePanel;
    private boolean running;
    public static Canvas canvas;


    /*public void setRunning(boolean running){
        this.running = running;
    }*/

    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel){
        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;
    }

    @Override
    public void run() {
        long startTime;
        long timeMillis = 1000/MAX_FPS;
        long waitTime;
        int frameCount = 0;
        long totalTime = 0;
        long targetTime = 1000/MAX_FPS;

        while (running) {
            startTime = System.nanoTime ();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
//                    this.gamePanel.update();
                    this.gamePanel.update();
                    this.gamePanel.draw(canvas);
//                    System.out.printf("d u p a");
                }
            } catch(Exception e) {

            } finally {
                if(canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    } catch(Exception e) {e.printStackTrace();}
                }
            }
            timeMillis = (System.nanoTime() - startTime/1000000);
            waitTime = targetTime - timeMillis;
            try {
                if (waitTime > 0)
                    this.sleep(waitTime);
            } catch (Exception e) {e.printStackTrace();}

            totalTime += System.nanoTime() - startTime;
            frameCount ++;

            if (frameCount == MAX_FPS) {
                gamePanel.averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.printf("" + gamePanel.averageFPS + " " + gamePanel.images.size());

                if (gamePanel.images.size() > 4) {
                    for (Screen img : gamePanel.images)
//
                    System.out.println(" img desc: " + img.getDesc());// + " img size: " + img.getAnimation().getImage().getWidth());
                }
            }
        }
    }

    public void setRunning (boolean b){
        running = b;
    }
}
