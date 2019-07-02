package baekjoon;

import java.math.BigInteger;
import java.util.Scanner;

public class Main01837{
	
	public static boolean isPrime(int number) {
//		if(number < 2 || (number != 2 && number % 2 == 0))
//			return false;
		int sqrt = (int)Math.sqrt(number);
		for(int i=3; i<=sqrt; i+=2)
			if(number % i == 0)
				return false;
		return true; 
	}
	
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String Ps = input.next();
        int k = input.nextInt();
        input.close();
        BigInteger P = new BigInteger(Ps);
        boolean good = true;
        if(P.mod(new BigInteger(2+"")).equals(BigInteger.ZERO)) {
        	good = false;
        	k = 2;
        }
        for(int i=3; i<k && good; i+=2)
            if(P.mod(new BigInteger(i+"")).equals(BigInteger.ZERO) && isPrime(i)) {
            	good = false;
            	k = i;
            }
        if(good)
            System.out.println("GOOD");
        else
            System.out.println("BAD " + k);
    }
}