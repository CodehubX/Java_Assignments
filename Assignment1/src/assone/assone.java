package assone;

/**
 * Created by jm on 9/12/2014.
 */
public class assone {
    public static void main(String[] args) {
        Thread one = new Thread(new ThreadAone(), "first Thread");
        System.out.println("We will begin with the first thread");
        one.start();
    }
}
