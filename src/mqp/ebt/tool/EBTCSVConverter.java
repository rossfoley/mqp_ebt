package mqp.ebt.tool;

import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;

/**
 * Tool to convert Chromosomes to CSV files
 * @author Ross Foley and Karl Kuhn
 */
public class EBTCSVConverter {
    private final String runName = "radius1";
    private final int minGen = 1;
    private final int maxGen = 100;
    private final int interval = 1; //each one

    /**
     * Converts all generations between minGen and maxGen into a CSV file
     * Only taking the specified counts
     * @throws IOException Hopefully not used.
     */
    public void converter() throws IOException{
        //Buffered Writer
        String bwFileName = "graph/" + runName + ".csv";
        File bwFile = new File(bwFileName);
        bwFile.getParentFile().mkdirs();
        BufferedWriter bw = new BufferedWriter(new FileWriter(bwFile));

        //First line
        bw.write("Generation,Maximum Fitness");
        bw.newLine();
        bw.flush();

        for(int gen = minGen; gen <= maxGen; gen++) {
            if(gen%interval == 0 || gen == 1) {
                //Buffered reader
                String brFileName = "db/" + runName + "/generation" + gen + ".xml";
                File brfil = new File(brFileName);
                BufferedReader br = new BufferedReader(new FileReader(brfil));

                //Get fitness
                br.readLine();
                String fit = br.readLine();
                int index = fit.indexOf('.');
                fit = fit.substring(19, index);

                //Write a line
                bw.write(gen + "," + fit);
                bw.newLine();
                bw.flush();

                //Close the files
                br.close();
            }
        }
        bw.close();
    }

    /**
     * Main method that just runs an instance
     * @param args The command line arguements
     * @throws IOException Hopefully unused
     */
    public static void main(String[] args) throws IOException {
        EBTCSVConverter cv = new EBTCSVConverter();
        cv.converter();
    }
}
