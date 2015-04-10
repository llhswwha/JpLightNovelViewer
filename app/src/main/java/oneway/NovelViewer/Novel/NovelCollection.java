package oneway.NovelViewer.Novel;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import oneway.NovelViewer.Data.MyFileIO;

public class NovelCollection {
    public String path = "";
    public String name = "";
    public LinkedList<NovelInfo> nis = new LinkedList<NovelInfo>();
    public NovelCollection(File path)
    {
        this.path = path.getPath();
        name =path.getName();
        try
        {
        	LinkedList<File> dirs = MyFileIO.GetSubDirs(path);
            for (File dir : dirs)
            {
            	nis.add(new NovelInfo(dir));
            }
        }
        catch(Exception e) { }

    }
	public LinkedList<String> getSeries() {
		LinkedList<String> names=new LinkedList<String>();
		for(NovelInfo ni : nis)
		{
			names.add(ni.name);
		}
		return names;
	}

    //get title and image
    public List<Map<String, Object>> getData(Object defaultImg) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for(NovelInfo ni : nis)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title", ni.name);
            if(ni.imgPath!=null && !ni.imgPath.equals(""))
            {
                map.put("img", ni.imgPath);
            }
            else
            {
                map.put("img", defaultImg);
            }
            list.add(map);
        }
        return list;
    }

	public NovelInfo get(int id)
	{
		return nis.get(id);
	}

}
