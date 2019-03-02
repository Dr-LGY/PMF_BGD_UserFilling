// package TMF_OPC;

import java.io.*;
import java.util.Arrays;

 
public class ReadConfigurations
{
    public static void readConfigurations(String[]args) throws IOException 
	{
    	// read the configurations
        for (int k=0; k < args.length; k++)
        {
        	if (args[k].equals("-d")) Data.d = Integer.parseInt(args[++k]);
    		else if (args[k].equals("-alpha_u")) Data.alpha_u = Float.parseFloat(args[++k]);
    		else if (args[k].equals("-alpha_v")) Data.alpha_v = Float.parseFloat(args[++k]);        		
    		else if (args[k].equals("-alpha_w")) Data.alpha_w = Float.parseFloat(args[++k]);
    		else if (args[k].equals("-alpha_g")) Data.alpha_g = Float.parseFloat(args[++k]);    		   		
    		else if (args[k].equals("-beta_u")) Data.beta_u = Float.parseFloat(args[++k]);
    		else if (args[k].equals("-beta_v")) Data.beta_v = Float.parseFloat(args[++k]);
    		else if (args[k].equals("-lambda")) Data.lambda = Float.parseFloat(args[++k]);
    		else if (args[k].equals("-gamma")) Data.gamma = Float.parseFloat(args[++k]);
    		else if (args[k].equals("-fnTrainData")) Data.fnTrainData = args[++k];
    		else if (args[k].equals("-fnAuxiliaryData")) Data.fnAuxiliaryData = args[++k];
    		else if (args[k].equals("-fnOutputData")) Data.fnOutputData = args[++k];
    		else if (args[k].equals("-fnTestData")) Data.fnTestData = args[++k];
    		else if (args[k].equals("-MinRating")) Data.MinRating = Float.parseFloat(args[++k]);
    		else if (args[k].equals("-MaxRating")) Data.MaxRating = Float.parseFloat(args[++k]);    		
    		else if (args[k].equals("-n")) Data.n = Integer.parseInt(args[++k]);
    		else if (args[k].equals("-m")) Data.m = Integer.parseInt(args[++k]);
    		else if (args[k].equals("-rho")) Data.rho = Float.parseFloat(args[++k]);
    		else if (args[k].equals("-num_iterations")) Data.num_iterations = Integer.parseInt(args[++k]);
        }
			
        // print the configurations
        System.out.println(Arrays.toString(args));
        
        System.out.println("d: " + Integer.toString(Data.d));    	
    	System.out.println("alpha_u: " + Float.toString(Data.alpha_u));
    	System.out.println("alpha_v: " + Float.toString(Data.alpha_v));
    	System.out.println("alpha_w: " + Float.toString(Data.alpha_w));
    	System.out.println("alpha_g: " + Float.toString(Data.alpha_g));    	   	
    	System.out.println("beta_u: " + Float.toString(Data.beta_u));
    	System.out.println("beta_v: " + Float.toString(Data.beta_v));    	 	
    	System.out.println("gamma: " + Float.toString(Data.gamma));    		
    	System.out.println("fnTrainData: " + Data.fnTrainData);
    	System.out.println("fnTestData: " + Data.fnTestData);
    	System.out.println("fnAuxiliaryData: " + Data.fnAuxiliaryData);    	
    	System.out.println("fnOutputData: " + Data.fnOutputData);    	
    	System.out.println("MinRating: " + Float.toString(Data.MinRating));
    	System.out.println("MaxRating: " + Float.toString(Data.MaxRating));
    	System.out.println("n: " + Integer.toString(Data.n));
    	System.out.println("m: " + Integer.toString(Data.m));    	    	

        System.out.println("num_iterations: " + Integer.toString(Data.num_iterations));
    }
}
