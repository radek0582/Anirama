package com.example.camerademo3;

import android.graphics.Bitmap;

/**
 * Created by Radek on 2018-01-07.
 */

public class Animation {
    private Bitmap [] frames;
    private int currentFrame;
    private long startTime;
    private long delay;
    private boolean playedOnce;
    private int finishFrame;
    private int frameShift = 0;
    private int animationLength = 0;
    private int currentState = 1;
    private int newState = 0;

    public void setFrames (Bitmap [] frames){
        this.frames = frames;
        currentFrame = 0;
        startTime = System.nanoTime();
    }

    public void verifyFrame (int id, Bitmap frame, int density){
        this.frames [id] = frame;
        this.frames [id].setDensity(density);
    }

    public void setCurrentState(int currentState) {
        this.currentState = currentState;
    }

    public void setCurrentFrame(int currentFrame) {
        this.currentFrame = currentFrame;
    }

    public int getCurrentFrame() {
        return currentFrame;
    }

    public int getCurrentState() {
        return currentState;
    }

    public int getFinishFrame() {
        return finishFrame;
    }

    public void setFrameShift (int shift)
    {
        this.frameShift = shift;
    }

    public void setAnimationLength (int length){
        this.animationLength = length;
    }

    public void setFinishFrame(int finishFrame) {
        this.finishFrame = finishFrame;
    }

    public long getDelay() {
        return delay;
    }

    public void setDelay (long d) {
        delay = d;
    }

    public void setFrame (int i){currentFrame = i;}

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setNewState(int newState) {
        this.newState = newState;
    }

    public int getNewState() {
        return newState;
    }

    public int getFrameShift() {
        return frameShift;
    }

    public void update (){
        long elapsed = (System.nanoTime()-startTime)/1000000;

        if (this.frames.length > 1) {
            if (newState != currentState) {
                currentFrame += frameShift;
                currentState = newState;
            }

            if (elapsed > delay) {
                currentFrame++;
                startTime = System.nanoTime();
            }

            if (currentFrame > animationLength + frameShift) {
                currentFrame = frameShift;
                playedOnce = true;
            }
//            System.out.println("current frame: " + currentFrame);
        }
    }

    public Bitmap getImage (){
        return frames [currentFrame];
    }

    public Bitmap getImageByFrame (int frameNo){
        return frames [frameNo];
    }

    public int getFrame(){return currentFrame;}

    public boolean playedOnce (){return playedOnce;}


}
