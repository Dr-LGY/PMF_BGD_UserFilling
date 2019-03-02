import java.io.IOException;

public class Train_target {
	public static void train(int iterations) {
		
		for (int iter = 0; iter < iterations; iter++){
			
			System.out.print("Iter:" + Integer.toString(iter) + "| ");
			
			try {
				Test.test();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int iter_rand = 0; iter_rand < Data.num_train; iter_rand++) 
			{   	    		
				// ===========================================
				// --- random sampling one triple of (userID,itemID,rating), Math.random(): [0.0, 1.0)
				int rand_case = (int) Math.floor( Math.random() * Data.num_train );
				int userID = Data.indexUserTrain[rand_case];	    		
				int itemID = Data.indexItemTrain[rand_case];
				float rating = Data.ratingTrain[rand_case];
				// ===========================================	    		
	
				// ===========================================
	
				float pred = 0;
				
	
				for (int f=0; f<Data.d; f++)
				{
					pred += Data.U[userID][f] * Data.V[itemID][f];
				}
				
				pred += Data.g_avg + Data.biasU[userID] + Data.biasV[itemID];
				float error = rating - pred;
					// -----------------------
				
				// -----------------------
				// --- update \mu    			
				Data.g_avg = Data.g_avg - Data.gamma * ( -error );

				// --- biasU, biasV
				Data.biasU[userID] = Data.biasU[userID] - Data.gamma * ( -error + Data.beta_u * Data.biasU[userID] );
				Data.biasV[itemID] = Data.biasV[itemID] - Data.gamma * ( -error + Data.beta_v * Data.biasV[itemID] );
	
				// -----------------------
				// --- update U, V
				float [] V_before_update = new float[Data.d];
				for(int f=0; f<Data.d; f++)
				{	
					V_before_update[f] = Data.V[itemID][f];

					float grad_U_f = -error * Data.V[itemID][f] + Data.alpha_u * Data.U[userID][f];
					float grad_V_f = -error * Data.U[userID][f]   + Data.alpha_v * Data.V[itemID][f];
					Data.U[userID][f] = Data.U[userID][f] - Data.gamma * grad_U_f;
					Data.V[itemID][f] = Data.V[itemID][f] - Data.gamma * grad_V_f;		    			
				}

				// -----------------------	    			
				
					
			}
				// ===========================================
			
			
			Data.gamma = (float) (Data.gamma * 0.9);
		} 	
		
	}
	
	
	public static void train1(int iterations) {
		
		for (int iter = 0; iter < iterations; iter++){
			
			System.out.print("Iter:" + Integer.toString(iter) + "| ");
			
			try {
				Test.test1();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int iter_rand = 0; iter_rand < Data.num_train; iter_rand++) 
			{   	    		
				// ===========================================
				// --- random sampling one triple of (userID,itemID,rating), Math.random(): [0.0, 1.0)
				int rand_case = (int) Math.floor( Math.random() * Data.num_train );
				int userID = Data.indexUserTrain[rand_case];	    		
				int itemID = Data.indexItemTrain[rand_case];
				float rating = Data.ratingTrain[rand_case];
				// ===========================================	    		
	
				// ===========================================
	
				float pred = 0;
				
	
				for (int f=0; f<Data.d; f++)
				{
					pred += Data.U[userID][f] *(Data.rho * Data.V[itemID][f] + (1 - Data.rho) * Data.V1[itemID][f]);
				}
				
				pred += Data.g_avg + Data.biasU[userID] + Data.biasV[itemID];
				float error = rating - pred;
					// -----------------------
				
				// -----------------------
				// --- update \mu    			
				Data.g_avg = Data.g_avg - Data.gamma * ( -error );

				// --- biasU, biasV
				Data.biasU[userID] = Data.biasU[userID] - Data.gamma * ( -error + Data.beta_u * Data.biasU[userID] );
				Data.biasV[itemID] = Data.biasV[itemID] - Data.gamma * ( -error + Data.beta_v * Data.biasV[itemID] );
	
				// -----------------------
				// --- update U, V
				float [] V_before_update = new float[Data.d];
				for(int f=0; f<Data.d; f++)
				{	
					V_before_update[f] = Data.V[itemID][f];

					float grad_U_f = -error * (Data.rho * Data.V[itemID][f] + (1 - Data.rho) * Data.V1[itemID][f]) + Data.alpha_u * Data.U[userID][f];
					float grad_V_f = -error * Data.rho * Data.U[userID][f] + Data.alpha_v * Data.V[itemID][f];
					Data.U[userID][f] = Data.U[userID][f] - Data.gamma * grad_U_f;
					Data.V[itemID][f] = Data.V[itemID][f] - Data.gamma * grad_V_f;		    			
				}

				// -----------------------	    			
				
					
			}
				// ===========================================			
			Data.gamma = (float) (Data.gamma * 0.9);
		} 	
		
	}
}
