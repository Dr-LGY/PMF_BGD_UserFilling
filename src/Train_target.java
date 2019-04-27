import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import com.sun.xml.internal.ws.api.server.ContainerResolver;

public class Train_target {
	public static void train(int iterations) {
		//long startTime=System.nanoTime();
		for (int iter = 0; iter < iterations; iter++){
			
			System.out.print("Iter:" + Integer.toString(iter) + "| ");
			
			try {
				Test.test();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			float grad_V[][] = new float[Data.m + 1][Data.d];
			float grad_U[][] = new float[Data.n + 1][Data.d];
			double one_div_NM =  1.0 / (Data.n * Data.m);
			for (int u = 1; u <= Data.n; u++) {
				List<Integer> I_A = new LinkedList<Integer>(Data.I);
				I_A.removeAll(Data.I_u[u]);
				Collections.shuffle(I_A);
				
				for (int i : Data.I_u[u]) {
					float pred = 0; 
					for (int f=0; f<Data.d; f++)
					{
						pred += Data.U[u][f] * Data.V[i][f];
					}
					float error = Data.r[u][i] - pred;
					for(int f=0; f<Data.d; f++)
					{	
						
						grad_U[u][f] += -error * Data.V[i][f] + Data.alpha_u * Data.U[u][f];
							    			
					}
					
					for(int f=0; f<Data.d; f++)
					{	
						grad_V[i][f] += -error * Data.U[u][f]   + Data.alpha_v * Data.V[i][f];
						
							    			
					}
					
				}
			
				for (int count = 0; count < Data.rho * Data.I_u[u].size() && count < I_A.size(); ++count) {
					
					int i = I_A.get(count);	
					float pred = 0; 
					for (int f=0; f<Data.d; f++)
					{
						pred += Data.U[u][f] * Data.V[i][f];
					}
					float error = Data.ave_r_u[u] - pred;
					for(int f=0; f<Data.d; f++)
					{	
						
						grad_U[u][f] += -error * Data.V[i][f] + Data.alpha_u * Data.U[u][f];
							    			
					}
					
					for(int f=0; f<Data.d; f++)
					{	
						grad_V[i][f] += -error * Data.U[u][f]   + Data.alpha_v * Data.V[i][f];
						
							    			
					}
						
					
				}
			
				
			}
			for (int u = 1; u <= Data.n; ++u) {
				for(int f=0; f<Data.d; f++) Data.U[u][f] = (float) (Data.U[u][f] - Data.gamma * grad_U[u][f] * one_div_NM);
				
			}
			for (int i = 1; i <= Data.m; ++i) {
				for(int f=0; f<Data.d; f++) Data.V[i][f] = (float) (Data.V[i][f] - Data.gamma * grad_V[i][f] * one_div_NM);
			}
			/*for (int i = 1; i <= Data.m; i++) {
				float grad_V[] = new float[Data.d];
				for (int u = 1; u <= Data.n; u++) {
					if (Data.r[u][i] != 0) { 
						float pred = 0;
						for (int f=0; f<Data.d; f++)
						{
							pred += Data.U[u][f] * Data.V[i][f];
						}
						float error = Data.r[u][i] - pred;
						for(int f=0; f<Data.d; f++)
						{	
							grad_V[f] += -error * Data.U[u][f]   + Data.alpha_v * Data.V[i][f];
							
								    			
						}
					}	
				}
				for(int f=0; f<Data.d; f++) Data.V[i][f] = (float) (Data.V[i][f] - Data.gamma * grad_V[f] * one_div_NM);
			}*/
			Data.gamma = (float) (Data.gamma * ((float)(200 * iterations) / (iter + 200 * iterations)) );
		} 	
		//long endTime=System.nanoTime(); 

	//	System.out.println("程序运行时间： "+(endTime-startTime)+"ns");
	}
	
	
	/*public static void train1(int iterations) {
		
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
		
	}*/
}
