package main;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import BusProgress.BusProgress;
import Scheduler.Scheduler;
import database.GetDBData;
import database.PushDB;
import distance.Distance;
import location.Location;
import station.BusStation;

public class Main {
	static double[] curDis;
	static double[] maxDis;
	static int[] progress;
	static BusStation[] busStation;
	static Scheduler sd;
	
	
	public static void main(String[] args) {
		int cnt=1;
		while(true) {
			
			/*
			 * 10초마다 동기화.
			 */
			int sleepTime = 1000 * 10;
			long now = System.currentTimeMillis();
            Date date = new Date(now);
            SimpleDateFormat sdfNow = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            System.out.println((cnt++)+"]동기화 시간: "+ sdfNow.format(date));
            
			/*
			 * 시간표에 시간과 지금시간 비교해서 코스 선정
			 */
			sd = new Scheduler();
			BusStation.checkTime(sd);

			/*
			 * 코스 고정(테스트를 위해)
			 */
//          sd = new Scheduler();
//			sd.setCurrentCourse('G');
			
			/*
			 * busStation 객체가 없다면
			 */
			if(sd.getCurrentCourse() == 'E') {
				System.err.println("Course Error");
			}
            if(busStation == null) {
            	// 객체 생성
            	busStation = BusStation.checkCourse(sd.getCurrentCourse());
            	
            	/*
            	 * busStation의 마지막 역까지 도착햇다면
            	 */
            } else if(busStation[busStation.length-1].getIsBusArrived()){
            	// 객체 다시 생성
            	busStation = BusStation.checkCourse(sd.getCurrentCourse());
            } 
			Calendar cal = Calendar.getInstance();
			int curHour = cal.get(Calendar.HOUR_OF_DAY);
			int curMinute = cal.get(Calendar.MINUTE);
			

			try {
				if(sd.getCurrentCourse() == 'E') throw new Exception("코스 없음");
				processing();
			} catch (NullPointerException e) {
				System.out.println("값 없음.");
			} catch (Exception e) {
				System.err.println("오류발생: " + e.getMessage());
			}
			
			if (BusStation.getHour() <= curHour && BusStation.getMinute() <= curMinute) {
				try {
					push();
				} catch (Exception e) {
					System.out.println("push Error");
				}
				
			}
			
//			try {
//				push();
//			} catch (Exception e) {
//				System.out.println("클래스 이름 못찾음");
//			}
			
			try {
				Thread.sleep(sleepTime);
			}catch (Exception e) {
				e.printStackTrace();
			}

		}
			
	}
	
	static void push() {
		PushDB pushDB = new PushDB();
			try {
				pushDB.pushData(busStation);
			} catch (Exception e) {
				System.out.println("연결 오류");
			}
		
	}
	
	static void processing() throws NullPointerException{
		
		/*
		 * 코스 출력
		 */
		System.out.println("코스: " + sd.getCurrentCourse());
		
		curDis = new double[busStation.length - 1];
		maxDis = new double[busStation.length - 1];
		progress = new int[busStation.length - 1];

		GetDBData getDBData = new GetDBData();
		try {
			getDBData.getJSONData();
		} catch (Exception e) {
			e.printStackTrace();
		}
		getDBData.getDBData();


		Location currentLocation = new Location(getDBData.getLatitude(), getDBData.getLongitude());
		for (int i = 0; i < curDis.length; i++) {
			curDis[i] = Distance.distance(currentLocation, busStation[i+1]);
		}
		

		for (int i = 0; i < busStation.length - 1; i++) {
			maxDis[i] = Distance.distance(busStation[i], busStation[i + 1]);
		}

		
		
		
		// Progress
		for (int i = 0; i < progress.length; i++) {
			progress[i] = BusProgress.getProgress(maxDis[i], curDis[i]);
		}
		
		
		
		
//		System.out.println(maxDis[6]);
//		System.out.println(curDis[6]);
//		System.out.println(BusProgress.getProgress(maxDis[6], curDis[6]));
		/*
		 * 실험용
		 */
//		Scanner input = new Scanner(System.in);
//		for (int i = 0; i < 5; i++) {
//			System.out.print("입력>>> ");
//			progress[i] = input.nextInt();
//		}
//		progress[0] = 99;
//		progress[1] = 100;
//		progress[2] = 100;
//		progress[3] = 100;
//		progress[4] = 100;
//		progress[5] = 100;
//		progress[6] = 100;
		
		
		
		
		busStation[0].setIsBusArrived(true);
		for (int i = 0; i < progress.length; i++) {
			// 전 정류장에 먼저 도착햇는지 여부 확인
			if (i != 0 && !busStation[i].getIsBusArrived()) {
				continue;
			}
			else busCheck(busStation[i + 1], progress[i]);
		}
		
		
		
//		input.close();
	}
	
	static void print() throws NullPointerException{
		System.out.println();
		System.out.println("-------------------------------------");
		System.out.println();

		/*
		 * 출력
		 */
		System.out.println("curDis:" + curDis.length);
		for (int i = 0; i < curDis.length; i++) {
			System.out.println(i+"] DB위치 -> " + busStation[i+1].getStationName() + ": " + curDis[i]);

		}
		System.out.println();

		System.out.println("maxDis:" + maxDis.length);
		for (int i = 0; i < maxDis.length; i++) {
			System.out.println(
					i+"] "+busStation[i].getStationName() + "->" 
			+ busStation[i + 1].getStationName() + ": " + maxDis[i]);

		}
		System.out.println();

		System.out.println("Progress: " + progress.length);
		for (int i = 0; i < progress.length; i++) {
			System.out.println(i+"] DB위치 -> "
			+ busStation[i + 1].getStationName() + ": " + progress[i]);
		}
		System.out.println();

		System.out.println("isBusArrived: ");
		System.out.println("시작: " + busStation[0].getStationName());
		for (int i = 1; i <= progress.length; i++) {
			System.out.println(busStation[i].getStationName() + ": \n\t" + busStation[i].getIsBusArrived());
		}
		System.out.println();
		
	}

	static void busCheck(BusStation busStation, int progress) {
//		if(busStation.getIsBusArrived()) {
//			busStation.setIsBusArrived(true);
//		}
		if (progress >= 96) {
			busStation.setIsBusArrived(true);
		}
	}

}
