package oneway.NovelViewer.Word;

import java.util.HashMap;

public class WuShiYin {
    static String[] PingJiaMing = new String[] { "あいうえお", "かきくけこ", "さしすせそ", "たちつてと", "なにぬねの", "はひふへほ", "まみむめも", "やゆよ   ", "らりるれろ", "わをん  " ,
            "がぎぐげご", "ざじずぜぞ", "だぢづでど",               "ばびぶべぼ", "ぱぴぷぺぽ"};
    static String pingJia = "あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわをんがぎぐげござじずぜぞだぢずでどばびぶべぼぱぴぷぺぽゃっ";
    static String pianJia = "アイウエオカキクケコサシスセソタチツテトナニヌネノハヒフヘホマミムメモヤユヨラリルレロワオンガギグゲゴザジズゼゾダヂズデドバビブベボパピプペポャッ";

    public static String GetOriginalForm(String key)
    {
        // TODO Auto-generated method stub
        int id=pingJia.indexOf(key);
        int id2=id/5;
        Character c=PingJiaMing[id2].charAt(2);
        return c.toString();
    }


    static HashMap<String,String> AMap;
    public static String ConVertToA(String s)
    {
        if(AMap==null)
        {
            AMap=new HashMap<String,String>();
            AMap.put("う", "わ");
            AMap.put("く", "か");
            AMap.put("す", "さ");
            AMap.put("つ", "た");
            AMap.put("ぬ", "な");
            AMap.put("ふ", "は");
            AMap.put("む", "ま");
            AMap.put("ゆ", "や");//???
            AMap.put("る", "ら");//???
            AMap.put("ぐ", "が");
            AMap.put("ず", "ざ");//???
            AMap.put("づ", "だ");//???
            AMap.put("ぶ", "ば");
            AMap.put("ぷ", "ぱ");
        }
        String a=AMap.get(s);
        return a;
    }

    static HashMap<String,String> IMap;
    public static String ConVertToI(String s)
    {
        if(IMap==null)
        {
            IMap=new HashMap<String,String>();
            IMap.put("う", "い");
            IMap.put("く", "き");
            IMap.put("す", "し");
            IMap.put("つ", "ち");
            IMap.put("ぬ", "に");
            IMap.put("ふ", "ひ");
            IMap.put("む", "み");
            IMap.put("る", "り");//???
            IMap.put("ぐ", "ぎ");
            IMap.put("ず", "じ");//???
            IMap.put("づ", "ぢ");//???
            IMap.put("ぶ", "び");
            IMap.put("ぷ", "ぴ");
        }
        String i=IMap.get(s);
        return i;
    }

    static HashMap<String,String> EMap;
    public static String ConVertToE(String s)
    {
        if(EMap==null)
        {
            EMap=new HashMap<String,String>();
            EMap.put("う", "え");
            EMap.put("く", "け");
            EMap.put("す", "せ");
            EMap.put("つ", "て");
            EMap.put("ぬ", "ね");
            EMap.put("ふ", "へ");
            EMap.put("む", "め");
            EMap.put("る", "れ");
            EMap.put("ぐ", "げ");
            EMap.put("ず", "ぜ");
            EMap.put("づ", "で");
            EMap.put("ぶ", "べ");
            EMap.put("ぷ", "ぺ");
        }
        String e=EMap.get(s);
        return e;
    }

    static HashMap<String,String> OMap;
    public static String ConVertToO(String s)
    {
        if(OMap==null)
        {
            OMap=new HashMap<String,String>();
            OMap.put("う", "お");
            OMap.put("く", "こ");
            OMap.put("す", "そ");
            OMap.put("つ", "と");
            OMap.put("ぬ", "の");
            OMap.put("ふ", "ほ");
            OMap.put("む", "も");
            OMap.put("る", "ろ");
            OMap.put("ぐ", "ご");
            OMap.put("ず", "ぞ");
            OMap.put("づ", "ど");
            OMap.put("ぶ", "ぼ");
            OMap.put("ぷ", "ぽ");
        }
        String o=OMap.get(s);
        return o;
    }



    public static String Convert(String s, int from, int to)
    {
        if (to == 0 && s == "う")
        {
            return "わ";
        }
        for (String p : PingJiaMing)
        {
            if (p.substring(from,from+1).equals(s))
            {
                return p.substring(to,to+1);
            }
        }
        return "";
    }


    public static boolean IsPianJiaMing(String key)
    {
        int id=pingJia.indexOf(key);
        if(id==-1)
            return false;
        else
            return true;
    }

    /// <summary>
    /// 平假名转成片假名
    /// </summary>
    /// <param name="pin"></param>
    /// <returns></returns>
    public static String ConvertToPian(String pin)
    {
        String r = "";
        for (char c : pin.toCharArray())
        {
            r += Ping2Pian(c);
        }
        return r;
    }

    private static char Ping2Pian(char c)
    {
        int id = pianJia.indexOf(c);
        if (id != -1)
        {
            return pianJia.toCharArray()[id];
        }
        return c;
    }

    private static char Pian2Ping(char c)
    {
        int id = pingJia.indexOf(c);
        if (id != -1)
        {
            return pingJia.toCharArray()[id];
        }
        return c;
    }

    /// <summary>
    /// 片假名转成平假名
    /// </summary>
    /// <param name="pin"></param>
    /// <returns></returns>
    public static String ConvertToPing(String pin)
    {
        String r = "";
        for (char c : pin.toCharArray())
        {
            r += Pian2Ping(c);
        }
        return r;
    }

    /// <summary>
    /// 是否全是平假名
    /// </summary>
    /// <param name="word"></param>
    /// <returns></returns>
    public static boolean IsAllPing(String word)
    {
        return IsAll(word, pingJia);
    }

    /// <summary>
    /// 是否全是片假名
    /// </summary>
    /// <param name="word"></param>
    /// <returns></returns>
    public static boolean IsAllPian(String word)
    {
        return IsAll(word, pianJia);
    }

    /// <summary>
    /// 是否全是平假名/平假名
    /// </summary>
    /// <param name="word"></param>
    /// <param name="type"></param>
    /// <returns></returns>
    public static boolean IsAll(String word,String type)
    {
        for (char c : word.toCharArray())
        {
            int id = type.indexOf(c);
            if (id == -1)
                return false;
        }
        return true;
    }
}
