package database;

import java.sql.*;
import station.BusStation;

public class PushDB {

	public void pushData(BusStation[] busStation) {

		Connection conn = null;
		PreparedStatement stmt = null;
		// Statement stmt;
		ResultSet rs = null;
		String sql;

		try {

			Class.forName("org.mariadb.jdbc.Driver"); // JDBC driver를 메모리에 로드
			conn = DriverManager.getConnection("jdbc:mariadb://127.0.0.1:3306/DBName", "ID", "PASSWARD");

			System.err.println("접속 성공");
			stmt = conn.prepareStatement("select * from station");
			/*
			 * 원하는 쿼리문
			 */
			rs = stmt.executeQuery("DELETE FROM station");

			if (busStation != null) {
				for (int i = 1; i < busStation.length; i++) {
					sql = "insert into station values('" + busStation[i].getStationName() + "','"
							+ busStation[i].getIsBusArrived() + "')";
					rs = stmt.executeQuery(sql);
				}
			} else {
				sql = "insert into station values('" + "NoBus" + "','" + "false" + "')";
				rs = stmt.executeQuery(sql);
				System.err.println("NoBus");
			}
		} catch (SQLException e) {
			System.err.println("접속 실패: " + e.getMessage());
			
		} catch (ClassNotFoundException e) {
			System.err.println("ClassNotFound: "+ e.getMessage());

		} catch (Exception e) {
			System.err.println("Exception발생: " + e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close(); // 선택 사항
				}

				if (stmt != null) {
					stmt.close(); // 선택사항이지만 호출 추천
				}

				if (conn != null) {
					conn.close(); // 필수 사항
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
