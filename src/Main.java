// package TMF_OPC;

import java.io.*;

//movielens-100K
//java -Xmx2048m Main -d 20 -alpha_u 0.01 -alpha_v 0.01 -alpha_w 0.01 -alpha_g 0 -beta_u 0.01 -beta_v 0.01 1 -lambda 1 -rho 0.5 -gamma 0.01 -fnTrainData ./ml-100k/copy1.target -fnTestData ./ml-100k/copy1.test -fnAuxiliaryData ./ml-100k/copy1.auxiliary -fnOutputData Result_TMF_ML100K.txt -n 943 -m 1682 -num_iterations 50 -MinRating 1 -MaxRating 5
//java -Xmx2048m Main -d 20 -alpha_u 0.001 -alpha_v 0.001 -alpha_w 0.001 -alpha_g 0 -beta_u 0.001 -beta_v 0.001 1 -lambda 1 -rho 0.5 -gamma 0.01 -fnTrainData ./ml-100k/copy1.target -fnTestData ./ml-100k/copy1.test -fnAuxiliaryData ./ml-100k/copy1.auxiliary -fnOutputData Result_TMF_ML100K.txt -n 943 -m 1682 -num_iterations 50 -MinRating 1 -MaxRating 5

//movielens-1M
//java -Xmx2048m Main -d 20 -alpha_u 0.01 -alpha_v 0.01 -alpha_w 0.01 -alpha_g 0 -beta_u 0.01 -beta_v 0.01 1 -lambda 1 -rho 0.5 -gamma 0.01 -fnTrainData ./ml-1m/copy1.target -fnTestData ./ml-1m/copy1.test -fnAuxiliaryData ./ml-1m/copy1.auxiliary -fnOutputData Result_Federated_ML1M.txt -n 6040 -m 3952 -num_iterations 50 -MinRating 1 -MaxRating 5


//movielens-10M
//java -Xmx2048m Main -d 20 -alpha_u 0.01 -alpha_v 0.01 -alpha_w 0.01 -alpha_g 0 -beta_u 0.01 -beta_v 0.01 1 -lambda 1 -rho 0.5 -gamma 0.01 -fnTrainData ./ml-10m/copy1.target -fnTestData ./ml-10m/copy1.test -fnAuxiliaryData ./ml-10m/copy1.auxiliary -fnOutputData Result_Federated_ML10M.txt -n 71567 -m 10681 -num_iterations 50 -MinRating 1 -MaxRating 5


public class Main 
{
    public static void main(String[] args) throws IOException 
	{	
		// 1. read configurations		
		ReadConfigurations.readConfigurations(args);

		// 2. read training data and test data
        ReadData.readData();
               
		// 3. apply initialization
		Initialization.initialization();
		
		// 4. train

		Train.train(Data.num_iterations);
		

		// 5. test
		Test.test();		
    }
}
