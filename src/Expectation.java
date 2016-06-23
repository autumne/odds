public class Expectation 
{
	double x1, y1, odds1;
	double y2, x2, odds2;
	double bet;
	double win1, win2;
	double overall1, overall2;
	double predictor;
	
	public static void main(String args[])
	{
		Expectation oddsChecker = new Expectation(2,1,2,5,5); // x1:y1, y2:x2, bet
		
		//FILL THIS LINE OUT FOR THE SIMULATOR TO USE YOUR PARAMETERS
		oddsChecker.simulation(5, 5, oddsChecker.getOdds1());//getOdds2 is the reciprocal of odds2
	}
	
	public double getOdds1() 
	{
		return odds1;
	}
	public double getOdds2() 
	{//getOdds2 is the reciprocal of odds2
		
		return (1-odds2);
	}

	Expectation(double setX1, double setY1, double setY2, double setX2, double setBet)
	{
		x1 = setX1; //x1:y1
		y1 = setY1;
		odds1 = x1/(x1+y1);
		
		y2 = setY2; //y2:x2
		x2 = setX2;
		odds2 = y2/(y2+x2);
		
		bet = setBet;
		
		win1 = (bet/x1)*y1+-bet;
		System.out.println("Odds x1 win " + odds1 + ":" + (1-odds1));	
		win2  = (bet/y2)*x2+-bet;
		System.out.println("Odds y2 win " + odds2+ ":" + (1-odds2));


		System.out.println("Payout 1 $" + win1);
		System.out.println("Payout 2 $" + win2);

		overall1 = ((win1)*odds1 + (win2)*(1-odds1));
		
		System.out.println("Odds1 infinite expected outcome per bet $" + overall1);
		
		overall2 = ((win1)*(1-odds2) + (win2)*odds2);
		
		System.out.println("Odds2 infinite expected outcome per bet $" + overall2);
		
		predictor = x2/x1*y1/y2;
		System.out.println("Outcome predictor " + predictor);
		System.out.println("-");
	}

	
	public void simulation(int simulations, int runsPerSimulation, double oddsToUse)
	{
		double[] result = new double[simulations];
		double money = 0;
		double average = 0;
		double[] investmentNeeded = new double[simulations];
		double averageInvestmentNeeded = 0;
		double greatestInvestmentNeeded = 0;
		for(int i = 0; i <simulations;i++){
			money = 0;
			investmentNeeded[i] = -1*bet;
			for(int j = 0; j < runsPerSimulation;j++){
				if(Math.random() < oddsToUse){
					money = money + win1;
				}else{
					money = money + win2;
				}
				if(investmentNeeded[i] > money ){
					investmentNeeded[i] = money;
				}
				if(greatestInvestmentNeeded > money ){
					greatestInvestmentNeeded = money;
				}
				//System.out.println(money);
			}
			averageInvestmentNeeded = averageInvestmentNeeded + investmentNeeded[i];
			average = average + money;
			result[i] = money;
			//System.out.println(result[i]);
			
		}
		averageInvestmentNeeded = averageInvestmentNeeded/simulations;
		average = average/simulations;
		System.out.println("");
		System.out.println("SIMULATION");
		System.out.println("");
		System.out.println("Simulations: " + simulations);
		System.out.println("Runs per simulation: " + runsPerSimulation);
		System.out.println("");
		System.out.println("Average Return: $" + average);
		System.out.println("Odds1  Expected Average: $" + overall1*runsPerSimulation);
		System.out.println("Odds2  Expected Average: $" + overall2*runsPerSimulation);	
		System.out.println("");
		System.out.println("Average Investment Needed: $" + -1* averageInvestmentNeeded);
		System.out.println("Greatest Investment Needed: $" + -1* greatestInvestmentNeeded);

	}
	
}