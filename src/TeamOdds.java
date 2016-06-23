import org.jsoup.nodes.Element;

public class TeamOdds 
{
	private Element team;
	private Element odds;
	private int x;
	private int y;
	
	public TeamOdds()
	{
		
	}
	
	public TeamOdds(Element team, Element odds)
	{
		this.team = team;
		this.odds = odds;
		
		setNumericOdds(odds);
	}
	
	private void setNumericOdds(Element odds)
	{
		int pos = odds.text().indexOf("/");
		
		x = Integer.parseInt(odds.text().substring(1, pos));
		y = Integer.parseInt(odds.text().substring(pos + 1, odds.text().length() - 1));
	}
	
	public Element getTeam()
	{
		return team;
	}
	
	public void setTeam(Element team)
	{
		this.team = team;
	}
	
	public Element getOdds()
	{
		return odds;
	}
	
	public void setOdds(Element odds)
	{
		this.odds = odds;
	}
	
	public int getX() 
	{
		return x;
	}

	public void setX(int x) 
	{
		this.x = x;
	}

	public int getY() 
	{
		return y;
	}

	public void setY(int y) 
	{
		this.y = y;
	}

	public String toString()
	{
		return team.text() + " " + odds.text();
	}
}