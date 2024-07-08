package com.example.tictactoe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private boolean playerX = true;
    private int[][] board = new int[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            final int row = i / 3;
            final int col = i % 3;
            final Button button = (Button) gridLayout.getChildAt(i);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (((Button) view).getText().toString().equals("")) {
                        if (playerX) {
                            ((Button) view).setText("X");
                            board[row][col] = 1;
                        } else {
                            ((Button) view).setText("O");
                            board[row][col] = 2;
                        }
                        if (checkWinner()) {
                            showWinner(playerX ? "X" : "O");
                        }
                        playerX = !playerX;
                    }
                }
            });
        }
    }

    private boolean checkWinner() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != 0)
                return true;
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != 0)
                return true;
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != 0)
            return true;
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != 0)
            return true;

        return false;
    }

    private void showWinner(String winner) {
        Toast.makeText(this, "Winner is " + winner, Toast.LENGTH_LONG).show();
        resetBoard();
    }

    private void resetBoard() {
        GridLayout gridLayout = findViewById(R.id.gridLayout);
        for (int i = 0; i < gridLayout.getChildCount(); i++) {
            Button button = (Button) gridLayout.getChildAt(i);
            button.setText("");
        }
        board = new int[3][3];
        playerX = true;
    }
}
