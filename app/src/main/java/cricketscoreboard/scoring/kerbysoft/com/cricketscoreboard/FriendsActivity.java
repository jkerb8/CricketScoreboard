package cricketscoreboard.scoring.kerbysoft.com.cricketscoreboard;

import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.Base64;

import org.json.JSONObject;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.protocol.HTTP;
import cz.msebera.android.httpclient.util.EntityUtils;

public class FriendsActivity extends AppCompatActivity implements View.OnClickListener {

    Button addFriendBtn;
    Toast m_currentToast;
    private FriendsActivity.SendFriendRequestTask mFriendRequestTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        addFriendBtn = (Button) findViewById(R.id.addFriendBtn);

        addFriendBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addFriendBtn:
                addFriendDialog();
                break;
        }
    }

    private void addFriendDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_friend_layout);
        dialog.setTitle("");

        final Button cancelBtn = (Button) dialog.findViewById(R.id.addFriendCancelBtn);
        final Button saveBtn = (Button) dialog.findViewById(R.id.addFriendSaveBtn);
        final EditText usernameInput = (EditText) dialog.findViewById(R.id.friendUsernameInput);

        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);

        usernameInput.requestFocus();

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = usernameInput.getText().toString();
                if (!username.equals("")) {
                    sendFriendRequest(username);
                    dialog.dismiss();
                }
                else {
                    usernameInput.setError("Input a username");
                    usernameInput.requestFocus();
                }
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    private void sendFriendRequest(String username) {
        mFriendRequestTask = new FriendsActivity.SendFriendRequestTask(username);
        mFriendRequestTask.execute((Void) null);
    }

    private void showAddedFriendSnackbar(View view, String username) {
        Snackbar.make(view, "Friend Request sent to " + username, Snackbar.LENGTH_LONG)
                .setAction("Undo", null).show();
    }

    public void showMessage(String message) {
        if(m_currentToast != null)
        {
            m_currentToast.cancel();
        }
        m_currentToast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        m_currentToast.show();
    }



    private class SendFriendRequestTask extends AsyncTask<Void, Void, JSONObject> {

        private int userId;
        private String friendUserName;
        private int statusCode;
        private String loginURL = "http://192.168.0.101:2017/darts/sendFriendRequest";
        private String APIUser = "dartswithpals", API_KEY = "yed7a876-y0gz-11e6-9d9d-cth0c932ce02";

        SendFriendRequestTask(String username) {
            friendUserName = username;
            userId = SaveSharedPreference.getId(getApplicationContext());
        }

        @Override
        protected JSONObject doInBackground(Void... params) {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(loginURL);
            String message;

            try {
                //Authentication
                String encoded = "Basic " + Base64.encodeToString(
                        (APIUser + ":" + API_KEY).getBytes(),
                        Base64.NO_WRAP);
                httppost.setHeader("Authorization", encoded);
                httppost.setHeader(HTTP.CONTENT_TYPE,"application/json");

                //add the data
                JSONObject obj = new JSONObject();
                obj.put("friend_username", friendUserName);
                obj.put("user_id", userId);

                httppost.setEntity(new StringEntity(obj.toString(), "UTF-8"));

                // Execute HTTP Post Request
                HttpResponse response = httpclient.execute(httppost);
                message = EntityUtils.toString(response.getEntity());
                JSONObject message_json = new JSONObject(message);
                statusCode = message_json.getInt("status");
                Log.d("StatusCode", "StatusCode: " + statusCode);

                if (statusCode != 200) {
                    return null;
                }
                else {
                    return message_json;
                }

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }

        }

        protected void onPostExecute(final JSONObject message) {
            mFriendRequestTask = null;

            if (message != null) {
                showMessage("Friend request sent!");

            }
            else if (statusCode == 1000){
                showMessage("Username does not exist, please try again");

            }
            else {
                showMessage("An unexpected error occurred, please try again later");
            }

        }

        @Override
        protected void onCancelled() {
            mFriendRequestTask = null;
        }
    }

}
