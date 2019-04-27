// package TMF_OPC;

import java.io.IOException;
import java.util.*;

public class Test 
{
    public static void test() throws IOException 
	{       	
    	// --- number of test cases
    	float mae=0;
    	float rmse=0;    	
    	
    	// ====================================================
    	// --- for efficiency
//    	float[] tilde_Uu_g = new float[Data.d];
//		float[][] tilde_Uu = new float[Data.n+1][Data.d];
//		
//    	for( int userID = 1; userID<=Data.n; userID++ )
//    	{	
//    		// ---
//
//    		for(int g=1; g<=Data.num_rating_types; g++)
//        	{
//        		if( Data.user_graded_rating_number[userID][g]>0 )
//        		{
//        			float explicit_feedback_num_u_sqrt 
//        				= (float) Math.sqrt( Data.user_graded_rating_number[userID][g] );
//    	    		
//        			// --- aggregation
//        			HashSet<Integer> itemSet = Data.Train_ExplicitFeedbacksGraded.get(userID).get(g);
//    	    		for( int i2 : itemSet )
//    	    		{
//    	    			for (int f=0; f<Data.d; f++)
//    	    	    	{
//    	    				tilde_Uu_g[f] += Data.G[i2][g][f];
//    	    	    	}
//    	    		}
//    	    		
//    	    		// --- normalization
//    	    		for(int f=0; f<Data.d; f++)
//        		    {	
//    	    			tilde_Uu[userID][f] += tilde_Uu_g[f] / explicit_feedback_num_u_sqrt;
//    	    			tilde_Uu_g[f] = 0;
//        		    }
//        		}
//        	}
//    		
//    		
//    		
//
//    	}
    	// ====================================================
    	    	
    	for(int t=0; t<Data.num_test; t++)
    	{
    		int userID = Data.indexUserTest[t];
    		int itemID = Data.indexItemTest[t];
    		float rating = Data.ratingTest[t];
    		
    		// ===========================================    		
    		// --- prediction via inner product
    		float pred =  0;//Data.biasU[userID] + Data.biasV[itemID];
    		for (int f=0; f<Data.d; f++)
    		{
    			pred +=  Data.U[userID][f] * Data.V[itemID][f];	
    		}
//    		float pred1 = (float) 0.0;
//    		for (int f=0; f<Data.d; f++)
//    		{
//    			pred1 +=  Auxiliary.W[userID][f] * Data.V1[itemID][f];	
//    		}
////    		System.out.println("pred1 = " + pred1);
//    		pred = pred*4/5 + pred1/5;
    		// ===========================================
    		
    		// ===========================================
    		// --- post processing predicted rating
    		// if(pred < 1) pred = 1;
    		// if(pred < 0.5) pred = 0.5f;
			// if(pred > 5) pred = 5;
    		if(pred < Data.MinRating) pred = Data.MinRating;
    		if(pred > Data.MaxRating) pred = Data.MaxRating;
    		
			float err = pred-rating;
			mae += Math.abs(err);
			rmse += err*err;
    		// ===========================================    		
    	}
    	float MAE = mae/Data.num_test;
    	float RMSE = (float) Math.sqrt(rmse/Data.num_test);
    	
    	//output result
    	String result = "MAE:" + Float.toString(MAE) +  "| RMSE:" + Float.toString(RMSE);
    	System.out.println(result);
//    	try {
//			Data.bw.write(result +"\r\n"); 
//			Data.bw.flush();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }
    // =============================================================
    
    
    /*public static void test1() throws IOException 
	{       	
    	// --- number of test cases
    	float mae=0;
    	float rmse=0;    	
    	
    	// ====================================================
    	    	
    	for(int t=0; t<Data.num_test; t++)
    	{
    		int userID = Data.indexUserTest[t];
    		int itemID = Data.indexItemTest[t];
    		float rating = Data.ratingTest[t];
    		
    		// ===========================================    		
    		// --- prediction via inner product
    		float pred = 0;//Data.g_avg + Data.biasU[userID] + Data.biasV[itemID];
    		for (int f=0; f<Data.d; f++)
    		{
    			pred +=  Data.U[userID][f] * (Data.rho * Data.V[itemID][f] + (1 - Data.rho) * Data.V1[itemID][f]);	
    		}
    		// ===========================================
    		
    		// ===========================================
    		// --- post processing predicted rating
    		if(pred < Data.MinRating) pred = Data.MinRating;
    		if(pred > Data.MaxRating) pred = Data.MaxRating;
    		
			float err = pred-rating;
			mae += Math.abs(err);
			rmse += err*err;
    		// ===========================================    		
    	}
    	float MAE = mae/Data.num_test;
    	float RMSE = (float) Math.sqrt(rmse/Data.num_test);
    	
    	//output result
    	String result = "MAE:" + Float.toString(MAE) +  "| RMSE:" + Float.toString(RMSE);
    	System.out.println(result);
//    	try {
//			Data.bw.write(result +"\r\n"); 
//			Data.bw.flush();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    }*/
}




