import java.util.Random;

public class Forest {
    volatile int[][] location;

    Forest(){
        Random random = new Random();
        int x = random.nextInt(10);
        int y = random.nextInt(10);
        this.location = new int[10][10];

        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                if (i == x && j == y) location[i][j] = 1;
                else location[i][j] = 0;
            }
        }

        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                System.out.print(this.location[i][j]);
                System.out.print(" ");
            }
            System.out.print("\n");
        }

        System.out.println("Winnie the Pooh on the site " + String.valueOf(x) + ":" + String.valueOf(y));
    }
}
