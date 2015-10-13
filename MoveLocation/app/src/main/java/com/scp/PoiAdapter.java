package com.scp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.mapapi.search.core.PoiInfo;

import java.util.List;

/**
 * poi适配器
 */
public class PoiAdapter extends BaseAdapter {
    private Context context;
    private List<PoiInfo> pois;
    private LinearLayout linearLayout;


    PoiAdapter(Context context, List<PoiInfo> pois) {
        this.context = context;
        this.pois = pois;
    }

    @Override
    public int getCount() {
        return pois.size();
    }

    @Override
    public Object getItem(int position) {
        return pois.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.locationpois_item, null);
            linearLayout = (LinearLayout) convertView.findViewById(R.id.locationpois_linearlayout);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position == 0 && linearLayout.getChildCount() < 2) {
            ImageView imageView = new ImageView(context);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(32, 32);
            imageView.setLayoutParams(params);
            imageView.setBackgroundColor(Color.TRANSPARENT);
            imageView.setImageResource(R.mipmap.baidumap_ico_poi_on);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            linearLayout.addView(imageView, 0, params);
            holder.locationpoi_name.setTextColor(Color.parseColor("#FF5722"));
        }
        PoiInfo poiInfo = pois.get(position);
        holder.locationpoi_name.setText(poiInfo.name);
        holder.locationpoi_address.setText(poiInfo.address);
        return convertView;
    }

    class ViewHolder {
        TextView locationpoi_name;
        TextView locationpoi_address;

        ViewHolder(View view) {
            locationpoi_name = (TextView) view.findViewById(R.id.locationpois_name);
            locationpoi_address = (TextView) view.findViewById(R.id.locationpois_address);
        }
    }
}
