public class Note {
    int Note_id;
    int length;
    int matrix[] = null;
    public Note(int note_id,int length){
        this.length = length;
        this.Note_id = note_id;
        matrix = new int[length];
    }
    public Note(int note_id,int length,int[] array){
        this.length = length;
        this.Note_id  = note_id;
        this.matrix =array.clone();
    }
    void asssenmbel(){
        String xianshi = this.matrix.toString();
        System.out.println(xianshi);
    }
    int getDegree(){
        int degree = 0;
        for(int i = 0;i < this.length;i++){
            if(matrix[i] != 0){
                degree++;
            }
        }
        return degree;
    }

}
