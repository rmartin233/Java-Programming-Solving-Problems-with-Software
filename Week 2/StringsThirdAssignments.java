import edu.duke.*;
import java.io.File;

public class Part1 {
    public int findStopCodon(String dna, int startIndex, String stopCodon)
    {
        int stopIndex = dna.indexOf(stopCodon, startIndex);
        while(stopIndex != -1)
        {   
            
            int different = stopIndex - startIndex;
            if (different % 3 == 0){
                return stopIndex;
            }
            else{
                stopIndex = dna.indexOf(stopCodon, stopIndex+1);
            }
        }   
        return -1;
    }   

    public String findGene(String dna, int index)
    {   
        int atgIndex = dna.indexOf("ATG", index);

        if(atgIndex == -1)
        {
            return "";
        }

        int taaIndex = findStopCodon(dna, atgIndex, "TAA");
        int tgaIndex = findStopCodon(dna, atgIndex, "TGA");
        int tagIndex = findStopCodon(dna, atgIndex, "TAG");

        int minIndex = -1;

        if((taaIndex < tgaIndex && taaIndex != -1)||tgaIndex == -1)
        {
            minIndex = taaIndex;
        }
        else
        {
            minIndex = tgaIndex;
        }

        if((tagIndex < minIndex && tagIndex != -1)||minIndex == -1)
        {
            minIndex = tagIndex;
        }
        
        if (minIndex == -1)
        {
            return "";
        }

        return dna.substring(atgIndex, minIndex + 3);
    }   

    public void printAllGenes(String dna)
    {
    int startIndex = 0;

    while(true)
    {
        String currentGene = findGene(dna, startIndex);
        if (currentGene == "")
        {
            break;
        }
        System.out.println(currentGene);
        startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }
    }

    public StorageResource getAllGenes(String dna){
        StorageResource sr = new StorageResource();
        int startIndex = 0;

        while(true){
            String currentGene = findGene(dna, startIndex);
            if (currentGene == "")
            {
                break;
            }   
            sr.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) + currentGene.length();
        }

        return sr;
    }

    

    public void processGenes(StorageResource sr)
    {
        System.out.println("All Genes and counts:");
        int count = 0; int longest = 0;
        for (String item : sr.data())
        {
                count++;
                System.out.println(item);
        }
        System.out.println(count);


        System.out.println("All the Strings in sr that are longer than 60 characters: ");
        int count_9 = 0;
        for (String item : sr.data())
        {
            longest = item.length() > longest ?  item.length() : longest;
            if (item.length() > 60)
            {
                count_9++;
                System.out.println(item);
            }
        }
        System.out.printf("The number of Strings in sr that are longer than 60 characters: %d\n", count_9);


        System.out.println("Strings in sr whose C-G-ratio is higher than 0.35:");
        int count_35 = 0;
        for (String item : sr.data())
        {
            double cCount = 0; double gCount = 0;
            for(char ch : item.toCharArray())
            {
                if(ch == 'C')
                {
                    cCount++;
                }
                if(ch == 'G')
                {
                    gCount++;
                }
            }
            double cgRatio = cCount / gCount ;

            if(cgRatio > 0.35)
            {
                count_35++;
                System.out.println(item);
            }
        }
        System.out.printf("print the number of strings in sr whose C-G-ratio is higher than 0.35: %d\n", count_35);

    }

    public StorageResource srIsFile(){
        FileResource fr = new FileResource("Axl2p.fa");
        String dna = fr.asString();
        System.out.println(dna);
        dna = dna.toUpperCase();
        System.out.println(dna);
        StorageResource sr = getAllGenes(dna);
        return sr;
    }
    
    public void testGene()
    {
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        System.out.println(dna);
        dna = dna.toUpperCase();
        System.out.println(dna);
        printAllGenes(dna);
    }
    
    public void testGenes()
    {
    	mystery("AATBBTCCTDDTEET")
        processGenes(srIsFile());
    }

	public String mystery(String dna) {
	  int pos = dna.indexOf("T");
	  int count = 0;
	  int startPos = 0;
	  String newDna = "";
	  if (pos == -1) {
	    return dna;
	  }
	  while (count < 3) {
	    count += 1;
	    newDna = newDna + dna.substring(startPos,pos);
	    startPos = pos+1;
	    pos = dna.indexOf("T", startPos);
	    if (pos == -1) {
	      break;
	    }
	  }
	  newDna = newDna + dna.substring(startPos);
	  return newDna;
	}
}

    







