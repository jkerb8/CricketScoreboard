package cricketscoreboard.scoring.kerbysoft.com.cricketscoreboard;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveSharedPreference
{
    static final String PREF_PLAYER_ONE= "playerone";
    static final String PREF_PLAYER_TWO= "playertwo";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
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