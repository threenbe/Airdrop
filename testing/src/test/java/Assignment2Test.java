import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class Assignment2Test {

    static int[][] map;
    static int row;
    static int column;

    static ArrayList<Airdrop> expected = new ArrayList<>();

    @Before
    public void setUp(){
        //fileToMap("map.txt");
        //fileToMap("map2.txt");
        //fileToMap("map3.txt");
        fileToMap("map4.txt");

        //expected.add(new Airdrop(0,1,6));
        //expected.add(new Airdrop(0,3,7));
        //expected.add(new Airdrop(3,0,5));
        //expected.add(new Airdrop(3,4,1));

        //expected.add(new Airdrop(0,0,5000));

        //expected.add(new Airdrop(0, 1, 33));

        expected.add(new Airdrop(0, 1, 21));
        expected.add(new Airdrop(0, 6, 11));
    }


    @Test
    public void testProgram(){
        ArrayList<Airdrop> result = Assignment2.findProgram(map,row,column);

        assertArrayEquals(expected.toArray(), result.toArray());
    }


    private void fileToMap(String fileName) {
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String[] sizes = br.readLine().split(" ");
            row = Integer.parseInt(sizes[0]);
            column = Integer.parseInt(sizes[1]);

            map = new int[row][column];
            for(int i = 0; i < row; i++) {
                sizes = br.readLine().split(" ");

                for(int j = 0; j < column; j++) {
                    map[i][j] = Integer.parseInt(sizes[j]);
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
