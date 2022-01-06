package entity.db;

import utils.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Logger;

/**
 * This {@code AIMSDB} class represent database
 * in our ECO BIKE Software.
 *
 * @author nhom10
 *
 */
public class AIMSDB {

	private static final Logger LOGGER = Utils.getLogger(Connection.class.getName());
	private static Connection connect;

    /**
     * phuong thuc ket noi den co so du lieu
     * @return ket noi neu ket noi thanh cong
     */
    public static Connection getConnection() {
        if (connect != null) return connect;
        try {
			Class.forName("org.sqlite.JDBC");
            String url = "jdbc:sqlite:assets/db/capstone.db";
            connect = DriverManager.getConnection(url);
            LOGGER.info("Connect database successfully");
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        } 
        return connect;
    }
    

    public static void main(String[] args) {
        AIMSDB.getConnection();
    }
}
