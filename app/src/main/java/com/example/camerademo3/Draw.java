package com.example.camerademo3;



import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

public class Draw {
    public static void draw (GamePanel gamePanel, Canvas canvas){

        Paint paint = new Paint();
        paint.setTextSize(80 * gamePanel.factorXY1 / gamePanel.factorXY2);
        paint.setColor(Color.rgb(150, 150, 150));

//        System.out.printf("dupa3" + 10 / 0);

//        for (Screen img : gamePanel.imgToRemove)
//            gamePanel.images.remove(img);
//
//        gamePanel.imgToRemove.clear();
//
//        for (Screen img : gamePanel.imgToAdd)
//            gamePanel.images.add(img);
//
//        gamePanel.imgToAdd.clear();

        Screen board = null;

        for (Screen imgBoard : gamePanel.images)
            if (imgBoard.getDesc().equals("background")) {
                board = imgBoard;
            }

//        for (Screen img : gamePanel.imgToPaintBackground)
//        {
//            int a = img.getPositionX();
//            int b = img.getPositionY();
//
////            System.out.println("image wid: " + img.getAnimation().getImage().getWidth() + " hei" + img.getAnimation().getImage().getHeight() + "pix : " + img.getAnimation().getImage().getPixel(1,1));
//
//            for (int i = 0; i < img.getAnimation().getImage().getWidth(); i++)
//                for (int j = 0; j < img.getAnimation().getImage().getHeight(); j++)
//                {
//                    int colorCode = img.getAnimation().getImage().getPixel(i, j);
//
//                    if (Color.alpha(colorCode) != 0) {
////                        gamePanel.gameBoardState [i + a][j + b] = -2;
//                        board.getAnimation().getImage().setPixel(i + a - 125, j + b, Color.argb(Color.alpha(colorCode), Color.red(colorCode), Color.green(colorCode), Color.blue(colorCode)));
//                    }
//                }
//        }

//        gamePanel.imgToPaintBackground.clear();

//        for (Screen img : gamePanel.images){
//            if (img.getDesc().equals("background")) {
//                drawObj(img, gamePanel, gamePanel.factorXY1, gamePanel.factorXY2, canvas);
////                System.out.println("dupa1111");
//            }
//        }

        if (gamePanel.currentMainImage > 0) {
            drawObj(gamePanel.images.get(gamePanel.currentMainImage - 1), gamePanel, gamePanel.factorXY1, gamePanel.factorXY2, canvas);

        }

        for (Screen img : gamePanel.smallImages){
            if (img.getDesc().equals("miniature")) {
                drawObj(img, gamePanel, gamePanel.factorXY1, gamePanel.factorXY2, canvas);
//                System.out.println("dupa1111");
            }
        }



//        canvas.drawText(" FPS: " , 1500 * gamePanel.factorXY1 / gamePanel.factorXY2, 2800 * gamePanel.factorXY1 / gamePanel.factorXY2, paint);
    }

    public static void drawObj (Screen obj, GamePanel gamePanel, int factorXY1, int factorXY2, Canvas canvas){
        if (obj.getDesc() == "background") {
            Log.d("dens ", "dens: " + obj.getAnimation().getImage().getDensity());
            obj.getAnimation().getImage().setDensity(40);
        }
        else
            obj.getAnimation().getImage().setDensity(160);

        obj.setX((obj.getPositionX() + gamePanel.shiftX * factorXY2 / factorXY1 - obj.getAnimation().getImage().getWidth() / 2 ) * factorXY1 / factorXY2);
        obj.setY((obj.getPositionY() + gamePanel.shiftY * factorXY2 / factorXY1 - obj.getAnimation().getImage().getHeight() / 2 ) * factorXY1 / factorXY2);
        obj.update();
        obj.draw(canvas);
    }
}
