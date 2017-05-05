package cricketscoreboard.scoring.kerbysoft.com.cricketscoreboard;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button newGameBtn, continueGameBtn, settingsBtn;
    Toast m_currentToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newGameBtn = (Button) this.findViewById(R.id.newGameBtn);
        continueGameBtn = (Button) this.findViewById(R.id.continueGameBtn);
        settingsBtn = (Button) this.findViewById(R.id.settingsBtn);

        newGameBtn.setOnClickListener(this);
        continueGameBtn.setOnClickListener(this);
        settingsBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.newGameBtn:
                Intent newGameIntent = new Intent(MainActivity.this, CricketActivity.class);
                newGameIntent.putExtra("openingPastGame", false);
                startActivity(newGameIntent);
                break;
            case R.id.continueGameBtn:
                Intent contIntent = new Intent(MainActivity.this, CricketActivity.class);
                contIntent.putExtra("openingPastGame", true);
                startActivity(contIntent);
                break;
            case R.id.settingsBtn:
                showMessage("Coming soon...");
                break;

        }

    }

    public void showMessage(String message) {
        if(m_currentToast != null)
        {
            m_currentToast.cancel();
        }
        m_currentToast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        m_currentToast.show();
    }
}
