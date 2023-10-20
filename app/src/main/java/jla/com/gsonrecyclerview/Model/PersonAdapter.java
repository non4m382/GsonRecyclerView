package jla.com.gsonrecyclerview.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import jla.com.gsonrecyclerview.R;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonHolder> {

    List<Person> persons;

    Context context;

    public PersonAdapter(List<Person> persons, Context context) {
        this.persons = persons;
        this.context = context;
    }

    @NonNull
    @Override
    public PersonAdapter.PersonHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_person, parent, false);
        return new PersonHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonAdapter.PersonHolder holder, int position) {

//        holder.imv_ava.setImageResource(persons.get(position).getAva_id());

        Glide.with(context).load(persons.get(position).getAvatar()).into(holder.imv_ava);

        holder.tv_id.setText("Id: " + persons.get(position).getId() + "");
        holder.tv_email.setText("Email: " + persons.get(position).getEmail());
        holder.tv_firstName.setText("First Name: " + persons.get(position).getFirstName());
        holder.tv_lastName.setText("Last Name: " + persons.get(position).getLastName());
    }

    @Override
    public int getItemCount() {
        return persons.size();
    }

    public class PersonHolder extends RecyclerView.ViewHolder {

        ImageView imv_ava;

        TextView tv_id;

        TextView tv_email;
        TextView tv_firstName;
        TextView tv_lastName;


        public PersonHolder(@NonNull View itemView) {
            super(itemView);
            imv_ava = itemView.findViewById(R.id.imv_ava);
            tv_id = itemView.findViewById(R.id.tv_id);
            tv_email = itemView.findViewById(R.id.tv_email);
            tv_firstName = itemView.findViewById(R.id.tv_firstName);
            tv_lastName = itemView.findViewById(R.id.tv_lastName);
        }
    }
}
