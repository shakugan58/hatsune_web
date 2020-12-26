public class PagerankCentrality extends DismissMethod{
    Gragh gragh;
    PagerankCentrality(Gragh gragh){
        this.gragh = gragh;
    }
    @Override
    void dismiss() {
        gragh.setPagerank();
        for(int i = 0;i < gragh.length;i++){
            int index = findTheLargestNumberInArray(gragh.getPagerank());
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
