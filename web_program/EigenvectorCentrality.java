public class EigenvectorCentrality extends DismissMethod{
    Gragh gragh;
     public EigenvectorCentrality(Gragh gragh){
        this.gragh = gragh;
    }
    @Override
    void dismiss() {
        gragh.setEigenvectorAtribute();
        for(int i = 0;i < gragh.length ;i++){
            int index  = findTheLargestNumberInArray(gragh.getDegreeAttribute());

        }
    }
}
