package com.example.connect3game;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    int activeplayer = 0;
    int[] state = {2,2,2,2,2,2,2,2,2};
    int[][] winingposition = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    boolean gameActive = true;
    public void Press(View view){
       ImageView counter = (ImageView) view;
       int tagcounter =Integer.parseInt(counter.getTag().toString());
       if(state[tagcounter]==2 && gameActive==true){
           state[tagcounter] = activeplayer;
           counter.setTranslationY(-1000);
           if (activeplayer == 0) {
               counter.setImageResource(R.drawable.yellowball);
               activeplayer = 1;
           } else {
               counter.setImageResource(R.drawable.redball);
               activeplayer = 0;
           }
           counter.animate().translationYBy(1000).rotation(3600).setDuration(300);
           for (int[] position : winingposition) {
               if (state[position[0]] == state[position[1]] && state[position[1]] == state[position[2]] && state[position[0]] != 2) {
                   gameActive = false;
                   int player = state[position[0]];
                   String msg = "";
                   if (player == 1) {
                       msg = "Red";
                   } else {
                      msg = "Yellow";
                   }
                   Toast.makeText(this, msg + " has won", Toast.LENGTH_SHORT).show();
                   Button button = findViewById(R.id.playAgainButton);
                   button.setVisibility(view.VISIBLE);
                   TextView text = findViewById(R.id.textView);
                   text.setText("Congratulaions!! " + msg +" You won");
               }
           }
           boolean reset = true;
           for(int i=0;i<9;i++){
               if(state[i]==2) reset = false;
           }
           if(reset==true){
               Button button = findViewById(R.id.playAgainButton);
               button.setVisibility(view.VISIBLE);
           }
       }
  }
  public void play(View view){
        Button button = findViewById(R.id.playAgainButton);
        TextView text = findViewById(R.id.textView);
        text.setText("");
        button.setVisibility(view.INVISIBLE);
        activeplayer = 0;
        GridLayout grid = findViewById(R.id.grid);
        for(int i=0;i<grid.getChildCount();i++){
            ImageView child = (ImageView) grid.getChildAt(i);
            child.setImageDrawable(null);
        }
      for(int i=0;i<state.length;i++)
          state[i]=2;
       gameActive = true;
  }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}