package br.com.cyborg;

public class Main {

    public String olaMundo(){
        return "ola mundo";
    }


    public static void main(String[] args) {
        System.out.println((new Main()).olaMundo());
    }

}
