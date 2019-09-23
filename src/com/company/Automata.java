package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Automata {

    private class Verticle {
        private int _to;
        private char _edgeValue;

        public Verticle(int to, char edgeValue) {
            _to = to;
            _edgeValue = edgeValue;
        }

        public int getTo() {
            return _to;
        }

        public char getEdgeValue() {
            return _edgeValue;
        }
    }

    private boolean[] _isTerminate;
    private ArrayList<Verticle>[] _edges;
    private int _wordLength;

    public Automata(Scanner source) {
        _wordLength = source.nextInt();
        int verticleCount = source.nextInt();
        _isTerminate = new boolean[verticleCount];
        _edges = new ArrayList[verticleCount];
        for (int i = 0; i < verticleCount; i++)
            _edges[i] = new ArrayList<Verticle>();

        int edgeCount = source.nextInt();
        while (edgeCount-- > 0){
            int from = source.nextInt();
            int to = source.nextInt();
            char edge = source.next().charAt(0);
            Verticle verticle = new Verticle(to, edge);
            _edges[from].add(verticle);
        }
        int terminate = source.nextInt();
        while (terminate-- > 0){
            int state= source.nextInt();
            _isTerminate[state] = true;
        }
    }

    private boolean dfs(int v, String currentWord, String word, int parent) {
        if (currentWord.compareTo(word) == 0) {
            if (word.length() == _wordLength) {
                System.out.println("We have match, state of verticle is " + _isTerminate[v]);
                return _isTerminate[v];
            } else {
                System.out.println("We don't have match");
                return false;
            }
        }
        if (word.length() > _wordLength) {
            return false;
        }
        boolean ans = false;
        for (Verticle x : _edges[v]){
            if (x.getEdgeValue() == word.charAt(currentWord.length())) ans |= dfs(x.getTo(),
                    currentWord+x.getEdgeValue(), word, v);
        }
        System.out.println("Solution from this state is " + (ans==false ? "not" : "") + " found... Performing rollback to state "+parent);
        return ans;
    }

    public boolean GetResultFromProcessing(String word) {
        return dfs(0, "", word, -1);
    }
}
