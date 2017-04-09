package com.accountsoft.Adapter;

import java.util.ArrayList;
import java.util.List;

import com.accountsoft.R;
import com.accountsoft.scalss.Picture;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class pictureAdapter extends BaseAdapter{
	
	private LayoutInflater inflater;
	private List<Picture> pictures;

	public pictureAdapter(String[] titles, int[] images,Context context){
		super();
		pictures =new ArrayList<Picture>();
		inflater= LayoutInflater.from(context);
		for (int i = 0; i < images.length; i++) {
			
			 Picture picture =new Picture(titles[i],images[i]);
			pictures.add(picture);
			
		}
		
	}
	

	@Override
	public int getCount() {
		
		if (null!=pictures) {
			return pictures.size();
			
		}else {
			return 0;
		}
		
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return pictures.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView==null) {
			convertView =inflater.inflate(R.layout.gvitem, null);
			viewHolder=new ViewHolder();
			viewHolder.title=(TextView)convertView.findViewById(R.id.itemtitle);
			viewHolder.image=(ImageView)convertView.findViewById(R.id.itemimage);
			convertView.setTag(viewHolder);
		}else {
			viewHolder=(ViewHolder)convertView.getTag();
		}
		viewHolder.title.setText(pictures.get(position).getTitle());
		viewHolder.image.setImageResource(pictures.get(position).getimageid());
		return convertView;
	}
	class ViewHolder{
		public TextView title;
		public ImageView image;
		
	}
	

}
