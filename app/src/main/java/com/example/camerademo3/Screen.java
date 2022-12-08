package com.example.camerademo3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.DisplayMetrics;


//import static com.radek.tutorial.MainThread.canvas;

import com.example.camerademo3.GameElement;

import java.util.ArrayList;


/**
 * Created by Radek on 2018-01-07.
 */

public class Screen extends GameElement {
    private Bitmap spritesheet;
    private Animation animation = new Animation();


    private long startTime;
    private int direction;
    private int id = -1;
    private String desc;

    private int positionX = -1;
    private int positionY = -1;

    private int targetX = -1;
    private int targetY = -1;

    private int target = -1;        // no target

    protected ArrayList<Variable> variables;


    public ArrayList<Variable> getVariables() {
        return variables;
    }

    public int getVariableValue (String desc){
        int value = -1;

        for (Variable var : variables)
            if (var.getDesc().equals(desc)) {
                value = var.getValue();
                break;
            }

        return value;
    }

    public void setVariableValue (String desc, int val){
        for (Variable var : variables)
            if (var.getDesc().equals(desc)){
                var.setValue(val);
                break;
            }
    }

    public void setVariables(ArrayList<Variable> variables) {
        this.variables = variables;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public int getPositionX() {
        return positionX;
    }

    public void setPositionX(int positionX) {
        this.positionX = positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTargetX() {
        return targetX;
    }

    public void setTargetX(int targetX) {
        this.targetX = targetX;
    }

    public int getTargetY() {
        return targetY;
    }

    public void setTargetY(int targetY) {
        this.targetY = targetY;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Bitmap getSpritesheet() {
        return spritesheet;
    }

    public void setSpritesheet(Bitmap spritesheet) {
        this.spritesheet = spritesheet;
    }

    // Animated objects
    public Screen (Bitmap res, int xPos, int yPos, int w, int h, int direction, int numFrames, int animationDelay){
        super.x = xPos;
        super.y = yPos;
        this.direction = direction;
        height = h;
        width = w;
        Bitmap [] image = new Bitmap[numFrames];


        spritesheet = res;

        for (int i = 0; i<image.length; i++){
            image[i] = Bitmap.createBitmap(spritesheet, i*width, 0, width, height);
        }

        animation.setFrames(image);
        animation.setDelay(animationDelay);

    }

    public Screen (String desc, Bitmap res, int xPos, int yPos, int positionX, int positionY, int w, int h, int direction, int numFrames, int animationDelay, int animationLength, int density){
        this.desc = desc;
        super.x = xPos;
        super.y = yPos;
        this.direction = direction;
        this.positionX = positionX;
        this.positionY = positionY;
        height = h;
        width = w;
        Bitmap [] image = new Bitmap[numFrames];
        spritesheet = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        spritesheet = res;

        for (int i = 0; i<image.length; i++){
            image[i] = Bitmap.createBitmap(spritesheet, i*width, 0, width, height);
        }

        animation.setFrames(image);
        animation.setDelay(animationDelay);
        animation.setAnimationLength(animationLength);

        for (Bitmap img : image) {
            img.setDensity(density);
        }



        variables = new ArrayList<>();
    }

    public void addVariable (String desc, int val){
        Variable var = new Variable(desc, val);
        this.variables.add(var);
    }

    public void rotateFrames (int first, int last, int angle){
        for (int i = first; i <= last; i ++){
            int density =  animation.getImageByFrame(i).getDensity();
            this.animation.verifyFrame(i, Bitmap.createBitmap(spritesheet, i * width, 0, width, height), density);
            this.animation.verifyFrame(i, rotatedBitmap(this.animation.getImageByFrame(i), angle), density);
        }
    }

//    public void rotateFramesDelayed (int first, int last, int finalAngle){
//        int currentAngle = this.getVariableValue("currentAngle");
//
//        if (currentAngle != finalAngle) {
//            rotateFrames(first, last, currentAngle);
//
//            if (finalAngle > 0)
//                currentAngle += 5;
//            else
//                currentAngle -= 5;
//
//            this.setVariableValue("currentAngle", currentAngle);
//
//            if (currentAngle < finalAngle + 3 && currentAngle > finalAngle - 3) {
//                this.setVariableValue("currentAngle", finalAngle);
//                System.out.println(" current angle achieved ");
//            }
//        }
//    }

    public Bitmap rotatedBitmap (Bitmap original, int angle){
        Matrix matrix = new Matrix();
        matrix.preRotate(angle);
        Bitmap rotatedBitmap = Bitmap.createBitmap(original, 0, 0, original.getWidth(), original.getHeight(), matrix, true);
        original.recycle();
        return rotatedBitmap;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public Animation getAnimation() {
        return animation;
    }

    public void incrementY(float y){
        super.y += y;
    }

    public void incrementX(float x){
        super.x += x;
    }

    public void decreaseY(float y){
        super.y -= y;
    }

    public void decreaseX(float x){
        super.x -= x;
    }

    public void update (){
        animation.update();
    }

    public void draw (Canvas canvas){
        if (canvas != null)
            canvas.drawBitmap(animation.getImage(), x, y, null);
    }
}
