//package akari;
//
//public class Board {
//    private String difficultyLevel; //Możliwe poziomy: easy. medium. hard, other(czyli inny bok niż 7,10,14)
//    //W poleceniu było że musimy mieć dostęone rożne poziomy trudności. Można wykorzystać obecny generator i w zależności od poziomu trudności
//    //manipulować wyłącznie rozmiarem planszy lub też zmodyfikować generator i dodać możliwość manipulacji ilością różnych elementóœ na planszy.
//
//    //KONSTRUKTORY
//
//    //plansza łatwa- domyślnie
//    public Board(){
//        difficultyLevel="easy";
//        String[][] board=Generator.generate(7);
//    }
//
//    //plansza o zadanym poziomie trudności
//    public Board(String difficultyLevel){
//        this.difficultyLevel=difficultyLevel;
//        if(difficultyLevel=="easy"){
//            String[][] board =Generator.generate(7);
//        }else if(difficultyLevel=="medium"){
//            String[][] board =Generator.generate(10);
//        }else if(difficultyLevel=="hard"){
//            String[][] board =Generator.generate(14);
//        }else throw new IllegalArgumentException("difficulty level must be: easy, medium or hard");
//    }
//
//    //plansza o niestandardowym poziomie trudności (zadany bok, niekoniecznie 7,10,14)
//    public Board(int n){
//        difficultyLevel="other";
//            String[][] board=Generator.generate(n);
//    }
//
//    //GETTERY
//    public String[][] getBoard(){
//        return board[][].class;
//    }
//    public String [][] getSolvedBoard(){
//        return solvedBoard[][].class;
//    }
//    public String getDifficultyLevel(){
//        return difficultyLevel;
//    }
//}
