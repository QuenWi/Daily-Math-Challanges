package com.example.q_dailymath;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private Button[] bMode = new Button[4];
    private Button[] bNumberpad = new Button[12];
    private Button menue;
    private TextView eingabe;
    private TextView question;
    private TextView results;
    private int difficulty = 0;
    private boolean reversed = true;
    private int questionsLeft = 0;
    private int[] questionAnswers = new int[10];
    private int[] questionEingabe = new int[10];
    private int[] questionNumber1 = new int[10];
    private int[] questionNumber2 = new int[10];

    public void mode1(){
        bMode[0] = (Button) findViewById(R.id.mode1);
        bMode[1] = (Button) findViewById(R.id.mode2);
        bMode[2] = (Button) findViewById(R.id.mode3);
        bMode[3] = (Button) findViewById(R.id.reverse);
        bMode[0].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                changeMode(2, 1);
            }
        });
        bMode[1].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                changeMode(2, 2);
            }
        });
        bMode[2].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                changeMode(2, 3);
            }
        });
        bMode[3].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                reversed = !reversed;
                if(reversed){
                    bMode[3].setText("◄");
                } else{
                    bMode[3].setText("►");
                }
            }
        });
        if(reversed){
            bMode[3].setText("◄");
        } else{
            bMode[3].setText("►");
        }
    }

    public void mode2(){
        bNumberpad[0] = (Button) findViewById(R.id.enter0);
        bNumberpad[1] = (Button) findViewById(R.id.enter1);
        bNumberpad[2] = (Button) findViewById(R.id.enter2);
        bNumberpad[3] = (Button) findViewById(R.id.enter3);
        bNumberpad[4] = (Button) findViewById(R.id.enter4);
        bNumberpad[5] = (Button) findViewById(R.id.enter5);
        bNumberpad[6] = (Button) findViewById(R.id.enter6);
        bNumberpad[7] = (Button) findViewById(R.id.enter7);
        bNumberpad[8] = (Button) findViewById(R.id.enter8);
        bNumberpad[9] = (Button) findViewById(R.id.enter9);
        bNumberpad[10] = (Button) findViewById(R.id.enterBack);
        bNumberpad[11] = (Button) findViewById(R.id.enterResult);

        bNumberpad[0].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                addNumber(0);
            }
        });
        bNumberpad[1].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                addNumber(1);
            }
        });
        bNumberpad[2].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                addNumber(2);
            }
        });
        bNumberpad[3].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                addNumber(3);
            }
        });
        bNumberpad[4].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                addNumber(4);
            }
        });
        bNumberpad[5].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                addNumber(5);
            }
        });
        bNumberpad[6].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                addNumber(6);
            }
        });
        bNumberpad[7].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                addNumber(7);
            }
        });
        bNumberpad[8].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                addNumber(8);
            }
        });
        bNumberpad[9].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                addNumber(9);
            }
        });
        bNumberpad[10].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                removeNumber();
            }
        });
        bNumberpad[11].setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){ enterNumber(); }
        });
        nextQuestion();
    }
    public void mode3(){
        menue = (Button) findViewById(R.id.menue);
        results = (TextView) findViewById(R.id.resultsText);
        String s = "Number1 * Number2 = Result | Your Guess\n";
        menue.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){ changeMode(1, 0); }
        });
        for(int i = 0; i < 10; i++){
            s = s + Integer.toString(questionNumber1[i]) + " * " + Integer.toString(questionNumber2[i]) + " = "
                    + Integer.toString(questionAnswers[i]) + " | " + Integer.toString(questionEingabe[i]) + "\n";
        }
        results.setText(s);

    }

    public void addNumber(int number){
        String nr = Integer.toString(number);
        eingabe = (TextView) findViewById(R.id.eingabe);
        if(!reversed){
            String s = nr + eingabe.getText().toString();
            eingabe.setText(s);
        } else {
            String s = eingabe.getText().toString() + nr;
            eingabe.setText(s);
        }
    }
    public void removeNumber(){
        eingabe = (TextView) findViewById(R.id.eingabe);
        String s = eingabe.getText().toString();
        if(s.length() > 0){
            if(!reversed){
                s = s.substring(1, s.length());
                eingabe.setText(s);
            } else{
                s = s.substring(0, s.length() - 1);
                eingabe.setText(s);
            }
        }
    }
    public void enterNumber(){
        eingabe = (TextView) findViewById(R.id.eingabe);
        String s = eingabe.getText().toString();
        if(s.length() > 0){
            questionEingabe[9 - questionsLeft] = Integer.parseInt(s);
            questionsLeft--;
            eingabe.setText("");
            nextQuestion();
        }
    }
    public void nextQuestion(){
        if(questionsLeft >= 0){
            question = findViewById(R.id.question);
            String s = Integer.toString(questionNumber1[9 - questionsLeft]) + " * " + Integer.toString(questionNumber2[9 - questionsLeft]);
            question.setText(s);
        } else{
            changeMode(3, 0);
        }
    }
    public void prepere(){
        questionsLeft = 9;
        Random rn = new Random();
        for(int i = 0; i < 10; i++){
            if(difficulty == 1) {
                questionNumber1[i] = rn.nextInt(9) + 1;
                questionNumber2[i] = rn.nextInt(9) + 1;
            } else if(difficulty == 2){
                questionNumber1[i] = rn.nextInt(90) + 10;
                questionNumber2[i] = rn.nextInt(90) + 10;
            } else if(difficulty == 3){
                questionNumber1[i] = rn.nextInt(900) + 100;
                questionNumber2[i] = rn.nextInt(900) + 100;
            }
            questionAnswers[i] = questionNumber1[i] * questionNumber2[i];
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading);
        changeMode(1, 0);
    }

    public void changeMode(int nr, int difficulty2){
        if(nr == 1){
            setContentView(R.layout.quiz);
            mode1();
        }
        if(nr == 2){
            difficulty = difficulty2;
            prepere();
            setContentView(R.layout.activity_main);
            mode2();
        }
        if(nr == 3){
            setContentView(R.layout.results);
            mode3();
        }
    }
}