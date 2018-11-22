package com.yanghaoyi.localupdatelistview.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ListView;

import com.yanghaoyi.localupdatelistview.R;
import com.yanghaoyi.localupdatelistview.adapter.LocalUpdateAdapter;
import com.yanghaoyi.localupdatelistview.data.WechatFriendsData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author : YangHaoYi on 2018/11/21.
 *         Email  :  yang.haoyi@qq.com
 *         Description :单点刷新ListView
 *         Change : YangHaoYi on 2018/11/21.
 *         Version : V 1.0
 */
public class LocalUpdateListViewActivity extends FragmentActivity {

    private static final int LIST_COUNT = 100;
    private static final String FRIENDS = "  Friends";

    private List<WechatFriendsData> listData = new ArrayList<>();
    private LocalUpdateAdapter adapter;
    private ListView lvFriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_update);
        init();
    }

    private void init(){
        initView();
        initData();
        initEvent();
    }

    private void initView(){
        lvFriends = findViewById(R.id.lvFriends);
    }

    private void initData(){
        for(int i=0;i<LIST_COUNT;i++){
            WechatFriendsData wechatFriendsData = new WechatFriendsData();
            wechatFriendsData.setName(i+FRIENDS);
            wechatFriendsData.setHeadPath("");
            listData.add(wechatFriendsData);
        }
        adapter = new LocalUpdateAdapter(this,listData);
        lvFriends.setAdapter(adapter);
    }

    private void initEvent(){
        ScheduledExecutorService delayThreadPool = Executors.newScheduledThreadPool(3);
        delayThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                adapter.updateView(3,"https://ws1.sinaimg.cn/large/0065oQSqgy1fxd7vcz86nj30qo0ybqc1.jpg",lvFriends);
            }
        },1000, TimeUnit.MILLISECONDS);
        delayThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                adapter.updateView(10,"https://ws1.sinaimg.cn/large/0065oQSqgy1fwyf0wr8hhj30ie0nhq6p.jpg",lvFriends);
            }
        },3000,TimeUnit.MILLISECONDS);
        delayThreadPool.schedule(new Runnable() {
            @Override
            public void run() {
                adapter.updateView(19,"https://ws1.sinaimg.cn/large/0065oQSqgy1fwgzx8n1syj30sg15h7ew.jpg",lvFriends);
            }
        },5000,TimeUnit.MILLISECONDS);
    }

}
