package com.example.camerademo3;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.ImageView;
import android.view.View;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;
import java.util.zip.Inflater;


public class Update extends AppCompatActivity {
    static long startTime;
    static long elapsed;

    static ArrayList<Screen> capturedImagesBig;
    static ImageView img;

    public static void update(GamePanel gamePanel) {

        if (gamePanel.playing) {

            elapsed = (System.nanoTime() - startTime) / 1000000;

            if (elapsed > 1000) {

            }
        }
    }


}