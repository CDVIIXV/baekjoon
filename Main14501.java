package baekjoon;

import java.util.Scanner;

public class Main14501
{
	static final int max_pay = 1001;
	
	static int revenue, day;
	static int[] schedule;
	
	public static void max_revenue(int date, int free, int pay)
	{
		if(date == day && free == 0)
		{
			if(pay > revenue)
				revenue = pay;
		}
		else if(date < day)
		{
			if(free > 0)
			{
				max_revenue(date+1, free-1, pay);
			}
			else 
			{
				max_revenue(date+1, 0, pay);
				int delay = schedule[date] / max_pay;
				int datepay = schedule[date] % max_pay;
				max_revenue(date+1, delay-1, pay + datepay);
			}
		}
	}
	
	public static void main(String[] args)
	{
		Scanner scan = new Scanner(System.in);
		day = scan.nextInt();
		schedule = new int[day];
		for(int i=0; i<day; ++i)
		{
			int date = scan.nextInt();
			int pay = scan.nextInt();
			schedule[i] = date * max_pay + pay;
		}
		scan.close();
		revenue = 0;
		max_revenue(0, 0, 0);
		System.out.println(revenue);
	}
}

