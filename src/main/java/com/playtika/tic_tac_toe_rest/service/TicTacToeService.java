package com.playtika.tic_tac_toe_rest.service;

import com.playtika.tic_tac_toe_rest.game.Game;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Getter
@Setter
public class TicTacToeService  {


    @Autowired
    Game game;



    public void init(){

        game.initField();

    }

    public void print(){

        game.printField();

    }

    public String[][] getField(){

        return game.getField();
    }

    public String[] toOneDimension(String[][] field){
        return game.toOneDimension(field);
    }


    public int step(int x, int y){

       game.movePerson(x, y);
       game.printField();
       if (game.checkWin("X")) {
           System.out.println("You are win");
           return 1;
       }


       game.moveAI();
       System.out.println();
       game.printField();
       if (game.checkWin("0")) {
           System.out.println("Comp is win");
           return 2;
       }



      return 0;

   }





}
