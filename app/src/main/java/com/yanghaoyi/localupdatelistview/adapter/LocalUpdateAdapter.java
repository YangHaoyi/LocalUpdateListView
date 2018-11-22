package com.yanghaoyi.localupdatelistview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yanghaoyi.localupdatelistview.R;
import com.yanghaoyi.localupdatelistview.data.WechatFriendsData;

import java.util.List;

/**
 * @author : YangHaoYi on 2018/11/21.
 *         Email  :  yang.haoyi@qq.com
 *         Description :局部更新适配器Adapter
 *         Change : YangHaoYi on 2018/11/21.
 *         Version : V 1.0
 */
public class LocalUpdateAdapter extends BaseAdapter{
    private Context mContext;

    private List<WechatFriendsData> friendsDataList;

    public LocalUpdateAdapter(Context context, List<WechatFriendsData> list) {
        super();
        this.mContext = context;
        this.friendsDataList = list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list, null);
            holder = new ViewHolder();
            holder.ivHead = convertView.findViewById(R.id.ivHead);
            holder.tvName = convertView.findViewById(R.id.tvName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Glide.with(mContext).load(friendsDataList.get(position).getHeadPath()).into(holder.ivHead);
        holder.tvName.setText(friendsDataList.get(position).getName());

        return convertView;
    }

    @Override
    public int getCount() {
        return friendsDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder {
        private ImageView ivHead;
        private TextView tvName;
    }

    public void updateView(int position,String headPath, ListView listView) {
        int visibleFirstPosition = listView.getFirstVisiblePosition();
        int visibleLastPosition = listView.getLastVisiblePosition();
        if (position >= visibleFirstPosition && position <= visibleLastPosition) {
            View view = listView.getChildAt(position - visibleFirstPosition);
            ViewHolder holder = (ViewHolder) view.getTag();
            updateList(position,headPath);
            Glide.with(mContext).load(friendsDataList.get(position).getHeadPath()).into(holder.ivHead);
        } else {
            updateList(position,headPath);
        }
    }

    private void updateList(int position,String headPath){
        WechatFriendsData wechatFriendsData = new WechatFriendsData();
        wechatFriendsData.setHeadPath(headPath);
        friendsDataList.set(position, wechatFriendsData);
    }


}
