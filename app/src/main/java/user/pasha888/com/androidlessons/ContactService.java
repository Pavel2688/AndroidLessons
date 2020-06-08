package user.pasha888.com.androidlessons;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ContactService extends Service {
    public interface IContactService{
        ContactService getService();
    }
   // private  String TAG_LOG = "SERVICE";
    private IBinder binder = new LocalService();
    public static final Contacts[] contacts = {
            new Contacts("Павел", "89042767668", "111111111111", "aaaaaaaa",
                    "bbbbb", "description", new GregorianCalendar(1998, Calendar.JUNE,7)),
            new Contacts("Имя", "телефон", "телефон2", "email",
                    "email2", "description", new GregorianCalendar(1998, Calendar.JUNE,8))
    };


    @Override
    public void onCreate() {
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    public void getListContacts(ContactListFragment.GetContact callback){
        final WeakReference<ContactListFragment.GetContact> ref = new WeakReference<>(callback);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Contacts[] result = contacts;
                ContactListFragment.GetContact local = ref.get();
                if (local != null){
                    local.getContactList(result);
                }
            }
        }).start();
    }

    public void getDetailContact(ContactDetailsFragment.GetContact callback, int idContact){
        final WeakReference<ContactDetailsFragment.GetContact> ref = new WeakReference<>(callback);
        final int id = idContact;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Contacts result = contacts[id];
                ContactDetailsFragment.GetContact local = ref.get();
                if (local != null){
                    local.getDetailsContact(result);
                }
            }
        }).start();
    }

    class LocalService extends Binder {
        ContactService getService(){
            return ContactService.this;
        }
    }
}
