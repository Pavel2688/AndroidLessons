package user.pasha888.com.androidlessons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    public LinearLayout linearLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onKnopka(View view) {
        linearLayout = (LinearLayout) findViewById(R.id.layout);
      //  Button fotoButton =(Button)findViewById(R.id.foto);

        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.getLayoutParams().height=2000;
        setTitle("Детали контакта");
    }
    @Override
    public void onBackPressed()//если нажал кнопку назад
    {
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        linearLayout.getLayoutParams().height=550;
        setTitle("Список контактов");
    }
}
