package Database.DAO;

import Database.Mappers.ShareMapper;
import Database.Models.ShareModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ShareDAO {

    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ShareDAO(NamedParameterJdbcTemplate namedParameterJdbcTemplate, JdbcTemplate jdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createShare(List<ShareModel> shares) {
        String sql = "INSERT INTO Shares(Owner, Issuer, Percentage) VALUES (?, ?, ?)";

        if (shares.isEmpty()) {
            return;
        }
        try (Connection con = this.jdbcTemplate.getDataSource().getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.NO_GENERATED_KEYS)) {

            for (ShareModel share : shares) {
                ps.setString(1, share.getOwner());
                ps.setString(2, share.getIssuer());
                ps.setFloat(3, share.getPercentage());
                ps.addBatch();
            }
            ps.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ShareModel> getShares() {
        String sql = "UPDATE Shares " +
                "SET Owner=sub.Owner " +
                "FROM (SELECT Owner, Issuer FROM Shares GROUP BY Owner, Issuer HAVING SUM(Percentage) >= 50) AS sub " +
                "WHERE Shares.Owner = sub.Issuer";
        SqlParameterSource namedParameters = new MapSqlParameterSource();

        int result = 1;
        while (result > 0) {
            result = this.namedParameterJdbcTemplate.update(sql, namedParameters);
        }

        sql = "SELECT Owner, Issuer, SUM(Percentage) AS Percentage FROM Shares GROUP BY Owner, Issuer HAVING SUM(Percentage) >= 50";

        List<ShareModel> shares = new ArrayList<>();
        shares.addAll(this.namedParameterJdbcTemplate.query(sql, namedParameters, new ShareMapper()));
        return shares;
    }

    public void clearTable() {
        String sql = "TRUNCATE TABLE Shares CASCADE";
        SqlParameterSource namedParameters = new MapSqlParameterSource();
        this.namedParameterJdbcTemplate.update(sql, namedParameters);
    }
}
