package oneway.NovelViewer.Helpers;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by Administrator on 2015/4/3.
 */
public class DialogHelper {
    public static void show(Context context,String title,String text)
    {
        AlertDialog.Builder builder=new  AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(text);
        builder.show();
    }
}
