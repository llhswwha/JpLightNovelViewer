package oneway.NovelViewer;


import oneway.NovelViewer.Word.Word;
import oneway.NovelViewer.WordBase.WordBase;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddWordActivity extends Activity
{
    EditText etWordName,etWordContent;
    EditText etWordType;
    //Spinner spWordType;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_word_view);

        GetViews();

        GetIntentInfo();
        GetClipBoardInfo();
    }
    void GetIntentInfo()
    {
        Intent intent=getIntent();
        String typeName=intent.getStringExtra("TypeName");
        if(typeName==null)
            typeName="名词";
        etWordType.setText(typeName);
        //int id=intent.getIntExtra("TypeId", 0);
        //spWordType.setId(id);
    }
    void GetViews()
    {
        etWordType=(EditText)findViewById(R.id.etWordType);
        etWordName=(EditText)findViewById(R.id.etWordName);
        etWordContent=(EditText)findViewById(R.id.etWordContent);
    }
    void GetClipBoardInfo()
    {
        ClipboardManager clip = (ClipboardManager)getSystemService(this.CLIPBOARD_SERVICE);
        String text=clip.getText().toString();    // 粘贴
        text=text.replace(".", ".\n");
        text=text.replace("。", "。\n");
        text=text.replace("。\n〕", "。〕");
        text=text.replace("。\n／", "。／");
        etWordContent.setText(text);

        String[] lines=text.split("\n");
        if(lines.length>0)
        {
            String firstLine=lines[0];
            String[] parts=firstLine.split(" ");
            if(parts.length<2)
                return;
            String name=parts[0]+" "+parts[1].substring(1,parts[1].length()-1);
            etWordName.setText(name);
        }
    }
    WordBase wb=new WordBase();
    void AddWord()
    {
        //int id=spWordType.getId();
        //String type=wb.GetTypeName(id);
        String type=etWordType.getText().toString();
        String name=etWordName.getText().toString();
        String content= etWordContent.getText().toString();
        Word word=new Word(type,name,content);
        wb.AddWord(word);
        Toast.makeText(this,"Add Word finished!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        String itemText=item.toString();
        if(itemText.equals("获取"))
            GetClipBoardInfo();
        if(itemText.equals("添加"))
            AddWord();
        if(itemText.equals("保存"))
            wb.saveWords();
        return super.onOptionsItemSelected(item);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        menu.add("获取");
        menu.add("添加");
        menu.add("保存");
        return super.onCreateOptionsMenu(menu);
    }
}
