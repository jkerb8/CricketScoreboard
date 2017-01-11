package cricketscoreboard.scoring.kerbysoft.com.cricketscoreboard;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<Button> p1MinusBtns, p2MinusBtns;
    ArrayList<ImageButton> p1PlusBtns, p2PlusBtns;
    Button resetBtn;
    EditText p1EditText, p2EditText;
    TextView p1Score, p2Score;
    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        p1MinusBtns = new ArrayList<>();
        p2MinusBtns = new ArrayList<>();
        p1PlusBtns = new ArrayList<>();
        p2PlusBtns = new ArrayList<>();

        p1MinusBtns.add((Button) findViewById(R.id.p1minus1));
        p1MinusBtns.add((Button) findViewById(R.id.p1minus2));
        p1MinusBtns.add((Button) findViewById(R.id.p1minus3));
        p1MinusBtns.add((Button) findViewById(R.id.p1minus4));
        p1MinusBtns.add((Button) findViewById(R.id.p1minus5));
        p1MinusBtns.add((Button) findViewById(R.id.p1minus6));
        p1MinusBtns.add((Button) findViewById(R.id.p1minus7));

        p2MinusBtns.add((Button) findViewById(R.id.p2minus1));
        p2MinusBtns.add((Button) findViewById(R.id.p2minus2));
        p2MinusBtns.add((Button) findViewById(R.id.p2minus3));
        p2MinusBtns.add((Button) findViewById(R.id.p2minus4));
        p2MinusBtns.add((Button) findViewById(R.id.p2minus5));
        p2MinusBtns.add((Button) findViewById(R.id.p2minus6));
        p2MinusBtns.add((Button) findViewById(R.id.p2minus7));

        p1PlusBtns.add((ImageButton) findViewById(R.id.p1plus1));
        p1PlusBtns.add((ImageButton) findViewById(R.id.p1plus2));
        p1PlusBtns.add((ImageButton) findViewById(R.id.p1plus3));
        p1PlusBtns.add((ImageButton) findViewById(R.id.p1plus4));
        p1PlusBtns.add((ImageButton) findViewById(R.id.p1plus5));
        p1PlusBtns.add((ImageButton) findViewById(R.id.p1plus6));
        p1PlusBtns.add((ImageButton) findViewById(R.id.p1plus7));

        p2PlusBtns.add((ImageButton) findViewById(R.id.p2plus1));
        p2PlusBtns.add((ImageButton) findViewById(R.id.p2plus2));
        p2PlusBtns.add((ImageButton) findViewById(R.id.p2plus3));
        p2PlusBtns.add((ImageButton) findViewById(R.id.p2plus4));
        p2PlusBtns.add((ImageButton) findViewById(R.id.p2plus5));
        p2PlusBtns.add((ImageButton) findViewById(R.id.p2plus6));
        p2PlusBtns.add((ImageButton) findViewById(R.id.p2plus7));

        resetBtn = (Button) findViewById(R.id.resetButton);
        p1EditText = (EditText) findViewById(R.id.playerOneEditText);
        p2EditText = (EditText) findViewById(R.id.playerTwoEditText);
        p1Score = (TextView) findViewById(R.id.p1ScoreText);
        p2Score = (TextView) findViewById(R.id.p2ScoreText);

        for (Button b : p1MinusBtns) {
            b.setOnClickListener(this);
        }
        for (Button b : p2MinusBtns) {
            b.setOnClickListener(this);
        }
        for (ImageButton b : p1PlusBtns) {
            b.setOnClickListener(this);
        }
        for (ImageButton b : p2PlusBtns) {
            b.setOnClickListener(this);
        }
        resetBtn.setOnClickListener(this);

        game = new Game();
    }

    @Override
    public void onClick(View view) {
        p1EditText.clearFocus();
        p2EditText.clearFocus();

        switch (view.getId()) {
            case R.id.resetButton:
                confirmReset();
                return;
            case R.id.p1minus1:
                game.minus(1, 0);
                break;
            case R.id.p1minus2:
                game.minus(1, 1);
                break;
            case R.id.p1minus3:
                game.minus(1, 2);
                break;
            case R.id.p1minus4:
                game.minus(1, 3);
                break;
            case R.id.p1minus5:
                game.minus(1, 4);
                break;
            case R.id.p1minus6:
                game.minus(1, 5);
                break;
            case R.id.p1minus7:
                game.minus(1, 6);
                break;
            case R.id.p2minus1:
                game.minus(2, 0);
                break;
            case R.id.p2minus2:
                game.minus(2, 1);
                break;
            case R.id.p2minus3:
                game.minus(2, 2);
                break;
            case R.id.p2minus4:
                game.minus(2, 3);
                break;
            case R.id.p2minus5:
                game.minus(2, 4);
                break;
            case R.id.p2minus6:
                game.minus(2, 5);
                break;
            case R.id.p2minus7:
                game.minus(2, 6);
                break;
            case R.id.p1plus1:
                game.hit(1, 0);
                break;
            case R.id.p1plus2:
                game.hit(1, 1);
                break;
            case R.id.p1plus3:
                game.hit(1, 2);
                break;
            case R.id.p1plus4:
                game.hit(1, 3);
                break;
            case R.id.p1plus5:
                game.hit(1, 4);
                break;
            case R.id.p1plus6:
                game.hit(1, 5);
                break;
            case R.id.p1plus7:
                game.hit(1, 6);
                break;
            case R.id.p2plus1:
                game.hit(2, 0);
                break;
            case R.id.p2plus2:
                game.hit(2, 1);
                break;
            case R.id.p2plus3:
                game.hit(2, 2);
                break;
            case R.id.p2plus4:
                game.hit(2, 3);
                break;
            case R.id.p2plus5:
                game.hit(2, 4);
                break;
            case R.id.p2plus6:
                game.hit(2, 5);
                break;
            case R.id.p2plus7:
                game.hit(2, 6);
                break;
            default:
                return;
        }
        updateVisuals();
    }

    public void confirmReset() {
        new AlertDialog.Builder(this)
                .setTitle("Reset Game")
                .setMessage("Are you sure you want to reset the game?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        game.reset();
                        updateVisuals();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public void updateVisuals() {
        for (int i=0; i<7; i++) {
            //setting all of player1's scores
            if (game.getPlayer1().getScores()[i] > 0) {
                p1MinusBtns.get(i).setText(Integer.toString(game.getPlayer1().getScores()[i]));
            }
            else {
                p1MinusBtns.get(i).setText("");
            }

            //setting all of player2's scores
            if (game.getPlayer2().getScores()[i] > 0) {
                p2MinusBtns.get(i).setText(Integer.toString(game.getPlayer2().getScores()[i]));
            }
            else {
                p2MinusBtns.get(i).setText("");
            }

            //setting all of player1's states
            switch (game.getPlayer1().getStates()[i]) {
                case 0:
                    p1PlusBtns.get(i).setImageResource(R.drawable.nada);
                    break;
                case 1:
                    p1PlusBtns.get(i).setImageResource(R.drawable.oneslash);
                    break;
                case 2:
                    p1PlusBtns.get(i).setImageResource(R.drawable.twoslash);
                    break;
                case 3:
                    p1PlusBtns.get(i).setImageResource(R.drawable.threeslash);
                    break;
                default:
                    break;
            }

            //setting all of player2's states
            switch (game.getPlayer2().getStates()[i]) {
                case 0:
                    p2PlusBtns.get(i).setImageResource(R.drawable.nada);
                    break;
                case 1:
                    p2PlusBtns.get(i).setImageResource(R.drawable.oneslash);
                    break;
                case 2:
                    p2PlusBtns.get(i).setImageResource(R.drawable.twoslash);
                    break;
                case 3:
                    p2PlusBtns.get(i).setImageResource(R.drawable.threeslash);
                    break;
                default:
                    break;
            }
        }

        if (game.getPlayer1().getTotalScore() != 0) {
            p1Score.setText(Integer.toString(game.getPlayer1().getTotalScore()));
        }
        else {
            p1Score.setText("");
        }

        if (game.getPlayer2().getTotalScore() != 0) {
            p2Score.setText(Integer.toString(game.getPlayer2().getTotalScore()));
        }
        else {
            p2Score.setText("");
        }

        checkForWinner();
    }

    public void checkForWinner() {
        boolean p1Fin=true, p2Fin=true;
        for (int i=0; i<7; i++) {
            if (game.getPlayer1().getStates()[i] < 3) {
                p1Fin = false;
            }
            if (game.getPlayer2().getStates()[i] < 3) {
                p2Fin = false;
            }
        }

        if (p1Fin && game.getPlayer1().getTotalScore() > game.getPlayer2().getTotalScore()) {
            showWinner(1);
        }
        else if (p2Fin && game.getPlayer1().getTotalScore() < game.getPlayer2().getTotalScore()) {
            showWinner(2);
        }
    }

    public void showWinner(int player) {
        String winner = "";
        if (player == 1) {
            winner = p1EditText.getText().toString();
        }
        else if (player == 2) {
            winner = p2EditText.getText().toString();
        }
        else {
            return;
        }
        new AlertDialog.Builder(this)
                .setTitle("Game Over")
                .setMessage(winner + " has won the game!")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //close
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }
}
