package com.app.formationjcikhniss.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.app.formationjcikhniss.R;
import com.app.formationjcikhniss.models.NetUsers;

import java.util.List;

public class NetUsersAdapter extends ArrayAdapter<NetUsers> {

    private Context context;
    private int resource;
    private List<NetUsers> items;

    public NetUsersAdapter(@NonNull Context context, int resource, @NonNull List<NetUsers> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.items = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        Holder holder = new Holder();
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(resource, parent, false);
            holder.tvName = view.findViewById(R.id.tv_name);
            holder.tvEmail = view.findViewById(R.id.tv_email);
            holder.tvPhone = view.findViewById(R.id.tv_phone);
            view.setTag(holder);
        }else {
            holder = (Holder)view.getTag();
        }

        NetUsers user = items.get(position);

        holder.tvName.setText(user.getName());
        holder.tvEmail.setText(user.getEmail());
        holder.tvPhone.setText(user.getPhone());

        return view;
    }

    private class Holder{
        private TextView tvName, tvEmail, tvPhone;
    }

}
