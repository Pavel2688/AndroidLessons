package user.pasha888.com.androidlessons;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ContactListFragment extends ListFragment {
    public static final Contacts[] contacts = {
            new Contacts("Павел", "89042767668", 1),
            new Contacts("Имя", "телефон", 2)
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Список контактов");
        ArrayAdapter<Contacts> adapter = new ArrayAdapter<Contacts>(getContext(), android.R.layout.simple_list_item_1, contacts){
            @NonNull
            @Override
            public View getView(int i, @Nullable View convertView, @NonNull ViewGroup parent) {
                if (convertView == null) {
                    convertView = getLayoutInflater().inflate(R.layout.fragment_contact_list, null, false);
                }
                TextView nameView = convertView.findViewById(R.id.tv_name);
                TextView phoneNumberView = convertView.findViewById(R.id.tv_phoneNumber);
                Contacts currentContact = contacts[i];
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
