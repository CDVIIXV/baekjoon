package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

// https://www.udebug.com/UVa/10138 테스트 케이스
public class Main04413 {

	private static final int hour_count = 24, per_tip = 100, account_charge = 200;	// 24시간, 횟수마다 1달러, 총 요금에 2달러
	private static final int time_data_count = 4, time_delim_increase = 100;	// 달:일:시:분, 두 자리수씩 간격을 잡아줌(*100)
	private static final int dollar_cent_rate = 100;
	private static final String delim = " :";	// 한 라인당 공백( )과 콜론(:) 으로 구분
	
	private static int[] hourPay;
	private static ArrayList<Record> records;
	
	static class Record implements Comparator<Record> {
		private String name;
		private int timeValue, way;
		private boolean isExit;
		
		public Record() {}	// compareTo only
		
		public Record(String name, int timeValue, boolean isExit, int way) {
			this.name = name;
			this.timeValue = timeValue;
			this.isExit = isExit;
			this.way = way;
		}

		// 차량 이름 순서로 나열하고 같은 이름 차량은 시간 순서로 나열한다.
		@Override
		public int compare(Record o1, Record o2) {
			int nameCompare = o1.name.compareTo(o2.name);
			if(nameCompare == 0)	// 같은 차량
				return o1.timeValue < o2.timeValue ? -1 : o1.timeValue > o2.timeValue ? 1 : 0;
			else
				return nameCompare;
		}
	}
	
	// 차량 1대에 대한 계산
	public static void calculate(ArrayList<Record> oneCarRecord, String carName) {
		boolean exit = true;
		int size = oneCarRecord.size(), startIndex = 0, pay = account_charge;
		for(int i=0; i<size; ++i) {
			Record car = oneCarRecord.get(i);
			if(exit && !car.isExit) {	// 입장 설정
				exit = false;
				startIndex = i;
			} else if(!exit && !car.isExit)	// 입장 갱신
				startIndex = i;
			else if(!exit && car.isExit) {	// 요금 계산
				int hour = (oneCarRecord.get(startIndex).timeValue / time_delim_increase) % time_delim_increase;
				pay += hourPay[hour] * Math.abs(car.way - oneCarRecord.get(startIndex).way) + per_tip;
				startIndex = -1;
                exit = true;
			}
		}
        if(pay > account_charge)	// 정산된 요금이 있을 때 출력한다.
            System.out.printf("%s $%d.%02d\n", carName, (pay / dollar_cent_rate), (pay % dollar_cent_rate));
	}
	
	// 차량 분류
	public static void classificate() {
		Collections.sort(records, new Record());
		ArrayList<Record> oneCarRecord = new ArrayList<Record>();
		Record car = records.get(0);
		oneCarRecord.add(car);
		for(int i=1; i<records.size(); ++i) {
			if(car.name.equals(records.get(i).name))
				oneCarRecord.add(records.get(i));
			else {
				calculate(oneCarRecord, car.name);
				oneCarRecord = new ArrayList<Record>();
				car = records.get(i);
				oneCarRecord.add(car);
			}
		}
		calculate(oneCarRecord, car.name);
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		String line = input.readLine();
		StringTokenizer words = new StringTokenizer(line);
		hourPay = new int[hour_count];
		for(int i=0; i<hour_count; i++)
			hourPay[i] = Integer.parseInt(words.nextToken());
		records = new ArrayList<Record>();
		while((line = input.readLine()) != null) {
			words = new StringTokenizer(line, delim);
			String name = words.nextToken();
			int timeValue = 0;
			for(int i=0; i<time_data_count; ++i)
				timeValue = time_delim_increase * timeValue + Integer.parseInt(words.nextToken());
			records.add(new Record(name, timeValue, words.nextToken().equals("exit"), Integer.parseInt(words.nextToken())));
		}
		input.close();
		classificate();
	}
}