package oneway.NovelViewer.MyView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.ClipboardManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import oneway.NovelViewer.Helpers.DialogHelper;
import oneway.NovelViewer.Helpers.Setting;
import oneway.NovelViewer.IndexTree.DivideWord;

/**
 * Created by Administrator on 2014/7/19.
 */
public class WordView extends TextView {
    protected DivideWord mWord;

    public TextView mDicView;

    public WordView(Context context) {
        super(context);
    }

    public WordView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WordView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public WordView(Context context,DivideWord word){
        this(context,word,null);
    }
    public WordView(Context context,DivideWord word,TextView dicView)  {
        this(context);
        mWord=word;
        mDicView=dicView;
        Init();
    }

    private void setMargin()
    {
        ViewGroup.MarginLayoutParams layoutParams=new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,0,0,0);
        setLayoutParams(layoutParams);
    }

    private void Init()
    {
        setText(mWord.realWord);
        setColor();
        setMargin();
        setTextSize(Setting.textSize);
        setOnClickListener(new View.OnClickListener(){
            public void onClick(View arg0) {
                if(mWord.GetWordCount()==0)
                {
                }
                else if(mWord.GetWordCount()==1)
                {
                    String text=mWord.GetWordText(0);
                    showText(text);
                }
                else
                {
                    if(mWord.CanBeOnly())
                    {
                        String text=mWord.GetMostPossibleWordText();
                        showText(text);
                    }
                    else
                    {
                        showPossibleWords();
                    }
                }
            }
        });
    }

    private void showText(String text)
    {
        DialogHelper.show(getContext(),mWord.realWord,text);
    }

    private void showPossibleWords()
    {
        new AlertDialog.Builder(getContext()).setTitle(mWord.realWord).setItems(mWord.getNames(),new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DialogHelper.show(getContext(),mWord.realWord,mWord.GetWordText(which));
            }
        }).show();
    }

    private void setColor()
    {
        if(mWord.GetWordCount()==0)
            setTextColor(Color.BLACK);
        else if(mWord.GetWordCount()==1)
            setTextColor(Color.BLUE);
        else
        {
            boolean only=mWord.CanBeOnly();
            if(only)
                setTextColor(Color.BLUE);
            else
                setTextColor(Color.RED);
        }
    }
}
