package oneway.NovelViewer.Novel;

import java.io.File;
import java.util.LinkedList;

import oneway.NovelViewer.Data.MyFileIO;

public class Novel {
    public String path = "";
    public String name = "";
    public String text = "";
    LinkedList<NovelPart> parts = new LinkedList<NovelPart>();
    public LinkedList<NovelPart> getParts()
    {
        //if (parts.size() == 0)
        //		Divide();
        return parts;
    }
    public NovelPart get(int id)
    {
        return parts.get(id);
    }
    public Novel(String path)
    {
        this.path = path;
        name=new File(path).getName();
        Divide();
    }
    public Novel(String path,String dir)
    {
        this.path = path;
        name=new File(path).getName();

        String divideDir=dir+"/parts/";
        LinkedList<File> files=MyFileIO.GetSubFiles(divideDir);
        if(files.size()==0)//并未通过手机版进行切分
        {
            File file=new File(path);
            NovelPart part=new NovelPart(file);
            //parts.add(0,part);
            parts.add(part);
        }
        else
            for(File file : files)
            {
                NovelPart part=new NovelPart(file);
                //parts.add(0,part);
                parts.add(part);
            }
    }
    public void Divide()
    {
        //text = File.ReadAllText(path, Encoding.Default);
        //text = Regex.Replace(text, "《[^《》]+》", "");
        text=MyFileIO.ReadAllText(path);

        String[] lines=text.split("\n");
        String part = "";
        for (int i = 0; i < lines.length; i++)
        {
            if(lines[i].equals(""))
                continue;
            if (!lines[i].trim().equals("［＃改ページ］"))
            {
                part += lines[i] + "\n";
            }
            else
            {
                NovelPart novelPart=new NovelPart(part);
                if(parts.size()==0)
                    novelPart.name="介绍";
                parts.add(novelPart);
                part = "";
            }
        }
        NovelPart novelPart=new NovelPart(part);
        parts.add(novelPart);
    }
    public LinkedList<String> getIndex()
    {
        LinkedList<String> names=new LinkedList<String>();
        for(NovelPart part :parts)
        {
            names.add(part.name);
        }
        return names;
    }
    public String GetDirectory()
    {
        File file=new File(path);
        return file.getParent();
    }
}
