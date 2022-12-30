package Randstad.Dmarc;

public class Data {
    private String OpcoCode;
    private String DomainName;
    private String transformation;
    private String spfPolicy;
    private String DMARCPolicy;



    public String getOpcoCode() {
        return OpcoCode;
    }

    public void setOpcoCode(String opcoCode) {
        OpcoCode = opcoCode;
    }

    public String getDomainName() {
        return DomainName;
    }

    public void setDomainName(String domainName) {
        DomainName = domainName;
    }

    public String getTransformation() {
        return transformation;
    }

    public void setTransformation(String transformation) {
        this.transformation = transformation;
    }

    public String getSpfPolicy() {
        return spfPolicy;
    }

    public void setSpfPolicy(String spfPolicy) {
        this.spfPolicy = spfPolicy;
    }

    public String getDMARCPolicy() {
        return DMARCPolicy;
    }

    public void setDMARCPolicy(String DMARCPolicy) {
        this.DMARCPolicy = DMARCPolicy;
    }
}
