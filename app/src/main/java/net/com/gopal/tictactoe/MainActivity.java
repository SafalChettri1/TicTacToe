package net.com.gopal.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import net.com.gopal.tictactoe.R.id;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn = true;

    private int roundCount;

    private int player1Points;

    private int player2Points;

    private TextView player1;

    private TextView player2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button reset = findViewById(R.id.reset);
        Button clear = findViewById(R.id.clear);
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearbtn();
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetBoard();
            }
        });
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        if (!(button.getText().toString().equals(""))) {
            return;
        }
        if (player1Turn) {
            button.setText("X");
        } else {
            button.setText("O");
        }
        roundCount++;
        if (checkForWin()) {
            if (player1Turn) {
                player1wins();
            } else {
                player2wins();
            }
        } else if (roundCount == 9) {
            draw();
        } else {
            player1Turn = !player1Turn;
        }
    }


    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                field[i][j] = buttons[i][j].getText().toString();
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1]) && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i]) && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }
        if (field[0][0].equals(field[1][1]) && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;
        }
        if (field[0][2].equals(field[1][1]) && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }
        return false;
    }

    private void player1wins() {
        player1Points++;
        Toast.makeText(this, "Player 1 Wins", Toast.LENGTH_SHORT).show();
        updatePointText();
        resetBoard();
    }

    private void player2wins() {
        player2Points++;
        Toast.makeText(this, "Player 2 Wins", Toast.LENGTH_SHORT).show();
        updatePointText();
        resetBoard();
    }

    private void draw() {
        Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointText() {
        player1.setText("Player1: " + player1Points);
        player2.setText("Player2: " + player2Points);
    }

    private void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        roundCount = 0;
        player1Turn = true;

    }

 private void clearbtn(){
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            buttons[i][j].setText("");
        }
    }
    roundCount = 0;
  player1Points = 0;
  player2Points = 0;
  updatePointText();

}
}
