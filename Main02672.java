package baekjoon;

import java.util.Scanner;

public class Main02672
{
	static int testCaseCount;
    static double[][] nemos;
    
    public static double sub(double cx1, double cy1, double cx2, double cy2, int index)
    {
    	if(index < 0)   
            return (cx2-cx1) * (cy2-cy1);
    	double px1 = nemos[index][0], py1 = nemos[index][1], px2 = nemos[index][2], py2 = nemos[index][3];
        if(px1 <= cx1 && px2 >= cx2)
        {
            if(py1 <= cy1 && py2 >= cy2)                   
                return 0;
            if(py1 <= cy1 && cy1 < py2 && py2 < cy2)        
                return sub(cx1, py2, cx2, cy2, index-1);
            if(cy1 < py1 && py1 < cy2 && cy2 <= py2)
                return sub(cx1, cy1, cx2, py1, index-1);
            if(cy1 < py1 && py2 < cy2)
                return sub(cx1, cy1, cx2, py1, index-1) + sub(cx1, py2, cx2, cy2, index-1);
        }
        if(px1 <= cx1 && cx1 < px2 && px2 < cx2)
        {
            if(py1 <= cy1 && py2 >= cy2)
                return sub(px2, cy1, cx2, cy2, index-1);
            if(py1 <= cy1 && cy1 < py2 && py2 < cy2)
                return sub(cx1, py2, px2, cy2, index-1) + sub(px2, cy1, cx2, cy2, index-1);
            if(cy1 < py1 && py1 < cy2 && cy2 <= py2)
                return sub(cx1, cy1, px2, py1, index-1) + sub(px2, cy1, cx2, cy2, index-1);
            if(cy1 < py1 && py2 < cy2)
                return sub(cx1, cy1, px2, py1, index-1) + sub(cx1, py2, px2, cy2, index-1) + sub(px2, cy1, cx2, cy2, index-1);
        }
        if(cx1 < px1 && px1 < cx2 && cx2 <= px2)
        {
            if(py1 <= cy1 && py2 >= cy2)
                return sub(cx1, cy1, px1, cy2, index-1);
            if(py1 <= cy1 && cy1 < py2 && py2 < cy2)
                return sub(cx1, cy1, px1, cy2, index-1) + sub(px1, py2, cx2, cy2, index-1);
            if(cy1 < py1 && py1 < cy2 && cy2 <= py2)
                return sub(cx1, cy1, px1, cy2, index-1) + sub(px1, cy1, cx2, py1, index-1);
            if(cy1 < py1 && py2 < cy2)
                return sub(cx1, cy1, px1, cy2, index-1) + sub(px1, cy1, cx2, py1, index-1) + sub(px1, py2, cx2, cy2, index-1);
        }
        if(cx1 < px1 && px2 < cx2)
        {
            if(py1 <= cy1 && py2 >= cy2)
                return sub(cx1, cy1, px1, cy2, index-1) + sub(px2, cy1, cx2, cy2, index-1);
            if(py1 <= cy1 && cy1 < py2 && py2 < cy2)
                return sub(cx1, cy1, px1, cy2, index-1) + sub(px2, cy1, cx2, cy2, index-1) + sub(px1, py2, px2, cy2, index-1);
            if(cy1 < py1 && py1 < cy2 && cy2 <= py2)
                return sub(cx1, cy1, px1, cy2, index-1) + sub(px2, cy1, cx2, cy2, index-1) + sub(px1, cy1, px2, py1, index-1);
            if(cy1 < py1 && py2 < cy2)
                return sub(cx1, cy1, px1, cy2, index-1) + sub(px2, cy1, cx2, cy2, index-1) + sub(px1, cy1, px2, py1, index-1) + sub(px1, py2, px2, cy2, index-1);
        }
        return sub(cx1, cy1, cx2, cy2, index-1);
    }
    
    public static double solution() {
    	double answer = 0;
    	for(int i=0; i<nemos.length; ++i)
    		answer += sub(nemos[i][0], nemos[i][1], nemos[i][2], nemos[i][3], i-1);
        return answer;
    }
    
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        testCaseCount = input.nextInt();
        nemos = new double[testCaseCount][4];
        for(int i=0; i<testCaseCount; ++i)
        {    
            nemos[i][0] = input.nextDouble();
            nemos[i][1] = input.nextDouble();
            nemos[i][2] = nemos[i][0] + input.nextDouble();
            nemos[i][3] = nemos[i][1] + input.nextDouble();
        }
        double answer = solution();
        if(answer == (int)answer)
        	System.out.println((int)answer);
        else
        	System.out.printf("%.2f", solution());
        input.close();
    }
}
