package Database.Controllers;

import Database.DAO.*;
import Database.Models.ShareModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
public class ShareController {

    private ShareDAO shareDAO;

    @Autowired
    public ShareController(ShareDAO shareDAO) {
        this.shareDAO = shareDAO;
    }

    @RequestMapping(value = "/get", method = RequestMethod.POST)
    public ResponseEntity createGetShares(@RequestBody List<ShareModel> shares) {
        shareDAO.createShare(shares);
        final String result = this.genResultTable(shareDAO.getShares());
        shareDAO.clearTable();

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @RequestMapping(value = "/clear", method = RequestMethod.GET)
    public ResponseEntity clear() {
        shareDAO.clearTable();

        return ResponseEntity.status(HttpStatus.OK).body("OK");
    }

    private String genResultTable(List<ShareModel> shares) {
        String result = "<table><tr><th>Company</th><th>Owner</th></tr>";
        for (ShareModel share : shares) {
            result += "<tr><td>" + share.getIssuer() + "</td><td>" + share.getOwner() + "</td></tr>";
        }
        result += "</table>";

        return result;
    }
}
