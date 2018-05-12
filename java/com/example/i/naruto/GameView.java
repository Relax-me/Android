package com.example.i.naruto;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.InputStream;
import java.util.Random;

/**
 * Created by songl on 2017/11/30.
 */

class GameView extends SurfaceView implements SurfaceHolder.Callback{
    private Context fcontext;
    private SurfaceHolder holder;
    public static Resources res;
    private boolean isStop = false;
    private ViewThread viewThread;
    private Thread mThread;
    public static Bitmap bgnow;
    public static Bitmap zhujuenow;
    public GameView(Context context) {
        super(context);
        fcontext=context;
        holder = getHolder();
        holder.addCallback(this);
        viewThread=new ViewThread();
        mThread = new Thread(viewThread);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        res=FightActivity.res;
        Random random=new Random();
        bgnow = getbg(random.nextInt(8));
        zhujuenow = createBitmapByID(res, R.drawable.r00, 1.5f);
        MyNaruto.init(bgnow.getWidth());
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isStop = true;
        mThread.destroy();
    }
    class ViewThread implements Runnable{
        @Override
        public void run(){
            Canvas canvas = null;
            while (!isStop) {
                try {
                    canvas = holder.lockCanvas();
                    if (canvas != null) {
                        Graphics.drawImage(canvas, bgnow, 0, 0, MyNaruto.mapx, 0, FightActivity.windowWidth, bgnow.getHeight());
                        Graphics.drawImage(canvas, zhujuenow, MyNaruto.x, MyNaruto.y-zhujuenow.getHeight(), 0, 0, zhujuenow.getWidth(), zhujuenow.getHeight());
                        Thread.sleep(32);
                    }
                }catch (Exception e) {
                }finally {
                    try
                    {
                        if (canvas != null)
                        {
                            holder.unlockCanvasAndPost(canvas);
                        }
                    }
                    catch (Exception e)
                    {}
                }
            }
        }
    }
    public static Bitmap getbg(int n){
        switch (n){
            case 0:return createBitmapByID(res, R.drawable.f001,2.1f);
            case 1:return createBitmapByID(res, R.drawable.f002,2.1f);
            case 2:return createBitmapByID(res, R.drawable.f003,2.1f);
            case 3:return createBitmapByID(res, R.drawable.f004,2.1f);
            case 4:return createBitmapByID(res, R.drawable.f005,2.1f);
            case 5:return createBitmapByID(res, R.drawable.f006,2.1f);
            case 6:return createBitmapByID(res, R.drawable.f007,2.1f);
            case 7:return createBitmapByID(res, R.drawable.f008,2.1f);
        }
        return createBitmapByID(res, R.drawable.f001);
    }
    public static Bitmap getzhujuel(int n){
        switch (n){
            case 0:return createBitmapByID(res, R.drawable.l01, 1.5f);
            case 1:return createBitmapByID(res, R.drawable.l02, 1.5f);
            case 2:return createBitmapByID(res, R.drawable.l03, 1.5f);
            case 3:return createBitmapByID(res, R.drawable.l04, 1.5f);
            case 4:return createBitmapByID(res, R.drawable.l05, 1.5f);
            case 5:return createBitmapByID(res, R.drawable.l06, 1.5f);
            case 6:return createBitmapByID(res, R.drawable.l07, 1.5f);
            case 7:return createBitmapByID(res, R.drawable.l08, 1.5f);
        }
        return createBitmapByID(res, R.drawable.l00,1.5f);
    }
    public static Bitmap getzhujuer(int n){
        switch (n){
            case 0:return createBitmapByID(res, R.drawable.r01, 1.5f);
            case 1:return createBitmapByID(res, R.drawable.r02, 1.5f);
            case 2:return createBitmapByID(res, R.drawable.r03, 1.5f);
            case 3:return createBitmapByID(res, R.drawable.r04, 1.5f);
            case 4:return createBitmapByID(res, R.drawable.r05, 1.5f);
            case 5:return createBitmapByID(res, R.drawable.r06, 1.5f);
            case 6:return createBitmapByID(res, R.drawable.r07, 1.5f);
            case 7:return createBitmapByID(res, R.drawable.r08, 1.5f);
        }
        return createBitmapByID(res, R.drawable.r00,1.5f);
    }
    // 工具方法：根据图片id来获取实际的位图
    private static Bitmap createBitmapByID(Resources res, int resID)
    {
        try
        {
            InputStream is = res.openRawResource(resID);
            return BitmapFactory.decodeStream(is, null, null);

        }
        catch (Exception e)
        {
            return null;
        }
    }
    // 工具方法：根据图片id来获取实际的位图，并按scale进行缩放
    private static Bitmap createBitmapByID(Resources res, int resID, float scale)
    {
        try
        {
            InputStream is = res.openRawResource(resID);
            Bitmap bitmap = BitmapFactory.decodeStream(is, null, null);
            if (bitmap == null || bitmap.isRecycled())
            {
                return null;
            }
            if (scale <= 0 || scale == 1f)
            {
                return bitmap;
            }
            int wdith = (int) (bitmap.getWidth() * scale);
            int height = (int) (bitmap.getHeight() * scale);
            Bitmap newBitmap = Graphics.scale(bitmap, wdith, height);
            if (!bitmap.isRecycled() && !bitmap.equals(newBitmap))
            {
                bitmap.recycle();
            }
            return newBitmap;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
