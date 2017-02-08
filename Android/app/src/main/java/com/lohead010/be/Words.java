package com.lohead010.be;


public class Words {
    private int ID;
    private String word;
    private String Definition;
    private String POS;
    private String pronounce;


    public Words(){}

    public Words(int ID, String word, String Definition)
    {
        super();
        this.ID = ID;
        this.word = word;
        this.Definition = Definition;

    }

    public int getID() {return ID;}

    public void setID(int newID) {ID = newID;}

    public String getWord() {return word;}

    public void setWord(String newWord) {word = newWord;}

    public String getDefinition() {return  Definition;}

    public void setDefinition(String newDef) {Definition=newDef;}

    @Override
    public String toString() {return "Verb: " + word + " Definition:" + Definition;}



}
