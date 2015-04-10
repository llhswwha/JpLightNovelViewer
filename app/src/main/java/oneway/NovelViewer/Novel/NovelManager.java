package oneway.NovelViewer.Novel;

import java.io.File;
import java.util.LinkedList;

import oneway.NovelViewer.Data.FileNames;
import oneway.NovelViewer.Data.MyFileIO;
import oneway.NovelViewer.Helpers.AppContext;
import oneway.NovelViewer.Helpers.TimeHelper;

public class NovelManager {

    static NovelManager nm;
    public static void load()
    {
        //long start=System.currentTimeMillis();
        nm=new NovelManager();
        //AppContext.loadNovelsTime= TimeHelper.getTime(start);
    }

    public static NovelManager getInstance()
    {
        if(nm==null)
        {
            load();
        }
        return nm;
    }

    public String path = "";
    public String name = "";
    LinkedList<NovelCollection> series = new LinkedList<NovelCollection>();//Novel Series
    private NovelManager()
    {
        File root=new File(FileNames.dirPathNovelBase);
        LoadNovel(root);

    }
    void LoadNovel(File file)
    {
        if (!file.exists())
            return;
        this.path = file.getPath();
        LinkedList<File> dirs = MyFileIO.GetSubDirs(path);
        for (File dir : dirs)
        {
            series.add(new NovelCollection(dir));
        }
    }
    public LinkedList<String> getSeries()
    {
        LinkedList<String> names=new LinkedList<String>();
        for (NovelCollection s : series)
        {
            names.add(s.name);
        }
        return names;
    }
    public NovelCollection getSeries(int id)
    {
        return series.get(id);
    }
}
