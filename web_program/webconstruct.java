import java.io.*;
import java.util.Random;
public class webconstruct {
    Gragh gragh;
    int length;
    String webtype;
    public webconstruct(int i){
        this.gragh = new Gragh(i);
        this.length = i;

    }
    void ERWebconfig(double d) {
        if(d < 0){
            return;
        }
        webtype = "erweb";
        for(int i = 0; i < this.length;i++){
            for(int j = i + 1;j < this.length;j++){
                double probability =Math.random();
                if(probability < d){
                    gragh.addOneSide(i,j);
                }
            }
            gragh.prt();
            WriteinFile();
        }
    }
    void WSWebConfig(int k,double d ){
        if((k%2) > 0){
            System.out.println("input wrong");
        }
        else {
            webtype = "wsweb";
            for(int i = 0;i < this.gragh.length;i++){
                for (int j = 1;j <= (k/2);j++) {
                    int plus = i + j;
                    int minus = i -j;
                    if (plus > length-1){
                        plus = plus-length;
                    }
                    if (minus < 0){
                        minus = minus + length;
                    }
                    gragh.addOneSide(i,plus);
                    gragh.addOneSide(i,minus);
                }
            }
            for(int i = 0;i<gragh.length;i++){
                for(int j = 0;j <=(k/2);j++){
                    int target = i + j;
                    if (target > length - 1){
                        target = target - length;
                    }
                    Random rand = new Random();
                    int randomNumber = rand.nextInt(gragh.length);
                    while (true){
                        if(randomNumber != i ){
                            break;
                        }
                        randomNumber = rand.nextInt(gragh.length);
                    }
                    double propability = Math.random();
                    if(propability < d){
                        gragh.addOneSide(i,randomNumber);
                    }
                }
            }

        }


    }
    void BAWebConfig(int inintialNote,int addrabelNote,int m){
        webtype = "baweb";
        int noteNumber = inintialNote;
        int totalDegree = 0;
        int DegreeNow = 0;
        Random rand = new Random();
        int randnumber;
        if (addrabelNote <= 0 || m <=0 || m > gragh.length || (inintialNote + addrabelNote) != gragh.length){
            return;
        }
        for(int i = 0;i < inintialNote -1;i++ ){
            gragh.addOneSide(i,i+1);
        }
        gragh.addOneSide(0,inintialNote-1);
        for(int i = 1; i <= addrabelNote;i++){
            for(int id = 0;id < noteNumber;id ++){
                totalDegree =totalDegree + gragh.collection.get(id).getDegree();
            }
            for(int j = 0;j < m ;j++){
                randnumber = rand.nextInt(totalDegree);
                for(int n = 0;n <noteNumber;n++){
                    DegreeNow = DegreeNow + gragh.collection.get(n).getDegree();
                    if(DegreeNow > randnumber ){
                        gragh.addOneSide(n,inintialNote + i - 1);
                        break;
                    }
                }
            }
            noteNumber++;
        }
    }
    public void WriteinFile() {
        String path = "G:\\hatsune_miku\\src\\haha.txt";
        String line = System.getProperty("line.separator");
        File f = new File(path);
        int output = 0;
        try {
            FileWriter out = new FileWriter(f,true);
            for (int i = 0; i < gragh.length; i++) {
                for (int j = 0; j < gragh.length; j++) {
                    output = gragh.collection.get(i).matrix[j];
                    out.write(String.valueOf(output));
                }
                out.write(line);
            }
            out.write(line);
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String Args[]) {
        long startTime=System.nanoTime();
        webconstruct test = new webconstruct(5);
        test.ERWebconfig(0.4);
//        webconstruct test2 = new webconstruct(1000);
//        test2.WSWebConfig(4,0.1);
//        webconstruct test3 = new webconstruct( 10000);
//        test3.BAWebConfig(10,9990,10);
        long endTime=System.nanoTime();
        test.gragh.prt();
        System.out.println("the time is  "+(endTime-startTime)+"ns");
    }
}
