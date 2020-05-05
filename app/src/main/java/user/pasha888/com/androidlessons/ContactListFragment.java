package user.pasha888.com.androidlessons;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Objects;


public class ContactListFragment extends ListFragment {



    public static final Kontakt[] kontakt = {
            new Kontakt("Павел", "89042767668", 1),
            new Kontakt("Имя", "телефон", 2)
    };



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Список контактов");
        ArrayAdapter<Kontakt> adapter = new ArrayAdapter<Kontakt>(getContext(), android.R.layout.simple_list_item_1, kontakt){
            @NonNull
            @Override
            public View getView(int i, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.fragment_contact_list, null, false);
                }

                TextView nameView = convertView.findViewById(R.id.tv_name);
                TextView phoneNumberView = convertView.findViewById(R.id.tv_phoneNumber);
                Kontakt currentContact = kontakt[i];
                nameView.setText(currentContact.getName());
                phoneNumberView.setText(currentContact.getPhoneNumber());

                return convertView;
            }

        };

        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(@NonNull ListView listView, @Nullable View view, int i, long id) {

        ContactDetailsFragment f = new ContactDetailsFragment();
        Bundle args = new Bundle();
        args.putInt("index", i);
        f.setArguments(args);
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment, f);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
    @Override
    public void onStart() {
        super.onStart();
        getActivity().setTitle("Список контактов");
    }


}
