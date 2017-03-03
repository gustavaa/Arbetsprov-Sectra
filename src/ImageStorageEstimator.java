import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by gustavaaro on 2017-03-03.
 */
public class ImageStorageEstimator {

    private ArrayList<Image> images;



    public static void main(String[] args){
        getInput();


    }

    public static void getInput(){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            String line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }




}
