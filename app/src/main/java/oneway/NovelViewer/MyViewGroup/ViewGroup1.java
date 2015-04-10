package oneway.NovelViewer.MyViewGroup;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Administrator on 2014/7/18.
 */
public class ViewGroup1 extends ViewGroup {
    public ViewGroup1(Context context) {
        super(context);
        Button btn=new Button(context);
        btn.setText("abcdefg");
        addView(btn);

        Button btn2=new Button(context);
        btn2.setText("1234567");
        addView(btn2);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        View v=getChildAt(0);
        if(v!=null)
        {
            v.layout(100,100,400,500);
        }

        View v2=getChildAt(1);
        if(v2!=null)
        {
            v2.layout(150,150,250,250);
        }
    }

    /*
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        View v=getChildAt(0);
        if(v!=null)
        {
            drawChild(canvas,v,getDrawingTime());
        }
    }*/
}
