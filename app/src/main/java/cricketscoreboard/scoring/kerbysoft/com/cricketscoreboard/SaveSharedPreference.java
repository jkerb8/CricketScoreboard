package cricketscoreboard.scoring.kerbysoft.com.cricketscoreboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


public class SaveSharedPreference
{
    static final String PREF_USER_EMAIL= "email";
    static final String PREF_USER_NAME= "username";
    static final String PREF_PASSWORD = "password";
    static final String PREF_ID = "id";
    static final String PREF_TEAMNAME = "teamname";
    static final String PREF_FIRST_TIME = "firsttime";
    static final String PREF_PLAYER_ONE = "Player 1";
    static final String PREF_PLAYER_TWO = "Player 2";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, String userName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.apply();
    }

    public static String getUserName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    public static void setEmail(Context ctx, String email)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_EMAIL, email);
        editor.apply();
    }

    public static String getEmail(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_EMAIL, "");
    }

    public static void setPassword (Context ctx, String password) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_PASSWORD, password);
        editor.apply();
    }

    public static String getPassword(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_PASSWORD, "");
    }

    public static void setId(Context ctx, int id)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt(PREF_ID, id);
        editor.apply();
    }

    public static int getId(Context ctx)
    {
        return getSharedPreferences(ctx).getInt(PREF_ID, 0);
    }

    public static void setTeamname(Context ctx, String teamname)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_TEAMNAME, teamname);
        editor.apply();
    }

    public static String getTeamname(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_TEAMNAME, "");
    }

    public static void setFirstTime(Context ctx, boolean firsttime)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putBoolean(PREF_FIRST_TIME, firsttime);
        editor.apply();
    }

    public static boolean getFirstTime(Context ctx)
    {
        return getSharedPreferences(ctx).getBoolean(PREF_FIRST_TIME, true);
    }

    public static void setPlayerOne(Context ctx, String name)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_PLAYER_ONE, name);
        editor.apply();
    }

    public static String getPlayerOne(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_PLAYER_ONE, "");
    }

    public static void setPlayerTwo(Context ctx, String name) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_PLAYER_TWO, name);
        editor.apply();
    }

    public static String getPlayerTwo(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_PLAYER_TWO, "");
    }
}
