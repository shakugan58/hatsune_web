public class EigenvectorCentrality extends DismissMethod{
    Gragh gragh;
     public EigenvectorCentrality(Gragh gragh){
        this.gragh = gragh;
    }
    @Override
    void dismiss() {
        for(int i = 0;i < gragh.length ;i++){
            gragh.setEigenvectorAtribute();
            int index  = findTheLargestNumberInArray(gragh.getDegreeAttribute());
            gragh.deleteOneNote(i);
        }
    }
}
