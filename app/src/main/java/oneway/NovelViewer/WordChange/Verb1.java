package oneway.NovelViewer.WordChange;

import java.util.HashMap;

import oneway.NovelViewer.Word.WuShiYin;

public class Verb1
{
    static String[] tails=new String[]{"り","ます","ない","よう","ろ","ば","れば","られる","させる","て","た"};

    public static String[] GetTails(String word,String tail)
    {
        //String a = WuShiYin.Convert(tail, 2, 0);
//        String i = WuShiYin.Convert(tail, 2, 1);
//        String e = WuShiYin.Convert(tail, 2, 3);
//        String o = WuShiYin.Convert(tail, 2, 4);

        String a = WuShiYin.ConVertToA(tail);
        String i = WuShiYin.ConVertToI(tail);
        String e = WuShiYin.ConVertToE(tail);
        String o = WuShiYin.ConVertToO(tail);

        int length=tails.length;
        String[] names=new String[50];
        int id=0;
        names[id++]= i;
        names[id++]= i + "ます";
        names[id++]= a + "ない";
        names[id++]= a + "なく";
        names[id++]= a + "なかった";
        names[id++]= a + "なければ";
        names[id++]= o + "う";
        names[id++]= e;
        names[id++]= e + "ば";
        names[id++]= e + "る";
        names[id++]= a + "れる";
        names[id++]= a + "れて";
        names[id++]= a + "れた";
        names[id++]= a + "れたり";
        names[id++]= a + "せる";
        names[id++]= a + "して";
        names[id++]= TeChange(word, tail);
        names[id++]= TeChange(word, tail)+ "いる";
        names[id++]= TeChange(word, tail)+ "いた";
        names[id++]= TeChange(word, tail)+ "ない";
        names[id++]= TeChange(word, tail)+ "なかった";
        names[id++]= TeChange(word, tail)+ "きた";
        names[id++]= TaChange(word, tail);
        names[id++]= TaChange(word, tail)+ "り";
        names[id++]= tail+ "べき";
        //names[11]= tail;

        return names;
    }
    public static String[] GetTails()
    {
        return tails;
    }
    static HashMap<String,String> TeMap;
    public static String TeChange(String word, String tail)//て形变化
    {
        if (word == "いく")//例外,行く
            return "って";

        if(TeMap==null)
        {
            TeMap=new HashMap<String,String>();
            TeMap.put("ぐ", "いで");
            TeMap.put("ぶ", "んで");
            TeMap.put("む", "んで");
            TeMap.put("ぬ", "んで");
            TeMap.put("つ", "って");
            TeMap.put("る", "って");
            TeMap.put("り", "って");
            TeMap.put("う", "って");
            TeMap.put("す", "して");
            TeMap.put("く", "いて");
        }

        String te=TeMap.get(tail);
        return te;

    }

    static HashMap<String,String> TaMap;
    public static String TaChange(String word, String tail)//て形变化
    {
        if (word == "いく")//例外,行く
            return "った";

        if(TaMap==null)
        {
            TaMap=new HashMap<String,String>();
            TaMap.put("ぐ", "いだ");
            TaMap.put("ぶ", "んだ");
            TaMap.put("む", "んだ");
            TaMap.put("ぬ", "んだ");
            TaMap.put("つ", "った");
            TaMap.put("る", "った");
            TaMap.put("り", "った");
            TaMap.put("う", "った");
            TaMap.put("す", "した");
            TaMap.put("く", "いた");
        }

        String ta=TaMap.get(tail);
        return ta;
//        if(tail.equals("ぐ"))
//        	return "いで";
//        
//        else if(tail.equals("ぶ"))
//        	return "んで";
//        else if(tail.equals("む"))
//        	return "んで";
//        else if(tail.equals("ぬ"))
//        	return "んで";
//
//        else if(tail.equals("つ"))
//        	return "って";
//        else if(tail.equals("る"))
//        	return "って";
//        else if(tail.equals("り"))
//        	return "って";
//        else if(tail.equals("う"))
//        	return "って";
//        
//        else if(tail.equals("す"))
//        	return "して";
//        else if(tail.equals("く"))
//        	return "いて";
//        return "";
    }


}
