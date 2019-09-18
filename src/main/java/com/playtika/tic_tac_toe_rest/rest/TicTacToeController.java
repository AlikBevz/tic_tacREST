package com.playtika.tic_tac_toe_rest.rest;

import com.playtika.tic_tac_toe_rest.service.TicTacToeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "tictactoe/")
@RestController
public class TicTacToeController {

    @Autowired
    TicTacToeService ticTacToeService;

    private int count = 0;

    @GetMapping(value = "move")
    public String[] getMove(@RequestParam(value = "row") int x, @RequestParam(value = "column") int y ){

        String[] stringOutUser = new String[1];
        String[] stringOutComp = new String[1];
        stringOutUser[0] = "You are win";
        stringOutComp[0] = "Comp is win";


        if (count==0) {

        ticTacToeService.init();

                    }

        int result = ticTacToeService.step( x, y);

        if (result == 1) return stringOutUser;
        if (result == 2) return stringOutComp;

        count++;

        return ticTacToeService.toOneDimension(ticTacToeService.getField()) ;
    }


}
