package user.pasha888.com.androidlessons;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class ContactDetailsFragment extends Fragment implements CompoundButton.OnCheckedChangeListener {

    private ContactService contactService;
    View viewContactDetails;
    private int id;
    Contacts contactDetails;
    private TextView telephoneNumber;
    private TextView name;
    private TextView telephoneNumber2;
    private TextView email;
    private TextView email2;
    private TextView description;
    private TextView dataOfBirth;
    private ToggleButton toggleButton;
    public static final String BROAD_ACTION = "user.pasha888.com.androidlessons";
    private  String TAG_LOG = "ContactDetailsFragment";
    AlarmManager alarmManager;


    interface GetContact{
        void getDetailsContact(Contacts result);
    }
    //метод создает обект класса по id
    static ContactDetailsFragment newInstance(int id) {
        ContactDetailsFragment fragment = new ContactDetailsFragment();
        Bundle args = new Bundle();
        args.putInt("id",id);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ContactService.IContactService){
            contactService = ((ContactService.IContactService)context).getService();
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewContactDetails = inflater.inflate(R.layout.fragment_contact_details, container, false);

        name= viewContactDetails.findViewById(R.id.tv_name);
        dataOfBirth = viewContactDetails.findViewById(R.id.btnBirthdayReminder);
        telephoneNumber = viewContactDetails.findViewById(R.id.detailedContactNumber);
        telephoneNumber2 = viewContactDetails.findViewById(R.id.detailedContactNumber2);
        email = viewContactDetails.findViewById(R.id.email1);
        email2 = viewContactDetails.findViewById(R.id.email2);
        description = viewContactDetails.findViewById(R.id.description);

        getActivity().setTitle("Детали контактов");

        id = getArguments().getInt("id");

        toggleButton = viewContactDetails.findViewById(R.id.btnBirthdayReminder);

        if (PendingIntent.getBroadcast(getActivity(), 0,
                new Intent(BROAD_ACTION),
                PendingIntent.FLAG_NO_CREATE) != null){
            toggleButton.setChecked(false);
            toggleButton.setOnCheckedChangeListener(this);
        }else {
            toggleButton.setChecked(true);
            toggleButton.setOnCheckedChangeListener(this);
        }



        contactService.getDetailContact(callback,id);

        return viewContactDetails;
    }


    private GetContact callback = new GetContact() {
        @Override
        public void getDetailsContact(Contacts result) {
            contactDetails = result;
            if (viewContactDetails != null){
                viewContactDetails.post(new Runnable() {
                    @Override
                    public void run() {
                        if (name == null) return;

                        name.setText(contactDetails.getName());
                        dataOfBirth.setText(contactDetails.getDateOfBirth().get(Calendar.DATE) + "." +
                                contactDetails.getDateOfBirth().get(Calendar.MONTH) + "." +
                                contactDetails.getDateOfBirth().get(Calendar.YEAR));
                        telephoneNumber.setText(contactDetails.getTelephoneNumber());
                        telephoneNumber2.setText(contactDetails.getTelephoneNumber2());
                        email.setText(contactDetails.getEmail());
                        email2.setText(contactDetails.getEmail2());
                        description.setText(contactDetails.getDescription());
                    }
                });
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewContactDetails = null;
        telephoneNumber = null;
        name = null;
        telephoneNumber2 = null;
        email = null;
        email2 = null;
        description = null;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        alarmManager = (AlarmManager)getActivity().getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(BROAD_ACTION);
        intent.putExtra("id",id);
        intent.putExtra("textReminder", contactDetails.getName() + " " + getActivity().getString(R.string.text_notification));

        PendingIntent alarmIntent = PendingIntent.getBroadcast(getActivity(),0,intent,0);
        if (isChecked){
            Log.d(TAG_LOG, "Alarm on");

            Calendar calendar = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());

            calendar.set(Calendar.DATE,contactDetails.getDateOfBirth().get(Calendar.DATE));
            calendar.set(Calendar.MONTH,contactDetails.getDateOfBirth().get(Calendar.MONTH));
            calendar.set(Calendar.YEAR,Calendar.getInstance().get(Calendar.YEAR));
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);

            if (System.currentTimeMillis() > calendar.getTimeInMillis()){
                calendar.add(Calendar.YEAR,1);
            }
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),alarmIntent);
        }else {
            Log.d(TAG_LOG, "Alarm off");
            alarmManager.cancel(alarmIntent);
        }
    }



}
