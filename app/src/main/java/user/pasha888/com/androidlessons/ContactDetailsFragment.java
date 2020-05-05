package user.pasha888.com.androidlessons;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ContactDetailsFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contact_details, container, false);
        int i = this.getArguments().getInt("index");
        TextView detailedContactName = (TextView) view.findViewById(R.id.detailedContactName);
        TextView detailedContactNumber = (TextView) view.findViewById(R.id.detailedContactNumber);
        detailedContactName.setText(ContactListFragment.contacts[i].getName());
        detailedContactNumber.setText(ContactListFragment.contacts[i].getPhoneNumber());
        getActivity().setTitle("Детали контакта");
        return view;
    }


}
