package dropdownmenu.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.lhm.dropdownmenuex.R;

import java.util.List;

/**
 * Created on 2018/11/26 14:47
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:下拉菜单
 */
public class ListDropDownAdapter extends BaseAdapter {

    private Context context;
    private List<String> list;
    private int checkItemPosition = 0;

    public void setCheckItem(int position) {
        checkItemPosition = position;
        notifyDataSetChanged();
    }

    public ListDropDownAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView != null) {
            viewHolder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_default_drop_down, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        fillValue(position, viewHolder);
        return convertView;
    }

    private void fillValue(int position, ViewHolder viewHolder) {
        viewHolder.mText.setText(list.get(position));
        if (checkItemPosition != -1) {
            if (checkItemPosition == position) {
//                viewHolder.mText.setTextColor(context.getResources().getColor(R.color.black));
                viewHolder.mText.setTextColor(context.getResources().getColor(R.color.blue1));
                viewHolder.mText.setBackgroundResource(R.color.white);
            } else {
//                viewHolder.mText.setTextColor(context.getResources().getColor(R.color.gray));
                viewHolder.mText.setTextColor(context.getResources().getColor(R.color.gray_666));
                viewHolder.mText.setBackgroundResource(R.color.white);
            }
        }
    }

    static class ViewHolder {
        TextView mText;

        ViewHolder(View view) {
            mText=view.findViewById(R.id.text);
        }
    }
}
