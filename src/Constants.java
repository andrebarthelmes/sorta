package sorta;

public class Constants 
{
	public static int randomSeed = 752;
	public static boolean useRandomSeed=false;
	
	public static int spoteaMaxGenerations = 50000;	
	public static int spoteaParentSize = 4; 
	public static int spoteaPopulationSize = 40; // populationSize - parentSize >= 5 !

	public static int permuteaMaxGenerations = 50000;	
	public static int permuteaParentSize = 4; 
	public static int permuteaPopulationSize = 40; // populationSize - parentSize >= 5 !
	
	
	public static int orderCountToTest = 30;
	public static int spots = 4;
	public static int addPages = 1;
	public static int[] availableOrderCount =     {5,10,15,20,25,30,35,40,45,50,60,70,80,90,100,125,150,175,200};
	public static int[] probabilityofOrderCount = {	0,0,0,0,							//5
													1,1,1,1,1,1,1,1,1,1,				//10
													2,2,2,2,2,2,2,2,2,2,				//15
													3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,		//20
													4,4,4,4,4,4,4,4,4,4,4,4,4,4,4,		//25
													5,5,5,5,5,5,5,5,5,5,				//30
													6,6,6,6,6,							//35
													7,7,7,7,7,7,7,7,7,7,				//40
													8,8,8,								//45
													9,9,9,9,9,							//50
													10,10,10,							//60
													11,11,								//70
													12,									//80
													13,									//90
													14,14,								//100
													15,									//125
													16,									//150
													17,									//175
													18									//200
												  };
}
