package com.example.camerademo3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;


import java.util.ArrayList;
import java.util.Random;

public class ObstacleManager {
    public static void obstacleManager(GamePanel gamePanel){
        gamePanel.startedGame = false;

        Button button;

        gamePanel.options.inScaled = false;

        gamePanel.tempBitmap = new Screen(BitmapFactory.decodeResource(gamePanel.getResources(), R.drawable.leaf3, gamePanel.options), -100, 1, 3000, 2250, -1, 1, 150);
        gamePanel.tempPixels = new int[3000*2250];
        gamePanel.tempBitmap.getSpritesheet().getPixels(gamePanel.tempPixels,0,3000,0,0,3000,2250);

        Bitmap compare = Bitmap.createBitmap(3000, 2250, Bitmap.Config.ARGB_8888);

        compare.setPixels(gamePanel.tempPixels,0,3000,0,0,3000,2250);
        compare.setDensity(160);// * factorXY1/factorXY2);

//        Screen backgroundImage = new Screen("background", compare, 1, 1, 1500, 1000, 3000, 2250, -1, 1, 150, 1, 160);
//        gamePanel.images.add(backgroundImage);


    }
}

