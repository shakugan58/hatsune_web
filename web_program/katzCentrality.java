public class katzCentrality extends DismissMethod{
    Gragh gragh;
    katzCentrality(Gragh gragh){this.gragh = gragh;};
    @Override
    void dismiss() {
        gragh.setKatzAtribute();
        for(int i = 0;i < gragh.length;i++){
            int index = findTheLargestNumberInArray(gragh.getKatzAtribute());
            if(index == -1){
                break;
            }
            for(int j = 0;j<gragh.length;j++){
                if(gragh.collection.get(index).matrix[j] != 0){
                    gragh.deleteOneSide(index,j);
                }
            }
            gragh.setKatzAtribute();
        }
    }

}
