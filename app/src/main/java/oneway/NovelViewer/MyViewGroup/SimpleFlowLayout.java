package oneway.NovelViewer.MyViewGroup;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2014/7/19.
 */
public class SimpleFlowLayout extends ViewGroup {
    public SimpleFlowLayout(Context context) {
        super(context);
    }

    public SimpleFlowLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SimpleFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //measureChildren(widthMeasureSpec,heightMeasureSpec);

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获得它的父容器为它设置的测量模式和大小
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);

        Log.e(TAG, sizeWidth + "," + sizeHeight);

        // 如果是warp_content情况下，记录宽和高
        int width = 0;
        int height = 0;
        /**
         * 记录每一行的宽度，width不断取最大宽度
         */
        int lineWidth = 0;
        /**
         * 每一行的高度，累加至height
         */
        int lineHeight = 0;

        int cCount = getChildCount();

        // 遍历每个子元素
        for (int i = 0; i < cCount; i++)
        {
            View child = getChildAt(i);
            // 测量每一个child的宽和高
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            // 得到child的lp
            MarginLayoutParams lp = getChildLayoutParams(child);
            // 当前子空间实际占据的宽度
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
            // 当前子空间实际占据的高度
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            /**
             * 如果加入当前child，则超出最大宽度，则的到目前最大宽度给width，类加height 然后开启新行
             */
            if (lineWidth + childWidth > sizeWidth)
            {
                width = Math.max(lineWidth, childWidth);// 取最大的
                lineWidth = childWidth; // 重新开启新行，开始记录
                // 叠加当前高度，
                height += lineHeight;
                // 开启记录下一行的高度
                lineHeight = childHeight;
            } else
            // 否则累加值lineWidth,lineHeight取最大高度
            {
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
            }
            // 如果是最后一个，则将当前记录的最大宽度和当前lineWidth做比较
            if (i == cCount - 1)
            {
                width = Math.max(width, lineWidth);
                height += lineHeight;
            }

        }

        int measuredWidth=(modeWidth == MeasureSpec.EXACTLY) ? sizeWidth  : width;
        int measureHeight= (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight  : height;
        //measureHeight=350;
        setMeasuredDimension(measuredWidth,measureHeight);
    }

    private String TAG="SimpleFlowLayout";

    /*
        是否换行，需要子类覆盖
     */
    protected boolean isWrap(View childView)
    {
        return false;
    }

    private MarginLayoutParams getChildLayoutParams(View childView)
    {
        ViewGroup.LayoutParams lp=childView.getLayoutParams();
        MarginLayoutParams cParams;
        if(childView.getLayoutParams() instanceof MarginLayoutParams) {
            cParams= (MarginLayoutParams) lp;
        }
        else  {
            cParams=new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            cParams.setMargins(0, 0, 0, 0);
        }
        return cParams;
    }

    /**
     * 存储所有的View，按行记录
     */
    private List<List<View>> mAllViews = new ArrayList<List<View>>();
    /**
     * 记录每一行的最大高度
     */
    private List<Integer> mLineHeight = new ArrayList<Integer>();
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        mAllViews.clear();
        mLineHeight.clear();

        int width = getWidth();

        int lineWidth = 0;
        int lineHeight = 0;
        // 存储每一行所有的childView
        List<View> lineViews = new ArrayList<View>();
        int cCount = getChildCount();
        // 遍历所有的孩子
        for (int i = 0; i < cCount; i++)
        {
            View child = getChildAt(i);
            MarginLayoutParams lp = getChildLayoutParams(child);
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();

            // 如果已经需要换行
            if (childWidth + lp.leftMargin + lp.rightMargin + lineWidth > width)
            {
                // 记录这一行所有的View以及最大高度
                mLineHeight.add(lineHeight);
                // 将当前行的childView保存，然后开启新的ArrayList保存下一行的childView
                mAllViews.add(lineViews);
                lineWidth = 0;// 重置行宽
                lineViews = new ArrayList<View>();
            }
            /**
             * 如果不需要换行，则累加
             */
            lineWidth += childWidth + lp.leftMargin + lp.rightMargin;
            lineHeight = Math.max(lineHeight, childHeight + lp.topMargin
                    + lp.bottomMargin);
            lineViews.add(child);
        }
        // 记录最后一行
        mLineHeight.add(lineHeight);
        mAllViews.add(lineViews);

        int left = 0;
        int top = 0;
        // 得到总行数
        int lineNums = mAllViews.size();
        for (int i = 0; i < lineNums; i++)
        {
            // 每一行的所有的views
            lineViews = mAllViews.get(i);
            // 当前行的最大高度
            lineHeight = mLineHeight.get(i);

            Log.e(TAG, "第" + i + "行 ：" + lineViews.size() + " , " + lineViews);
            Log.e(TAG, "第" + i + "行， ：" + lineHeight);

            // 遍历当前行所有的View
            for (int j = 0; j < lineViews.size(); j++)
            {
                View child = lineViews.get(j);
                if (child.getVisibility() == View.GONE)
                {
                    continue;
                }
                MarginLayoutParams lp = getChildLayoutParams(child);

                //计算childView的left,top,right,bottom
                int lc = left + lp.leftMargin;
                int tc = top + lp.topMargin;
                int rc =lc + child.getMeasuredWidth();
                int bc = tc + child.getMeasuredHeight();

                Log.e(TAG, child + " , l = " + lc + " , t = " + t + " , r ="
                        + rc + " , b = " + bc);

                child.layout(lc, tc, rc, bc);

                left += child.getMeasuredWidth() + lp.rightMargin
                        + lp.leftMargin;
            }
            left = 0;
            top += lineHeight;
        }

        /*int cCount=getChildCount();
        int width=getMeasuredWidth();
        int height=getMeasuredHeight();
        //Log.e(TAG,"width="+width);

        int x=0;
        int y=0;
        for(int i=0;i<cCount;i++)
        {
            View childView=getChildAt(i);
            int cWidth=childView.getMeasuredWidth();
            int cHeight=childView.getMeasuredHeight();
            MarginLayoutParams cParams=getChildLayoutParams(childView);
            int leftMargin=cParams.leftMargin;
            int rightMargin=cParams.rightMargin;
            int bottomMargin=cParams.bottomMargin;
            int topMargin= cParams.topMargin;

            if (isWrap(childView)||(x + cWidth + leftMargin + cWidth > width)) {//换行
                x = 0;
                y += leftMargin + cHeight + bottomMargin;
                //Log.e(TAG,"y="+y+"  top="+(y+cParams.topMargin)+"   topMargin"+cParams.topMargin);
            }

            int left = x + leftMargin;
            int right = left + cWidth;

            int top = y + topMargin;
            int bottom = top + cHeight;

            childView.layout(left, top, right, bottom);

            //Log.e(TAG,"cwidth="+cWidth+" cheight="+cHeight+":"+left+","+right+","+top+","+bottom);
            //Log.e(TAG,"x1="+x);
            //x+=cParams.leftMargin+cWidth+cParams.rightMargin;
            x = right + rightMargin;
            //Log.e(TAG,"x2="+x);

        }*/
    }
}
