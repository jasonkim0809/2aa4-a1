package ca.mcmaster.se2aa4.mazerunner;

public class Path {
    private String currentPath = "";

    public void concatToPath(String newInstruction){
        this.currentPath = this.currentPath + newInstruction;
    }
    
    public String toFactorized(){ // consists of F, R, or L
        if (this.currentPath.length() == 0){
            return this.currentPath;
        }
        String input = this.currentPath;

        char current = input.charAt(0);
        int sum = 0;
        String factorized = "";

        for (int i = 0; i < input.length(); i++){
            current = input.charAt(i);
            for (int j = i; j < input.length(); j++){
                if (input.charAt(j) == current){
                    sum++;
                }
                else{
                    j = input.length();
                }
            }
            factorized = (sum == 1) ? factorized + current + " " : factorized + sum + current + " ";
            i += (sum - 1);
            sum = 0;
        }
        return factorized.substring(0,factorized.length() - 1);
    }

    public String toCanonical(){
        return this.currentPath;
    }
}
