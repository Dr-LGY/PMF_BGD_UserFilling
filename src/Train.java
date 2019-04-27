import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import com.sun.xml.internal.ws.api.server.ContainerResolver;

public class Train {
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
		
			Data.gamma = (float) (Data.gamma * ((float)(200 * iterations) / (iter + 200 * iterations)) );
		} 	
		
	}
	
	
	
}
