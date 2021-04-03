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
import com.app.formationjcikhniss.models.Users;

import java.util.List;

/**
 * adapt users data to the model
 * @author Aymen Masmoudi[03.04.2021]
 * */
public class UsersAdapter extends ArrayAdapter<Users> {

    private Context context;
    private int resource;

    private List<Users> items;

    public UsersAdapter(@NonNull Context context, int resource, @NonNull List<Users> objects) {
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
            view.setTag(holder);
        }else {
            holder = (Holder)view.getTag();
        }

        Users user = items.get(position);

        holder.tvName.setText(user.getName());
        holder.tvEmail.setText(user.getEmail());

        return view;
    }

    private class Holder{
        private TextView tvName, tvEmail;
    }

}
