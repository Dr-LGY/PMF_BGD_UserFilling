// package TMF_OPC;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.HashSet;

public class Data 
{
	// === Configurations	
	// the number of latent dimensions
	public static int d = 20; 
	public static int num_rating_types = 10; // should be different for different data

	// tradeoff $\alpha_u$
	public static float alpha_u = 0.01f;
	// tradeoff $\alpha_v$
	public static float alpha_v = 0.01f;
	public static float alpha_v1 = 0.01f;

	// tradeoff $\alpha_g$
	public static float alpha_g = 0.01f; // graded observation
	// tradeoff $\alpha_w$
	public static float alpha_w = 0.01f;

	
	// tradeoff $\beta_u$
	public static float beta_u = 0.01f;
	// tradeoff $\beta_v$
	public static float beta_v = 0.01f; 

	// 
	public static float lambda = 1.0f; // when lambda=1, uses auxiliary data
	 
	// learning rate $\gamma$
	public static float gamma = 100f;
	
	public static float rho = 0.8f;

	 // === Input data files
	public static String fnTrainData = "C:\\Users\\LGY\\Desktop\\DATA\\ml-100k\\u1.base";
	public static String fnAuxiliaryData = "C:/Users/Administrator/Desktop/Áª°îÑ§Ï°/ML10M/copy1_01.auxiliary";
	public static String fnTestData = "C:\\Users\\LGY\\Desktop\\DATA\\ml-100k\\u1.test";
	public static String fnOutputData = "";

	// 
	public static int n = 943; // number of users
	public static int m = 1682; // number of items
	public static int num_train_target; // number of target training triples of (user,item,rating)
	public static int num_train_auxiliary; // number of auxiliary training triples of (user,item,rating)
	public static int num_train; // number of training triples of (user,item,rating), num_train = num_train_target+num_train_auxiliary
	public static int num_test; // number of test triples of (user,item,rating)

	public static float MinRating = 1.0f; // minimum rating value (0.5 for ML10M, Flixter; 1 for Netflix)
	public static float MaxRating = 5.0f; // maximum rating value

	// scan number over the whole data
	public static int num_iterations = 100; 

	// === training data (target data and auxiliary dta)
	public static int[] indexUserTrain; // start from index "0"
	public static int[] indexItemTrain; 
	public static float[] ratingTrain;
	public static float[][] r;
	public static HashMap<Integer, HashMap<Integer, HashSet<Integer>>> Train_ExplicitFeedbacksGraded 
	= new HashMap<Integer, HashMap<Integer, HashSet<Integer>>>();
	public static HashMap<Integer, HashSet<Integer>> Train_ExplicitFeedbacks 
	= new HashMap<Integer, HashSet<Integer>>();

	// === test data
	public static int[] indexUserTest;
	public static int[] indexItemTest;
	public static float[] ratingTest;

	// === some statistics, start from index "1"
	public static float[] userRatingSumTrain;
	public static float[] itemRatingSumTrain;
	public static int[] userRatingNumTrain;
	public static int[] itemRatingNumTrain;
	public static int[][] user_graded_rating_number;
	public static int[] user_rating_number;

	// === model parameters to learn, start from index "1"
	public static float[][] U;
	public static float[][] V;
	public static float[][] V1;
	public static float[][][] G;  

	public static float g_avg; // global average rating $\mu$
	public static float[] biasU;  // bias of user
	public static float[] biasV;  // bias of item

	// === file operation
	public static FileWriter fw ;
	public static BufferedWriter bw;
}
