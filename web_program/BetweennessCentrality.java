public class BetweennessCentrality extends DismissMethod {
    Gragh gragh;
    BetweennessCentrality(Gragh gragh){
        this.gragh = gragh;
    }
    @Override
    public void dismiss(){
        gragh.setBetweennessMatrix();
        for(int i = 0;i < gragh.length;i++){
            int index = findTheLargestNumberInArray(gragh.getBetweennessMatrix());
            if(index == -1){
                break;
            }
            gragh.setBetweennessMatrix(i,0);
            for(int j = 0;j<gragh.length;j++){
                if(gragh.collection.get(index).matrix[j] != 0){
                    gragh.deleteOneSide(index,j);
                }
            }

        }
    }

}
