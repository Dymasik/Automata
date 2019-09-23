package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Scanner;

public class AutomataHelper {

    private Scanner _sc;

    private boolean TryGetFile() {
        System.out.println("Enter path to automata definition file:\n");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            String automataFile = in.readLine();
            _sc = new Scanner(new File(automataFile));
            return true;
        } catch (Exception ex) {
            System.out.println("Next error has occurred: " + ex.toString());
            return false;
        }
    }

    protected void CreateAutomataFromFile() {

    }

    public void CreateAutomata() {
        if (TryGetFile()) {
            Automata automata = new Automata(_sc);
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                System.out.println("Enter a word: ");
                try {
                    System.out.println(automata.GetResultFromProcessing(in.readLine()) ?
                            "Good!" : "Bad!");
                } catch (Exception ex) {
                    System.out.println("Incorrect word!");
                    return;
                }
            }
        } else {
            System.out.println("There is no file!");
        }
    }

}

