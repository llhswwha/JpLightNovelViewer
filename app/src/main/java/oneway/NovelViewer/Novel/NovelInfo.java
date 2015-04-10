package oneway.NovelViewer.Novel;

import android.util.Log;

import java.io.File;
import java.util.LinkedList;

import oneway.NovelViewer.Data.MyFileIO;

public class NovelInfo {
    public String novelPath = "";
    public String name = "";
    public String path = "";
    public String imgPath="";
    //public Novel novel;
    public NovelInfo(File di)
    {
        //一般来讲有且肯定有一个.txt文件作为小说的，没有的话，我来整理
        path=di.getPath();
        LinkedList<File> files = MyFileIO.GetSubFiles(di);
        File file=files.get(0);
        novelPath=file.getPath();
        String name1=file.getName();
        //int id=name1.indexOf('.');
        //name=name1.substring(0,id);

        File imgDir=new File(di.getAbsolutePath()+"/img/");
        if(imgDir.exists()) {
            File[] imgs = imgDir.listFiles();
            if (imgs.length > 0) {
                imgPath = imgs[0].getAbsolutePath();
            }
        }
        else
        {
            Log.i("NovelInfo","Image Directory Not Exist! "+imgDir.getAbsolutePath());
        }
        name=di.getName();
    }
}
