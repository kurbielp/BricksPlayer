/**
 * Created by dell on 2017-01-05.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;



public  class MyPlayer {



    public static void checkIfCaptured(int x1, int y1, int x2, int y2, int[] a, int[] b, int[][] endangered, int numberOfFreeSquares) {
        int currentX;
        int  currentY;
        boolean isSurrounded = true;
        int[] brickx = new int[2];
        int[] bricky = new int[2];
        brickx[0]=x1;
        brickx[1]=x2;
        bricky[0]=y1;
        bricky[1]=y2;
        for (int k=0;k<2;k++) {
            for (int i = 0; i < 8; i++) {
                if( brickx[k] + a[i]< 0||  brickx[k] + a[i] > size ||  brickx[k] + a[i] < 0||  brickx[k] + a[i] > size )
                    continue;
                currentX = brickx[k] + a[i];
                currentY =  brickx[k] + a[i];

                for (int j = 0; j < 8; j+=2) {
                    if( currentX + a[i]< 0 ||  currentX + a[i] > size ||  currentY + a[i] < 0 ||  currentY + a[i] > size )
                        continue;
                    if (board[currentX + a[j]][currentY + b[j]] == 0) {
                        isSurrounded = false;
                    }
                }
                if (isSurrounded) {
                    board[currentX][currentY] = 1;
                    endangered[currentX][currentY] =0;
                    numberOfFreeSquares--;

                }
                isSurrounded = true;
            }
        }

    }

    public static void checkIfEndangered(int x1, int y1, int x2, int y2, int[] a, int[] b, int[][] endangered) {
        int currentX;
        int  currentY;
        int numberOfNeighbours = 0;
        int[] brickx = new int[2];
        int[] bricky = new int[2];
        brickx[0]=x1;
        brickx[1]=x2;
        bricky[0]=y1;
        bricky[1]=y2;
        for (int k=0;k<2;k++) {
            for (int i = 0; i < 8; i++) {
                if( brickx[k] + a[i]< 0 ||  brickx[k] + a[i] > size ||  brickx[k] + a[i] < 0 ||  brickx[k] + a[i] > size )
                    continue;
                currentX = brickx[k] + a[i];
                currentY = bricky[k] + b[i];

                for (int j = 0; j < 8; j+=2) {
                    if( currentX + a[i]< 0 ||  currentX + a[i] > size ||  currentY + a[i] < 0 ||  currentY + a[i] > size )
                        continue;
                    if (board[currentX + a[j]][currentY + b[j]] == 0) {
                        numberOfNeighbours  += 1;
                    }
                }
                if (numberOfNeighbours == 1) {

                    endangered[currentX][currentY] =1;
                }
                numberOfNeighbours=0;
            }
        }

    }
    public static void defensywa(int[] a ,int[] b ,int[][] endangered ){
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(endangered[i][j]==0) {
                    for (int k = 0; k < 8; k+=2) {
                        if( i + a[k]< 0 ||  i + a[k]> size ||  j + b[k] < 0 ||  j + b[k]  > size )
                            continue;
                        if (endangered[i + a[k]][j + b[k]] == 0) {
                            i += 1;
                            j += 1;
                            int u = i + a[k];
                            int v = j + b[k];
                            System.out.println(i + " " + j + " " + u + " " + v);
                            board[i][j] = 1;
                            board[i + a[k]][i + a[k]] = 1;
                            endangered[i][j] = 0;
                            endangered[i + a[k]][i + a[k]] = 0;
                            break;
                        }
                    }
                }
            }
        }
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(board[i][j]==0) {
                    for (int k = 0; k < 8; k+=2) {
                        if( i + a[k]< 0 ||  i + a[k]> size ||  j + b[k] < 0 ||  j + b[k]  > size )
                            continue;
                        if (board[i + a[k]][j + b[k]] == 0) {
                            i+=1;
                            j+=1;
                            int u=i+a[k];
                            int v=j+b[k];
                            System.out.println(i + " " + j+ " " + u + " " +v);
                            board[i][j] = 1;
                            board[i + a[k]][i + a[k]] = 1;
                            endangered[i][j] = 0;
                            endangered[i + a[k]][i + a[k]] = 0;
                            break;
                        }
                    }
                }
            }
        }
    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!TO SPRAWDŹ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    public static void ofensywa(int[] a ,int[] b /*,int[][] endangered*/ ) {
        //a i b są z maina (zaznaczone !!!)
        outerloop3:
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (/*endangered[i][j] == 1 && */board[i][j] == 0) {
                    for (int k = 0; k < 8; k+=2) {
                        if( i + a[k]< 0 ||  i + a[k]> size-1 ||  j + b[k] < 0 ||  j + b[k]  > size-1 ) {

                            continue;
                        }
                        if (board[i + a[k]][j + b[k]] == 0) {

                            board[i][j] = 1;
                            board[i + a[k]][i + a[k]] = 1;
                           // endangered[i][j] = 0;
                           // endangered[i + a[k]][i + a[k]] = 0;

                            int x1= i+1;
                            int y1 = j+1;
                            int x2=i+a[k]+1;
                            int y2=j+b[k]+1;

                            //System.out.println((i+1) + " " + (j+1)+ " " + (i+a[k]+1) + " " +(j+b[k]+1));
                            System.out.println(x1 + " " + y1+ " " + x2 + " " +y2 );


                            //checkIfCaptured(i, j, i + a[k], j + b[k], a, b, endangered);
                            //checkIfEndangered(i, j, i + a[k], j + b[k], a, b, endangered);
                            break outerloop3;
                        }
                    }
                }
            }

        }
 //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!KONIEC!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
/*
        outerloop4:
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                if(board[i][j]==0) {
                    for (int k = 0; k < 8; k+=2) {
                        if( i + a[k]< 0 ||  i + a[k]> size-1 ||  j + b[k] < 0 ||  j + b[k]  > size-1 )
                            continue;
                        if (board[i + a[k]][j + b[k]] == 0) {
                            i+=1;
                            j+=1;
                            int u=i+a[k];
                            int v=j+b[k];
                            System.out.println(i + " " + j+ " " + u + " " +v);
                            board[i][j] = 1;
                            board[i + a[k]][i + a[k]] = 1;
                            endangered[i][j] = 0;
                            endangered[i + a[k]][i + a[k]] = 0;
                            //checkIfCaptured(i, j, i + a[k], j + b[k], a, b, endangered);
                            //checkIfEndangered(i, j, i + a[k], j + b[k], a, b, endangered);
                            break outerloop4;
                        }
                    }
                }
            }
        }
        */


    }
    
    public static void main(String args[]) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            br.readLine();
            System.out.println("PONG");
            size = Integer.parseInt(br.readLine());
            board = new int[size][size];
            int[][] endangered = new int[size][size];// komorki majace tylko jedna sasiednie wolne pole
            int numberOfFreeSquares = size * size;

            //!!!!!!!!!!!!!TO TE A I B DO TEGO CO MOŻesz SPRAWDZIC !!!!!!!!!
            int[] a = new int[8]; // skladowe x
            int[] b = new int[8]; // skladowe y
            //paramrtry  ktore beda dodawane do danego pola by osiagnac parametry sasiednich pol
            a[0] = 1;
            b[0] = 0;

            a[1] = 1;
            b[1] = 1;

            a[2] = 0;
            b[2] = 1;

            a[3] = -1;
            b[3] = 1;

            a[4] = -1;
            b[4] = 0;

            a[5] = -1;
            b[5] = -1;

            a[6] = 0;
            b[6] = -1;

            a[7] = 1;
            b[7] = -1;

            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    board[j][i] = 0;
                    endangered[j][i] = 0;
                }
            }
            while (true) {
                String inputText = br.readLine();
                String zaczynaj = "ZACZYNAJ";
                if (!inputText.equals(zaczynaj)) {
                    int previousx1 = Integer.parseInt(inputText.split(" ")[0]) - 1;
                    int previousy1 = Integer.parseInt(inputText.split(" ")[1]) - 1;
                    int previousx2 = Integer.parseInt(inputText.split(" ")[2]) - 1;
                    int previousy2 = Integer.parseInt(inputText.split(" ")[3]) - 1;
                    board[previousx1][previousy1] = 1;
                    board[previousx2][previousy2] = 1;
                    endangered[previousx1][previousy1] = 0;
                    endangered[previousx2][previousy2] = 0;
                    numberOfFreeSquares -=2;
                  // checkIfCaptured(previousx1,previousy1 ,previousx2, previousy2, a, b, endangered); // sprawdza sasiednie pola wokol klocka sa pojedynczymi wolnymi otoczone naaokoło
                   // checkIfEndangered(previousx1,previousy1 ,previousx2, previousy2, a, b, endangered); //sprawdza sasiednie pola w okol klocka sasiaduja tylko z 1 polem wolnym polem
                }


                //System.out.println(1 + " " + 1 + " " + 1 + " " + 2);


                if ( true       /*numberOfFreeSquares % 2 ==1 */) {
                    ofensywa(a,b,endangered);//eliminacja zagrozonych pol jesli to mozliwe (troche bez sensu zorbione bo powinno byc ze stawia obok zagrozonego by go otoczyc
                    numberOfFreeSquares-=2;


                } else {
                  // defensywa(a,b,endangered);// proboje stawic klocek w niezagrozonych polach (chyba)
                    numberOfFreeSquares-=2;
                }






            }

       } catch (Exception e) {

        }
    }

    private static boolean[] possibleDirections(int x, int y) {
        boolean result[] = new boolean[4];
        result[0] = false;
        result[1] = false;
        result[2] = false;
        result[3] = false;
        try {
            if (board[x][y - 1] == 0) result[0] = true;
        } catch (ArrayIndexOutOfBoundsException e) {
            result[0] = false;
        }

        try {
            if (board[x + 1][y] == 0) result[1] = true;
        } catch (ArrayIndexOutOfBoundsException e) {
            result[1] = false;
        }

        try {
            if (board[x][y + 1] == 0) result[2] = true;
        } catch (ArrayIndexOutOfBoundsException e) {
            result[2] = false;
        }

        try {
            if (board[x - 1][y] == 0) result[3] = true;
        } catch (ArrayIndexOutOfBoundsException e) {
            result[3] = false;
        }

        return result;
    }

    private static int[][] board;
    private static int size;

}


//RZECZY
/*
    public  static  void initSurrondings() {
        Square[] surroundings = new Square[];
        surroundings[0].x = 1;
        surroundings[0].y = 0;

        surroundings[1].x = 1;
        surroundings[1].y = 1;

        surroundings[2].x = 0;
        surroundings[2].y = 1;

        surroundings[3].x = -1;
        surroundings[3].y = 1;

        surroundings[4].x = 0;
        surroundings[4].y = 0;

        surroundings[5].x = -1;
        surroundings[5].y = -1;

        surroundings[6].x = 0;
        surroundings[6].y = -1;

        surroundings[7].x = 1;
        surroundings[7].y = -1;

    */
    /*
           ArrayList<Square> surroundingsList = new ArrayList<Square>();
          Square surroundings = new Square();
           surroundings.x = 1;
           surroundings.y = 0;
           surroundingsList.add( 0,surroundings);
           surroundings.x = 1;
           surroundings.y = 1;
           surroundingsList.add(1, surroundings);

           surroundings.x = 0;
           surroundings.y = 1;
           surroundingsList.add( 2,surroundings);

           surroundings.x = -1;
           surroundings.y = 1;
           surroundingsList.add(3, surroundings);

           surroundings.x = 0;
           surroundings.y = 0;
           surroundingsList.add(4, surroundings);

           surroundings.x = -1;
           surroundings.y = -1;
           surroundingsList.add(5, surroundings);

           surroundings.x = 0;
           surroundings.y = -1;
           surroundingsList.add(6,surroundings);

           surroundings.x = 1;
           surroundings.y = -1;
           surroundingsList.add(7, surroundings);

           Square square1 = new Square();
           Square square2 = new Square();
           square1.x=x1;
           square2.x=x2;
           square1.y=y1;
           square2.y=y2;
           Square currentSquare = new Square();
           */