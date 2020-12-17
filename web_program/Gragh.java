import java.util.ArrayList;

public class Gragh {
    ArrayList<Note> collection = new ArrayList<Note>();
    int length;
    private int[] degreeAtribute;
    private int[] closenessAtribute;
    private double[] BetweennessMatrix;
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
                if(used[j] == true || this.collection.get(start).matrix[j] <= 0){
                    continue;
                }
                if(this.collection.get(start).matrix[j] > 0 && (minDis[j] < 0|| minDis[j] > minDis[start] + this.collection.get(start).matrix[j])){
                    minDis[j] = minDis[start] + this.collection.get(start).matrix[j];
                    path[j] = start;
                }
            }
            start = -1;
            for (int j =  0;j <this.length;j++){
                if (minDis[j] < 0 || used[j] == true){
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
                if(used[j] == true || this.collection.get(start).matrix[j] <= 0){
                    continue;
                }
                if(this.collection.get(start).matrix[j] > 0 && (minDis[j] < 0|| minDis[j] > minDis[start] + this.collection.get(start).matrix[j])){
                    minDis[j] = minDis[start] + this.collection.get(start).matrix[j];
                }
            }
            start = -1;
            for (int j =  0;j <this.length;j++){
                if (minDis[j] < 0 || used[j] == true){
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

