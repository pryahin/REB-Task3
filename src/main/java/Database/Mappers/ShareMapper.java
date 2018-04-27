package Database.Mappers;

import Database.Models.ShareModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ShareMapper implements RowMapper<ShareModel> {
    @Override
    public ShareModel mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        ShareModel forumModel = new ShareModel();
        forumModel.setOwner(resultSet.getString("Owner"));
        forumModel.setIssuer(resultSet.getString("Issuer"));
        forumModel.setPercentage(resultSet.getFloat("Percentage"));
        return forumModel;
    }
}
