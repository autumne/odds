import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 * Scraper for oddschecker.com
 *
 */
public class Scraper 
{
	private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) "
			+ "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36";
	
	private static final String DRAW = "DRAW";
	
	public static void main(String[] args)
	{
		Document webpage;
		String[] pagesToCheck = Websites.WEBSITES;
		
		try
		{
			for (int wp = 0; wp < pagesToCheck.length; wp++)
			{
				webpage = Jsoup.connect(pagesToCheck[wp]).userAgent(USER_AGENT).get();

				printTitle(webpage);
				
				printTeamOdds(webpage, true);
				
				System.out.println("\n========================================================");
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	private static void printTeamOdds(Document webpage, boolean ignoreDraws) 
	{
		ArrayList<TeamOdds> teamOdds = new ArrayList<TeamOdds>();
		Elements teams, odds;
		TeamOdds newTeam, teamA = new TeamOdds(), teamB = new TeamOdds();
		boolean isFirstTeam = true;
		Expectation expectation;
		
		teams = webpage.select("span[class$=fixtures-bet-name]");
		odds = webpage.select("span[class$=odds]");
		
		System.out.println("Number of odds: " + odds.size());
		System.out.println("Ignore draws: " + ignoreDraws + "\n");
		
		if (teams.size() == odds.size())
		{
			for (int i = 0; i < teams.size(); i++)
			{
				if (teams.get(i).text().toUpperCase().contains(DRAW) && ignoreDraws)
				{
				}
				else
				{
					newTeam = new TeamOdds(teams.get(i), odds.get(i));
					teamOdds.add(newTeam);
					System.out.println(teamOdds.get(teamOdds.size() - 1));
					if (isFirstTeam)
					{
						teamA = newTeam;
					}
					else
					{
						teamB = newTeam;
						expectation = new Expectation(teamA.getX(), teamA.getY(), teamB.getX(), teamB.getY(), 5);
					}
					isFirstTeam = !isFirstTeam;
				}
			}
		}
		else
		{
			System.out.println("Error: Number of teams does not match number of odds.");
		}
	}
	
	private static void printTitle(Document webpage)
	{
		String title = webpage.title();
		System.out.println(title + "\n");
	}
}