package Database.Models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ShareModel {

    private String owner;
    private String issuer;
    private float percentage;

    @JsonCreator
    public ShareModel(
            @JsonProperty("Owner") String owner,
            @JsonProperty("Issuer") String issuer,
            @JsonProperty("Percentage") float percentage
    ) {
        this.owner = owner;
        this.issuer = issuer;
        this.percentage = percentage;
    }

    public ShareModel() {
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}
