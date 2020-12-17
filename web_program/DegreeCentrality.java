public class DegreeCentrality extends DismissMethod{
    Gragh gragh;
    DegreeCentrality(Gragh gragh){
        this.gragh = gragh;
    }
    @Override
    public void dismiss(){

        for(int i = 0;i < gragh.length;i++){
            gragh.setDegreeAttribute();
            int index = findTheLargestNumberInArray(gragh.getDegreeAttribute());
            if(index == -1){
                break;
            }
            for(int j = 0;j<gragh.length;j++){
                if(gragh.collection.get(index).matrix[j] != 0){
                    gragh.deleteOneSide(index,j);
                }
            }
            gragh.prt();
        }
    }
}
