package cricketscoreboard.scoring.kerbysoft.com.cricketscoreboard;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.Base64;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.client.HttpClient;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.impl.client.DefaultHttpClient;
import cz.msebera.android.httpclient.protocol.HTTP;
import cz.msebera.android.httpclient.util.EntityUtils;

public class CreateUserActivity extends AppCompatActivity {

    EditText emailInput, usernameInput, passInput, pass2Input;
    Button saveAcctBtn;
    Toast m_currentToast;
    private CreateUserTask mCreateUserTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);

        emailInput = (EditText) findViewById(R.id.emailInput);
        usernameInput = (EditText) findViewById(R.id.usernameInput);
        passInput = (EditText) findViewById(R.id.passwordInput);
        pass2Input = (EditText) findViewById(R.id.password2Input);

        saveAcctBtn = (Button) findViewById(R.id.saveAcctBtn);
        saveAcctBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfValid();
            }
        });
    }

    public void checkIfValid() {

        emailInput.setError(null);
        passInput.setError(null);
        pass2Input.setError(null);

        // Store values at the time of the login attempt.
        String email = emailInput.getText().toString();
        String username = usernameInput.getText().toString();
        String password = passInput.getText().toString();
        String password2 = pass2Input.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            passInput.setError(getString(R.string.error_invalid_password));
            focusView = passInput;
            cancel = true;
        }
        else if (!password.equals(password2)) {
            pass2Input.setError("Passwords do not match");
            focusView = pass2Input;
            cancel = true;
        }

        if (username.length() < 4) {
            usernameInput.setError("Must be at least 4 characters");
            focusView = usernameInput;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            emailInput.setError(getString(R.string.error_field_required));
            focusView = emailInput;
            cancel = true;
        } else if (!isEmailValid(email)) {
            emailInput.setError(getString(R.string.error_invalid_email));
            focusView = emailInput;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            mCreateUserTask = new CreateUserActivity.CreateUserTask(email, username, password);
            mCreateUserTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    public void showMessage(String message) {
        if(m_currentToast != null)
        {
            m_currentToast.cancel();
        }
        m_currentToast = Toast.makeText(this, message, Toast.LENGTH_LONG);
        m_currentToast.show();
    }

    public class CreateUserTask extends AsyncTask<Void, Void, JSONObject> {

        private final String mEmail;
        private final String mPassword;
        private String mUsername;
        private int statusCode;
        private String loginURL = "http://192.168.1.104:2017/darts/createUser";
        private String APIUser = "dartswithpals", API_KEY = "yed7a876-y0gz-11e6-9d9d-cth0c932ce02";

        CreateUserTask(String email, String username, String password) {
            mEmail = email;
            mPassword = password;
            mUsername = username;
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
                obj.put("email", mEmail);
                obj.put("username", mUsername);
                obj.put("password", mPassword);

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
            mCreateUserTask = null;

            if (message != null) {

                showMessage("User created, sign in to continue");

                finish();
            }
            else if (statusCode == 1000){
                showMessage("Username or email already exists");
            }
            else {
                showMessage("An unexpected error occurred, please try again later");
            }

        }

        @Override
        protected void onCancelled() {
            mCreateUserTask = null;
        }
    }

}
