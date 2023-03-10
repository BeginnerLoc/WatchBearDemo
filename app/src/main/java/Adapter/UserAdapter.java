package Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.watchbeardemo.MessageActivity;
import com.example.watchbeardemo.R;

import java.util.List;

import Models.User;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.CustomViewHolder> {

    private Context mContext;
    private List<User> mUsers;

    public UserAdapter(Context context, List<User> users){
        this.mContext = context;
        this.mUsers = users;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.user_item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        User user = mUsers.get(position);
        holder.username.setText(user.getUsername());
        if(user.getUsername().equalsIgnoreCase("scammer")){
            holder.username.setTextColor(Color.parseColor("#ff0000"));
        }

        if (user.getImageURL().equals("default")){
            holder.profile_image.setImageResource(R.mipmap.ic_launcher);
        }else{
            holder.profile_image.setImageResource(R.drawable.scammer1);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MessageActivity.class);
                intent.putExtra("userid", user.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{

        public TextView username;
        public ImageView profile_image;

        public CustomViewHolder(View itemView){
            super(itemView);
            username = itemView.findViewById(R.id.username);
            profile_image = itemView.findViewById(R.id.profile_image);
        }
    }
}
