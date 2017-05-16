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

    Button newGameBtn, continueGameBtn, friendsBtn, settingsBtn, logoutBtn;
    Toast m_currentToast;
    Boolean loggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(SaveSharedPreference.getFirstTime(getApplicationContext())) {
            Installation.id(getApplicationContext());
            SaveSharedPreference.setFirstTime(getApplicationContext(), false);
        }

        loggedIn = !(SaveSharedPreference.getUserName(getApplicationContext()).length() == 0);

        if (!loggedIn) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newGameBtn = (Button) this.findViewById(R.id.newGameBtn);
        continueGameBtn = (Button) this.findViewById(R.id.continueGameBtn);
        friendsBtn = (Button) this.findViewById(R.id.friendsBtn);
        settingsBtn = (Button) this.findViewById(R.id.settingsBtn);
        logoutBtn = (Button) this.findViewById(R.id.logoutBtn);

        newGameBtn.setOnClickListener(this);
        continueGameBtn.setOnClickListener(this);
        friendsBtn.setOnClickListener(this);
        settingsBtn.setOnClickListener(this);
        logoutBtn.setOnClickListener(this);
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
            case R.id.friendsBtn:
                Intent friendIntent = new Intent(MainActivity.this, FriendsActivity.class);
                startActivity(friendIntent);
                break;
            case R.id.settingsBtn:
                showMessage("Coming soon...");
                break;
            case R.id.logoutBtn:
                if (loggedIn)
                    logout();
                else {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
        }

    }

    public void logout() {

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);

        builder
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        SaveSharedPreference.setUserName(getApplicationContext(), "");
                        SaveSharedPreference.setPassword(getApplicationContext(), "");
                        SaveSharedPreference.setId(getApplicationContext(), 0);
                        SaveSharedPreference.setTeamname(getApplicationContext(), "");

                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //dismiss
                    }
                })
                .show();
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
