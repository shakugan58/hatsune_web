import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Gragh {
    ArrayList<Note> collection = new ArrayList<Note>();
    int length;
    private int[] degreeAtribute;
    private int[] closenessAtribute;
    private double[] BetweennessMatrix;
    private double[] pagerank;
    private int[] eigenvectorAtribute;
     Gragh(int number){
        this.length = number;
        for(int i = 1 ;i < number+1;i++){
            Note a = new Note(i,number);
            this.collection.add(a);
            a = null;
        }
        setDegreeAttribute();
    }
    public void prt(){
        for(int i = 0;i<this.length;i++){
            for (int j = 0;j<this.collection.get(i).matrix.length;j++){
                System.out.print(this.collection.get(i).matrix[j]);
            }
            System.out.println("");
        }
        System.out.println("");
    }
    public void deleteOneNote(int index){
         for(int j = 0; j < length;j++){
             deleteOneSide(index,j);
         }
    }
    public void deleteOneSide(int i,int j){
         this.collection.get(i).matrix[j] = 0;
         this.collection.get(j).matrix[i] = 0;
    }
    public void addOneSide(int i,int j){
         this.collection.get(i).matrix[j] = 1;
         this.collection.get(j).matrix[i] = 1;
    }


//-----------------------------------------------------------------------------------------------------------------------------
    public void setDegreeAttribute(){
        degreeAtribute = new int[length];
        for(int i = 0; i < length;i++){
            degreeAtribute[i] = this.collection.get(i).getDegree();
        }
    }
    public int[] getDegreeAttribute(){
        return degreeAtribute;
    }




//----------------------------------------------------------------------------------------------------------------------------------
    public void setBetweennessMatrix(){
         BetweennessMatrix = new double[length];
        double[] b = new double[this.length];
        for(int start = 0;start < this.length ;start++){
            int[] path = getpath(start);
            int[] num = new int[this.length];
            int[] used = new int[this.length];
            for(int i = 0; i < this.length;i++){
                for(int j = 0;j < this.length ;j++){
                    if(path[j] != -1 && used[path[j]] == 0){
                        used[path[j]] = 1;
                    }
                }
                for(int j = 0;j < this.length;j++){
                    if(used[j] == 0 && path[j] >= 0){
                        num[path[j]] += 1 + num[j];
                        used[j] = 2;
                    }
                }
                for (int j = 0; j < this.length;j++) {
                    if(used[j] == 1){
                        used[j] = 0;
                    }
                }
            }
            for (int i = 0 ;i < this.length ;i++){
                b[i] +=num[i];
            }
        }
        double sum = (this.length)*(this.length) - this.length;
        for (int i = 0;i < this.length ;i++){
            BetweennessMatrix[i] = b[i]/sum;
        }
    }
    public void setBetweennessMatrix(int i,double d){
         BetweennessMatrix[i] = d;
    }
    public double[] getBetweennessMatrix(){

         return this.BetweennessMatrix;
    }
    public int[] getpath(int start){
        int[] path =new int[this.length];
        boolean[] used  = new boolean[this.length];
        int[] minDis = new int[this.length];
        for(int i = 0;i < this.length;i++){
            path[i] = -1;
            minDis[i] = -1;
        }
        used[start] = true;
        minDis[start] = 0;
        for (int i = 0;i <this.length;i++){
            for (int j = 0;j < this.length;j++){
                if(used[j] || this.collection.get(start).matrix[j] <= 0){
                    continue;
                }
                if(this.collection.get(start).matrix[j] > 0 && (minDis[j] < 0|| minDis[j] > minDis[start] + this.collection.get(start).matrix[j])){
                    minDis[j] = minDis[start] + this.collection.get(start).matrix[j];
                    path[j] = start;
                }
            }
            start = -1;
            for (int j =  0;j <this.length;j++){
                if (minDis[j] < 0 || used[j]){
                    continue;
                }
                if (start == -1 || minDis[start] > minDis[j]){
                    start = j;
                    used[start] = true;
                }
            }
            if (start == -1){
                break;
            }
        }
        return path;
    }
    //-------------------------------------------------------------------------------------------------------
    public void setClosenessAtribute(){
         closenessAtribute = new int[length];
         for (int i = 0;i < length;i++){
             int[] minDisNote = getminDis(i);
             for(int j = 0;j < length;j++){
                 closenessAtribute[i] += minDisNote[j];
             }
         }
    }
    public int[] getClosenessAtribute(){
         return this.closenessAtribute;
    }
    public int[] getminDis(int start){
        boolean[] used  = new boolean[this.length];
        int[] minDis = new int[this.length];
        for(int i = 0;i < this.length;i++){
            minDis[i] = -1;
        }
        used[start] = true;
        minDis[start] = 0;
        for (int i = 0;i <this.length;i++){
            for (int j = 0;j < this.length;j++){
                if(used[j] || this.collection.get(start).matrix[j] <= 0){
                    continue;
                }
                if(this.collection.get(start).matrix[j] > 0 && (minDis[j] < 0|| minDis[j] > minDis[start] + this.collection.get(start).matrix[j])){
                    minDis[j] = minDis[start] + this.collection.get(start).matrix[j];
                }
            }
            start = -1;
            for (int j =  0;j <this.length;j++){
                if (minDis[j] < 0 || used[j]){
                    continue;
                }
                if (start == -1 || minDis[start] > minDis[j]){
                    start = j;
                    used[start] = true;
                }
            }
            if (start == -1){
                break;
            }
        }
        return minDis;
    }


    //----------------------------------------------------------------------------------------------------------------------
    public void setEigenvectorAtribute(){
         eigenvectorAtribute = new int[length];
         for(int i = 0;i < length ;i++){
            for(int j = 0;j < length ;j++){
                if(collection.get(i).matrix[j] > 0){
                    eigenvectorAtribute[i]+=collection.get(j).getDegree();
                }
            }
         }
    }
    public int[] getEigenvectorAtribute(){
         return eigenvectorAtribute;
    }


    //---------------------------------------------------------------------------------------------------------------------------------
    public void setPagerank(){
         pagerank  = new double[length];
         double d  = 0.85;
         double[][] jumpMatrix = new double[length][length];
         for (int i = 0;i < length;i++){
             pagerank[i] = 1;
         }
         for (int i = 0;i < length;i++){
             int degree = collection.get(i).getDegree();
             if(degree == 0){
                 continue;
             }
             double transform = (double)1/degree;
             for (int j = 0;j < length;j ++){
                 if (collection.get(i).matrix[j] > 0){
                     jumpMatrix[i][j] = transform;
                 }
             }
         }
         while (true){
             boolean judgement = false;
             double[] pre_pagerank = Arrays.copyOf(pagerank,length);
             for (int i = 0;i < length;i++){
                 double sum  = 0;
                 for (int j = 0;j < length ;j++){
                     sum+=pagerank[j]*jumpMatrix[j][i];
                 }
                 pagerank[i] = 0.85*sum + 0.15*pre_pagerank[i];
             }
             for (int i = 0;i < length; i++){
                 if (Math.abs((pagerank[i]-pre_pagerank[i])) < 0.1){
                     judgement = true;
                 }
                 else judgement = false;
             }
             if (judgement){
                 break;
             }
         }
    }
    public double[] getPagerank(){
         return this.pagerank;
    }

    //-----------------------------------------------------------------------------------------------------------------------
    public void standaroutput(){
        String path = "G:\\hatsune_miku\\src\\haha.txt";
        String line = System.getProperty("line.separator");
        File f = new File(path);
        int output = 0;
        int digits = 0;
        if (length > 0){
            digits = String.valueOf(length).length();
        }
        try {
            FileWriter out = new FileWriter(f,true);
            for(int i = 0; i < length; i++){
                for(int n = String.valueOf(i).length();n <digits;n++){
                    out.write(0);
                }
                output = i;
                out.write(String.valueOf(output));
            }
            out.write(-1);
            for (int i = 0; i < length; i++) {
                for (int j = i; j < length; j++) {
                    if(collection.get(i).matrix[j] > 0) {
                        output = i;
                        for(int n = String.valueOf(i).length();n <digits;n++){
                            out.write(0);
                        }
                        out.write(String.valueOf(output));
                        output = j;
                        for(int n = String.valueOf(j).length();n <digits;n++){
                            out.write(0);
                        }
                        out.write(String.valueOf(j));
                    }
                }
            }
            out.write(-1);

            out.write(-2);
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
    public void complement(int i){

    }
//    public static void main(String Args[]){
//         webconstruct test = new webconstruct(5);
//         test.ERWebconfig(0.3);
//         test.gragh.prt();
//         test.gragh.setDegreeAttribute();
//         for(int i = 0;i < test.gragh.length ;i++){
//             System.out.println(test.gragh.getDegreeAttribute()[i]);
//         }
//    }

}

