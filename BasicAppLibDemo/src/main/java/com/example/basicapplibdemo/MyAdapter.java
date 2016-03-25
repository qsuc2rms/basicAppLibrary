package com.example.basicapplibdemo;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
/**
 * expandableListView适配器
 *
 */
public class MyAdapter extends BaseExpandableListAdapter {
	private Context context;
	private List<String> group;
	private List<List<String>> child;

	public MyAdapter(Context context, List<String> group,
			List<List<String>> child) {
		this.context = context;
		this.group = group;
		this.child = child;
	}

	@Override
	public int getGroupCount() {
		return group.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return child.get(groupPosition).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return group.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return child.get(groupPosition).get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}
	
	/**
	 * 显示：group
	 */
	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		ParentViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.layout_parent, null);
			holder = new ParentViewHolder();
			holder.textView = (TextView) convertView
					.findViewById(R.id.parent_textview);
			convertView.setTag(holder);
		} else {
			holder = (ParentViewHolder) convertView.getTag();
		}
		holder.textView.setText(group.get(groupPosition));
		holder.textView.setTextSize(25);
		holder.textView.setPadding(36, 10, 0, 10);
		return convertView;

	}
	
	/**
	 * 显示：child
	 */
	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		ChildViewHolder holder;
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.layout_child, null);
			holder = new ChildViewHolder();
			holder.textView = (TextView) convertView
					.findViewById(R.id.child_textview);
			convertView.setTag(holder);
		} else {
			holder = (ChildViewHolder) convertView.getTag();
		}
		holder.textView.setText(child.get(groupPosition).get(childPosition));
		holder.textView.setTextSize(20);
		holder.textView.setPadding(72, 10, 0, 10);
		return convertView;
	}

	class ParentViewHolder {
		TextView textView;
	}
	class ChildViewHolder {
		TextView textView;
	}
	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

}
