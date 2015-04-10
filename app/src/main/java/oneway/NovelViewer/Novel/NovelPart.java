package oneway.NovelViewer.Novel;

import java.io.File;
import java.util.LinkedList;
import java.util.StringTokenizer;

import oneway.NovelViewer.Data.MyFileIO;

public class NovelPart
{
    String path;
    public String name;
    String text;
    //    public String[] getPagragraphs()
//    {
//            String[] parts = text.split("\n");
//            LinkedList<String> parts2 = new LinkedList<String>();
//            for (String part : parts)
//            {
//                if (part.trim() != "")
//                {
//                	 String t = RemoveComment(part);
//                     parts2.add(t);
//                }
//            }
//            return (String[]) parts2.toArray();
//    }

    void AddLine(LinkedList<String> lines,String newLine)
    {
        newLine=newLine.trim();
        if(newLine!="")
            lines.add(newLine);
    }

    public LinkedList<String> GetLines()
    {
        return NovelPartHelper.GetLines(getText());
    }

    /*public LinkedList<String> GetLines() {
        lines = new LinkedList<String>();
        //StringTokenizer token = new StringTokenizer(text, "\n？。」　");
        String text = getText();
        StringTokenizer token = new StringTokenizer(text, "\n\r　");
        while (token.hasMoreTokens()) {
            String line = token.nextToken();
            line = line.trim();
            line = RemoveComment(line);

            if (line.length() > 0) {
                if (IsImage(line)) {
                    AddLine(lines, line);
                    continue;
                }

                StringBuilder builder = new StringBuilder(line);
                if (addALine(builder, token, lineLength * 2)) continue;
                if (addALine(builder, token, lineLength * 3)) continue;
                if (addALine(builder, token, lineLength * 4)) continue;
                line=builder.toString();

                if (line.length() < lineLength * 5) {
                    AddLine(lines, line);//最理想的是lineLength*4-lineLength*5之间的长度
                } else//太多了拆开
                {
                    String[] parts = line.split("。");
                    String newLine = "";

                    for (String part : parts) {
                        if (part.trim() == "")
                            continue;
                        String tmp = newLine + part;
                        if (tmp.length() < lineLength * 5) {
                            newLine += part + "。";
                        } else {
                            lines.add(newLine);
                            newLine = part + "。";
                        }
                    }

                    AddLine(lines, newLine);
                }
            }
        }
        return lines;
    }*/

    /*private boolean addALine(StringBuilder builder,StringTokenizer token )
    {
        String nextLine=token.nextToken();//新行，
        if(IsImage(nextLine))//是否图像
        {
            //增加2行
            AddLine(lines,builder.toString());
            AddLine(lines,nextLine);//图像单独一行
            //continue;
            return true;
        }
        else
        {
            builder.append("\n"+nextLine);
            return false;
        }
    }

    private boolean addALine(StringBuilder builder,StringTokenizer token ,int maxCount)
    {
        if(builder.length()<maxCount&&token.hasMoreTokens())//太少了，合起来
        {
            return addALine(builder,token);
        }
        return false;
    }*/

    String RemoveComment(String text)
    {
        int id = text.indexOf("［＃「※」");
        if (id == -1)
            return text;
        int id2 = text.indexOf("］");
        String pre=text.substring(0,id);
        String next=text.substring(id2+1);
        String result=pre+next;
        return result;
    }

    public NovelPart(String text)
    {
        this.text = text.replace("｜", "").trim();
        GetName();
    }
    public NovelPart(File file) {
        path=file.getPath();

        int id11=path.lastIndexOf(" ");
        int id12=path.lastIndexOf("/");
        int id1=id11;
        if(id12>id11)
            id1=id12;

        int id2=path.lastIndexOf(".");
        name=path.substring(id1+1,id2);
        id1=0;
    }
    void GetName()
    {
        String[] lines = text.split("\n");
        for(int i=0;i<lines.length&&i<10;i++)
        {
            String line = lines[i].trim();
            if(line.equals(""))
                continue;
            if(line.startsWith("※"))
                continue;
            if(line.startsWith("<img"))
                continue;
            if(name==null)
                name = line;
            if (line.startsWith("第"))
            {
                name = line;
                break;
            }
        }
    }
    public String getText()
    {
        if(text==null&&path!=null)
            text=MyFileIO.ReadAllText(path);
        return text;
    }
    public void Combine(String newName,NovelPart other)
    {
        name = newName;
        Combine(other);
    }
    public void Combine(NovelPart other)
    {
        text = text + other.text;
    }
}
