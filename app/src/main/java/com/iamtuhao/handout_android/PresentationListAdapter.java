package com.iamtuhao.handout_android;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * TODO load preview image
 */
public class PresentationListAdapter extends BaseAdapter {

    private static final String TAG = PresentationListAdapter.class.getSimpleName();

    @Override
    public int getCount() {
        return mPresentations.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private Context mContext;
    private ApiUtils.Presentation[] mPresentations;
    private LayoutInflater mInflater;

    public PresentationListAdapter(Context context, ApiUtils.Presentation[] presentations) {
        super();

        mContext = context;
        mPresentations = presentations;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ChildViewHolder cvh;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_cell, parent, false);

            cvh = new ChildViewHolder();

            convertView.setTag(cvh);
        } else {
            cvh = (ChildViewHolder) convertView.getTag();
        }

        String title = mPresentations[position].title;
        String owner = mPresentations[position].owner;
        final String downloadUrl = mPresentations[position].download_url;
        String lat = mPresentations[position].lat;
        String lng = mPresentations[position].lng;
        String previewUrl = mPresentations[position].preview_url;
        String distance = mPresentations[position].distance;
        String createTime = mPresentations[position].create_time;
        String updateTime = mPresentations[position].update_time;

        cvh.title = (TextView) convertView.findViewById(R.id.title_TextView);
        cvh.owner = (TextView) convertView.findViewById(R.id.owner_TextView);
        cvh.distance = (TextView) convertView.findViewById(R.id.distance_TextView);
        cvh.datetime = (TextView) convertView.findViewById(R.id.datetime_TextView);
        cvh.downloadUrl = (TextView) convertView.findViewById(R.id.downloadUrl_TextView);

        cvh.title.setText(title);
        cvh.owner.setText(owner);
        cvh.distance.setText(distance);
        cvh.datetime.setText(createTime);
        cvh.downloadUrl.setText(downloadUrl);
        cvh.downloadUrl.setMovementMethod(LinkMovementMethod.getInstance());
        cvh.downloadUrl.setVisibility(View.GONE);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Uri uri = Uri.parse(downloadUrl);

                final Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(uri, "text/html");
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);

                PackageManager packageManager = mContext.getPackageManager();
                List intentList = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                if (intentList.size() == 0) {
                    // error
                    Toast.makeText(mContext, "Open web.", Toast.LENGTH_SHORT).show();
                    Intent webIntent = new Intent(mContext, WebActivity.class);
                    webIntent.putExtra("URL", downloadUrl);
                    mContext.startActivity(webIntent);
                } else {
                    mContext.startActivity(intent);
                }
            }
        });

        return convertView;
    }

    private static class ChildViewHolder {
        TextView title;
        TextView owner;
        TextView distance;
        TextView datetime;
        TextView downloadUrl;
    }
}
