public class ClosenessCentrality extends DismissMethod{
    Gragh gragh;
    public ClosenessCentrality(Gragh gragh){
        this.gragh = gragh;
    }
    public void dismiss(){
        gragh.setClosenessAtribute();
        for (int i = 0; i < gragh.length ; i++){
            int index = findTheLargestNumberInArray(gragh.getClosenessAtribute());
            if(index == -1){
                break;
            }
            for (int j = 0;j < gragh.length; j++){
                gragh.deleteOneSide(index,j);
            }
            gragh.setClosenessAtribute(index,0);
           // gragh.prt();
        }
    }
}
