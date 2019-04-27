// package TMF_OPC;

public class Initialization
{
    public static void initialization()
	{
    	// --- model parameters to learn, start from index "1"
        Data.U = new float[Data.n+1][Data.d];
        Data.V = new float[Data.m+1][Data.d];
        Data.V1 = new float[Data.m+1][Data.d];
        Data.G = new float[Data.m+1][Data.num_rating_types+1][Data.d];
        
    /*	Data.biasU = new float[Data.n+1];  // bias of user
    	Data.biasV = new float[Data.m+1];  // bias of item
     */   
    	// ======================================================    	
    	// --- initialization of U, W, V, G
    	for (int u=1; u<Data.n+1; u++)
    	{
    		for (int f=0; f<Data.d; f++)
    		{
    			Data.U[u][f] = (float) ( (Math.random()-0.5)*0.01 );
    		}
    	}
    	//
    	for (int i=1; i<Data.m+1; i++)
    	{
    		for (int f=0; f<Data.d; f++)
    		{
    			Data.V[i][f] = (float) ( (Math.random()-0.5)*0.01 );
    			Data.V1[i][f] = (float) ( (Math.random()-0.5)*0.01 );
    		}
    	}
    	//
    	for(int i=1; i<Data.m+1; i++)
		{
    		for(int g=1; g<=Data.num_rating_types; g++)	
        	{
        		for(int f=0; f<Data.d; f++)
        		{	
        			Data.G[i][g][f] = (float) ( (Math.random()-0.5)*0.01 );
        		}
        	}
		}
    	// ======================================================
    	
    	
    	// ======================================================
    	// --- initialization of biasU, biasV
    /*	for (int u=1; u<Data.n+1; u++)
    	{
    		if(Data.userRatingNumTrain[u]>0)
    		{
    			Data.biasU[u]= ( Data.userRatingSumTrain[u]-Data.g_avg*Data.userRatingNumTrain[u] ) / Data.userRatingNumTrain[u];  
    		}
    	}
    	//
    	for (int i=1; i<Data.m+1; i++)
    	{
    		if(Data.itemRatingNumTrain[i]>0)
    		{
    			Data.biasV[i] = ( Data.itemRatingSumTrain[i]-Data.g_avg*Data.itemRatingNumTrain[i] ) / Data.itemRatingNumTrain[i];  
    		}
    	}   */ 	
    	// ====================================================== 
    }
}
