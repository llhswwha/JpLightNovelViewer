package oneway.NovelViewer;

import oneway.NovelViewer.ActivityParrent.SpinnerActivity;
import oneway.NovelViewer.Data.FileNames;
import oneway.NovelViewer.Novel.*;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import java.util.*;

public class NovelIndexActivity extends SpinnerActivity
{
    NovelManager nm;

    NovelCollection currentSeries;
    FileNames fileNames=new FileNames();
    Spinner spSeries;
    ListView lvNames;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.novel_index);
        nm=NovelManager.getInstance();

        //InitNovelSeries List
        spSeries=(Spinner)findViewById(R.id.spNovelSeries);
        spSeries.setOnItemSelectedListener(new SpItemSelectedListener());
        SetSpinnerItems(spSeries,nm.getSeries());

        lvNames=(ListView)findViewById(R.id.lvNovelNames);
        lvNames.setOnItemClickListener(new LVItemClickListener());
    }
    public class SpItemSelectedListener implements OnItemSelectedListener
    {
        public void onItemSelected(AdapterView<?> parrent,View view,int position,long id)
        {
            SelectNovelSeries((int) id);
        }
        public void onNothingSelected(AdapterView<?> parrent)
        {

        }
    }
    public class LVItemClickListener implements OnItemClickListener
    {
        public void onItemSelected(AdapterView<?> parrent,View view,int position,long id)
        {

        }
        public void onNothingSelected(AdapterView<?> parrent)
        {

        }

        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            // TODO Auto-generated method stub
            OpenNovelFile((int) arg3);
        }
    }
    public void SelectNovelSeries(int id)
    {
        currentSeries=nm.getSeries(id);
        List<String> seriesName=currentSeries.getSeries();
        //lvNames.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,seriesName));

        SimpleAdapter adapter = new SimpleAdapter(this,currentSeries.getData(R.drawable.ic_launcher),R.layout.novel_item_view, new String[]{"title","img"}, new int[]{R.id.title,R.id.img});
        lvNames.setAdapter(adapter);
    }

    private List<Map<String, Object>> getData(List<String> seriesName) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for(String sn : seriesName)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("title", sn);
            map.put("img", R.drawable.ic_launcher);
            list.add(map);
        }
        return list;
    }

    public void OpenNovelFile(int id)
    {
        NovelInfo ni=currentSeries.get(id);
        Intent intent=new Intent();
        intent.putExtra("fileName",ni.novelPath);
        intent.putExtra("dir",ni.path);
        //intent.setClass(NovelIndexActivity.this, NovelViewerActivity.class);
        intent.setClass(NovelIndexActivity.this, NovelViewerWordsActivity.class);
        NovelIndexActivity.this.startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // TODO Auto-generated method stub
        menu.add(Menu.NONE, Menu.FIRST + 1, 5, "词库").setIcon(android.R.drawable.ic_menu_help);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        int id=item.getItemId();
        if(id==Menu.FIRST+1)
        {
            Intent intent=new Intent();
            intent.setClass(NovelIndexActivity.this, WordBaseActivity.class);
            NovelIndexActivity.this.startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}