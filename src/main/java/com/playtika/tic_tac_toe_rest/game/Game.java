package com.playtika.tic_tac_toe_rest.game;

import com.playtika.tic_tac_toe_rest.exception.AlreadyFilledFieldException;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.lang.annotation.*;
import java.util.Random;


@Component
@Data
public class Game {


    private static final String NULL = "_";
    private int RAZMER_POLYA = 3;
    private String[][] field = new String[RAZMER_POLYA][RAZMER_POLYA];
    private int stepCounter = 0;


    public String[][] getField() {
        return field;
    }

    public void initField(){
        for (int i = 0; i < RAZMER_POLYA; i++) {
            for (int j = 0; j < RAZMER_POLYA; j++) {
                field[i][j] = NULL;
            }
        }
    }

    public String[] toOneDimension(String[][] field){
        String[] oneDimension = new String[RAZMER_POLYA];

        for (int i = 0; i < RAZMER_POLYA; i++) {
            for (int j = 0; j < RAZMER_POLYA; j++) {
                if (j==0) {oneDimension[i]=field[i][j];}
                  else oneDimension[i]= oneDimension[i] + field[i][j] ;
            }

        }

        return oneDimension;
    }


    public void printField() {

        for (int i = 0; i < RAZMER_POLYA; i++) {
            for (int j = 0; j < RAZMER_POLYA; j++) {
                System.out.print(field[i][j] + "\t");
            }
            System.out.println();
        }
    }

    

    public void checkIfFilled(String s) {

        if (!s.equals(NULL)) {
            throw new AlreadyFilledFieldException();
        }

    }
    private String selectPlayer() {
        String player;
        if (stepCounter % 2 == 0) {
            player = "X";
        } else {
            player = "0";
        }
        return player;
    }


    public void step(int x, int y) {

        try {
            checkIfFilled(field[x][y]);

            field[x][y] = selectPlayer();

            stepCounter++;
        }

        catch (AlreadyFilledFieldException e) {
            if (stepCounter % 2 == 0) { System.out.println("Person have used filling field and will pass move");}
            else { System.out.println("Comp have used filling field and will pass move");}
            stepCounter++;
        }

    }

    public void movePerson(int x, int y) {

        step(x,y);

    }

    public void  moveAI() {

       int x = new Random().nextInt(RAZMER_POLYA);
       int y = new Random().nextInt(RAZMER_POLYA);

        step(x,y);

    }

   public boolean checkWin(String sym) {

        for (int i = 0; i < RAZMER_POLYA; i++) {
            int result = 0;
            for (int j = 0; j < RAZMER_POLYA; j++) {
                if (field[i][j] == sym) result++;
            }
            if (result == RAZMER_POLYA) return true;
        }

        for (int i = 0; i < RAZMER_POLYA; i++) {
            int result = 0;
            for (int j = 0; j < RAZMER_POLYA; j++) {
                if (field[j][i] == sym) result++;
            }
            if (result == RAZMER_POLYA) return true;
        }

        int resultLeftToRight = 0;
        for (int i = 0; i < RAZMER_POLYA; i++) {
            for (int j = 0; j < RAZMER_POLYA; j++) {
                if ((i==j) && (field[i][j] == sym)) resultLeftToRight++;
            }
            if (resultLeftToRight == RAZMER_POLYA) return true;
        }

        int resultRightToLeft = 0;
        for (int i = 0, j = RAZMER_POLYA-1; i<RAZMER_POLYA && j>=0; i++, j--) {
            if (field[i][j] == sym) resultRightToLeft++;}

        if (resultRightToLeft == RAZMER_POLYA) return true;

        return false;
    }





}
