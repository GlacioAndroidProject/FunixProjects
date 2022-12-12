package com.example.funixassignment2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;


public class CircleMenu extends View {

    public static interface IMenuListener{

        public void onMenuClick(MenuCircle item);
    }

    public static class MenuCircle{
        private int x,y,radius;
        public int backGroundColor, resourceImage;
        public int id;
        public String text;
    }

    private Paint mainPaint;
    private Paint secondPaint;
    private Paint textPaint;
    private int mainColor = Color.BLUE;
    private int defautSubColor = Color.DKGRAY;
    private int defautSubTextColor = Color.BLACK;

    private int radius_main =130;

    private int menuInnerPadding = 40;
    private int radialCircleRadius = 60;
    private int textPadding = 25;
    private double startAngle = - Math.PI/2f;
    private ArrayList<MenuCircle> elements;
    private IMenuListener listener;

    public void setMainColor(int mainColor) {
        this.mainColor = mainColor;
    }

    public void setDefautsubColor(int defautsubColor) {
        this.defautSubColor = defautsubColor;
    }

    public void setDefautSubTextColor(int defautSubTextColor) {
        this.defautSubTextColor = defautSubTextColor;
    }

    public void setListener(IMenuListener listener){
        this.listener = listener;
    }
    public void clear(){
        elements.clear();
        listener=null;
    }
    public CircleMenu(Context context) {
        super(context);
        init();
    }

    public CircleMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CircleMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        elements = new ArrayList<>();
    }
    public void addMenuItem(String text,int id, int backGroundColor, int resourceImage){
        MenuCircle item = new MenuCircle();
        item.id = id;
        item.text=text;
        item.backGroundColor = backGroundColor;
        item.resourceImage = resourceImage;
        elements.add(item);

    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mainPaint = new Paint();

        mainPaint.setColor(mainColor);
        secondPaint = new Paint();
        secondPaint.setColor(defautSubColor);
        textPaint = new Paint();
        textPaint.setColor(defautSubTextColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int centerX = canvas.getWidth()/2 ;
        int centerY= canvas.getHeight()/2;
        canvas.drawCircle(centerX,centerY,radius_main,mainPaint);
        for(int i=0;i<elements.size();i++){
            double angle =0;
            if(i==0){
                angle = startAngle;
            }else{
                angle = startAngle+(i * ((2 * Math.PI) / elements.size()));
            }
            elements.get(i).x = (int) (centerX + Math.cos(angle)*(radius_main+menuInnerPadding+radialCircleRadius));
            elements.get(i).y = (int) (centerY + Math.sin(angle)*(radius_main+menuInnerPadding+radialCircleRadius));


            canvas.drawCircle( elements.get(i).x,elements.get(i).y,radialCircleRadius,secondPaint);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),elements.get(i).resourceImage);
            canvas.drawBitmap(bitmap, 0,0, secondPaint);
            float tW = textPaint.measureText(elements.get(i).text);
            canvas.drawText(elements.get(i).text,elements.get(i).x-tW/2,elements.get(i).y+radialCircleRadius+textPadding,textPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if(event.getAction()==MotionEvent.ACTION_DOWN){
            for(MenuCircle mc : elements){
                double distance =  Math.hypot(event.getX()-mc.x,event.getY()-mc.y);
                if(distance<= radialCircleRadius){
                    //touched
                    if(listener!=null)
                        listener.onMenuClick(mc);
                    return true;
                }
            }

        }

        return super.onTouchEvent(event);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();

    }
}