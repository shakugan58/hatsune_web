import java.util.Arrays;

abstract class DismissMethod {
    abstract void dismiss();
    int findTheLargestNumberInArray(int[] data){
        int max =data[0];
        int index = 0;
        int[] copy = new int[data.length];
        for(int i = 0;i < data.length;i++){
            if(data[i] > max){
                max = data[i];
                index = i;
            }
        }
        if (max == 0){
            return -1;
        }

        return index;
    }
    int findTheLargestNumberInArray(double [] data){
        double  max =data[0];
        int index = 0;
        int[] copy = new int[data.length];
        for(int i = 0;i < data.length;i++){
            if(data[i] > max){
                max = data[i];
                index = i;
            }
        }
        if (max == 0){
            return -1;
        }

        return index;
    }
}
