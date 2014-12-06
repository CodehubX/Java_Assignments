package ana2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;


public class ByzGenThree {
    public static void main(String args[]) throws Exception {
        int icounter;
        InetAddress lclhost;
        GenThree gentwo, genthree, genfour;

        for (icounter = 0; icounter < 2; icounter++) {
            lclhost = InetAddress.getLocalHost();
            gentwo = new GenThree(lclhost);
            gentwo.setSendPort(9004);
            gentwo.setRecPort(8003);

            gentwo.recData();
            gentwo.sendData();

            lclhost = InetAddress.getLocalHost();
            genthree = new GenThree(lclhost);
            genthree.setSendPort(9008);
            genthree.setRecPort(8007);

            genthree.recData();
            genthree.sendData();

            lclhost = InetAddress.getLocalHost();
            genfour = new GenThree(lclhost);
            genfour.setSendPort(9011);
            genfour.setRecPort(8012);

            genfour.sendData();
            genfour.recData();

            GenThree.vecdata = true;
        }
    }
}


class GenThree

{
    static boolean vecdata = false;
    private static String vecstr = "", datastr = "";
    private static int recctr = 0;
    private static int ctr = 0;
    private static int fnctr = 0;
    private static int recdata = 0;
    private static int dataarr[] = new int[12];
    private static int finalarr[] = new int[3];
    InetAddress lclhost;
    int sendport, recport;

    GenThree(InetAddress lclhost) {
        this.lclhost = lclhost;
    }

    void setSendPort(int sendport) {
        this.sendport = sendport;
    }

    void setRecPort(int recport) {
        this.recport = recport;
    }

    void sendData() throws Exception {
        DatagramSocket ds;
        DatagramPacket dp;
        BufferedReader br;
        ds = new DatagramSocket(sendport);
        System.out.println("Enter the Data");

        if (vecdata == false) {
            br = new BufferedReader(new InputStreamReader(System.in));
            datastr = br.readLine();
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
            datastr = br.readLine();
            datastr = vecstr;
        }

        dp = new DatagramPacket(datastr.getBytes(), datastr.length(), lclhost, sendport - 1000);
        ds.send(dp);
        ds.close();
    }

    void recData() throws Exception {

        DatagramSocket ds;
        DatagramPacket dp;
        byte[] buf = new byte[256];
        String msgstr;
        ds = new DatagramSocket(recport);
        dp = new DatagramPacket(buf, buf.length);
        ds.receive(dp);
        ds.close();
        msgstr = new String(dp.getData(), 0, dp.getLength());
        System.out.println(msgstr);


        if (msgstr.length() == 9) {
            recctr++;

            recdata = Integer.parseInt(msgstr);

            dataarr[ctr++] = recdata / 1000000;

            recdata = recdata % 1000000;

            dataarr[ctr++] = recdata / 1000;

            recdata = recdata % 1000;

            dataarr[ctr++] = recdata;

            if (recctr == 3) {
                maxval();
            }
        }

        if (vecdata == false) {
            vecstr = vecstr.concat(msgstr);
            System.out.println("Vector Data " + vecstr);
        }
    }


    void maxval() {
        int ctr1, ctr2, i, j;
        boolean gentwo = false, genthree = false, genfour = false;
        for (ctr1 = 0; ctr1 < 9; ctr1++) {
            i = 0;
            j = dataarr[ctr1];

            if (dataarr[ctr1] != 0) {
                for (ctr2 = 0; ctr2 < 9; ctr2++) {
                    if (j == dataarr[ctr2]) {
                        i++;
                        dataarr[ctr2] = 0;
                    }
                }
                if (i == 2) {
                    finalarr[fnctr++] = j;
                }
            }
        }

        Arrays.sort(finalarr);
        System.out.println("Final Vector");

        for (ctr1 = 0; ctr1 < 3; ctr1++) {
            System.out.println(finalarr[ctr1] + " ");
        }

        for (ctr1 = 0; ctr1 < 3; ctr1++) {

            if (finalarr[ctr1] >= 100 && finalarr[ctr1] < 200) {
                System.out.println("General Number one is Loyal");
                gentwo = true;
            } else if (finalarr[ctr1] >= 200 && finalarr[ctr1] < 300) {
                System.out.println("General Number two is Loyal");
                genthree = true;
            } else if (finalarr[ctr1] >= 400 && finalarr[ctr1] < 500) {
                System.out.println("General Number four is Loyal");
                genfour = true;
            }
        }

        if (gentwo == false) {
            System.out.println("General Number One is Not Loyal");
        }

        if (genthree == false) {
            System.out.println("General Number Two is Not Loyal");
        }

        if (genfour == false) {
            System.out.println("General Number Four is Not Loyal");
        }

    }

} 


