package oneway.NovelViewer.Novel;

import java.util.LinkedList;

import oneway.NovelViewer.Helpers.Setting;

/**
 * Created by Administrator on 2015/4/8.
 */
public class NovelPartHelper {

    public static boolean IsImage(String line)
    {
        line=line.trim();
        if(line.contains("※［＃挿絵画像"))
            return true;
        if(line.contains("［＃挿絵"))
            return true;
        if(line.contains("<img src="))
            return true;
        return false;
    }

    private static int getSubStringIndex(String txt,String spliter)
    {
        int id=-1;
        while(id< Setting.minCount)
        {
            int id2=txt.indexOf(spliter, id+1);
            if(id2==-1)
            {
                id2=txt.indexOf(")", id+1);
                if(id2==-1)
                {
                    break;
                }
            }
            if(id2<Setting.maxCount)
            {
                id=id2;
            }
            else
            {
                break;
            }
        }
        return id;
    }

    static LinkedList<String> result;
    public static LinkedList<String> GetLines(String txt)
    {
        LinkedList<Integer> counts=new LinkedList<Integer>();
        LinkedList<String> lines=new LinkedList<String>();
        result=new LinkedList<String>();
        //String[] parts=txt.split("[\n\r　]");
        String[] parts=txt.split("[\n\r]");
        for(int i=0;i<parts.length;i++)
        {
            String part=parts[i];
            if(part.length()!=0) {
                lines.add(part);
                counts.add(part.length());
            }
        }
        //for(int i=0;i<parts.length-1;)
        while(lines.size()>0)
        {
            String line1=lines.poll();
            if(lines.size()==0)
            {
                result.add(line1);
                continue;
            }
            if(IsImage(line1))
            {
                result.add(line1);
                continue;
            }

            int length=line1.length();
            if(length>Setting.maxCount)
            {
                int id=getSubStringIndex(line1,"。");
                if(id==-1)
                    id=getSubStringIndex(line1,"、");
                if(id!=-1)
                {
                    String newLine1=line1.substring(0,id+1);
                    String newLine2=line1.substring(id+1,line1.length());
                    result.add(newLine1);
                    lines.push(newLine2);
                }
                else
                {
                    id=getSubStringIndex(line1,"、");
                    if(lines.size()==3873)
                    {
                        System.out.println("Error");
                    }
                    System.out.println("Error2");
                }
            }
            else//&&length<=maxCount
            {
                if(length>Setting.minCount)
                {
                    result.add(line1);
                }
                else
                {
                    //todo:合并
                    String line2=lines.poll();
                    if(IsImage(line2))
                    {
                        result.add(line1);
                        result.add(line2);
                    }
                    else
                    {
                        int length2=line1.length()+line2.length();
                        if(length2>Setting.maxCount)
                        {
                            result.add(line1);
                            lines.push(line2);
                        }
                        else
                        {
                            if(length2>Setting.minCount)
                            {
                                result.add(line1+"\n"+line2);
                            }
                            else
                            {
                                lines.push(line1+"\n"+line2);
                            }
                        }
                    }
                }
            }
        }

        return result;
    }
}
