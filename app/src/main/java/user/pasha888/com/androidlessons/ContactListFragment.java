package user.pasha888.com.androidlessons;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ContactListFragment extends ListFragment {
    private ContactService contactService;
    private Contacts[] contacts;
    private  String TAG_LOG = "LIST";
    private View view;

    TextView name;
    TextView telephoneNumber;
    interface GetContact{
        void getContactList(Contacts[] result);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ContactService.IContactService){
            contactService = ((ContactService.IContactService)context).getService();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG_LOG, "CREATE");
        view = super.onCreateView(inflater, container, savedInstanceState);
        getActivity().setTitle("Список контактов");
        contactService.getListContacts(callback);
        return view;
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.d(TAG_LOG, "KLIK");
        showDetails(position);
    }

    private void showDetails(int position) {
        ContactDetailsFragment contactDetailsFragment = ContactDetailsFragment.newInstance(position);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content, contactDetailsFragment).addToBackStack(null).commit();
    }

    private GetContact callback = new GetContact() {
        @Override
        public void getContactList(Contacts[] result) {
            final Contacts[] contacts = result;
            if (view != null){
                view.post(new Runnable() {
                    @Override
                    public void run() {
                        ArrayAdapter<Contacts> contactArrayAdapter = new ArrayAdapter<Contacts>(getActivity(),
                                0, contacts) {
                            @Override
                            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                                View listItem = convertView;

                                if (listItem == null)
                                    listItem = getLayoutInflater().inflate(R.layout.fragment_contact_list, null, false);

                                Contacts currentContact = contacts[position];

                                name = (TextView) listItem.findViewById(R.id.tv_name);
                                telephoneNumber = (TextView) listItem.findViewById(R.id.tv_phoneNumber);

                                if (name == null) return listItem;

                                name.setText(currentContact.getName());
                                telephoneNumber.setText(currentContact.getTelephoneNumber());

                                return listItem;
                            }
                        };
                        setListAdapter(contactArrayAdapter);
                    }
                });
            }
        }
    };
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        view = null;
        name = null;
        telephoneNumber = null;
    }


}
