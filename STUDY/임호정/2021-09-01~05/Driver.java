package ssafy;

import org.apache.hadoop.util.ProgramDriver;

public class Driver {
	public static void main(String[] args) {
		int exitCode = -1;
		ProgramDriver pgd = new ProgramDriver();
		try {

			pgd.addClass("wordcount", Wordcount.class, "A map/reduce program that performs word counting.");
			pgd.addClass("wordcount1", Wordcount1.class, "A map/reduce program that performs word counting.");
			pgd.addClass("wordcountsort", Wordcountsort.class, "A map/reduce program that performs word counting.");
			pgd.addClass("inverted", InvertedIndex.class, "A map/reduce program that performs word counting.");
			pgd.addClass("matadd", MatrixAdd.class, "A map/reduce program that performs word counting.");

			pgd.addClass("matmulti", MatrixMulti.class, "A map/reduce program that performs word counting.");

			pgd.addClass("allpair", AllPairPartition.class, "A map/reduce program that performs word counting.");

			pgd.addClass("allpairself", AllPairPartitionSelf.class, "A map/reduce program that performs word counting.");


      			pgd.driver(args);
			exitCode = 0;
		}
		catch(Throwable e) {
			e.printStackTrace();
		}

		System.exit(exitCode);
	}
}
