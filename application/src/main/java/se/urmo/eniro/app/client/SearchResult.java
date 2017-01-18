package se.urmo.eniro.app.client;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SearchResult {

    private final List<Adverts> adverts;

    @JsonCreator
    public SearchResult(@JsonProperty("adverts") List<Adverts> adverts) {
        this.adverts = adverts;
    }

    public List<Adverts> adverts() {
        return adverts;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "adverts=" + adverts +
                '}';
    }

    public static class Adverts {
        private final CompanyInfo companyInfo;
        private final String infoPageLink;

        @JsonCreator
        public Adverts(@JsonProperty("companyInfo") CompanyInfo companyInfo,
                       @JsonProperty("infoPageLink") String infoPageLink) {
            this.companyInfo = companyInfo;
            this.infoPageLink = infoPageLink;
        }

        public CompanyInfo companyInfo() {
            return companyInfo;
        }

        public String infoPageLink() {
            return infoPageLink;
        }

        @Override
        public String toString() {
            return "Adverts{" +
                    "companyInfo=" + companyInfo +
                    ", infoPageLink='" + infoPageLink + '\'' +
                    '}';
        }
    }

    public static class CompanyInfo {

        private final String companyName;

        @JsonCreator
        public CompanyInfo(@JsonProperty("companyName") String companyName) {
            this.companyName = companyName;
        }

        public String companyName() {
            return companyName;
        }

        @Override
        public String toString() {
            return "CompanyInfo{" +
                    "companyName='" + companyName + '\'' +
                    '}';
        }
    }
}
